package zsb.azb.mcm.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.ManholeMapper;
import zsb.azb.mcm.Model.JDManholeEntity;
import zsb.azb.mcm.Model.JDManholeStatus;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Netty.UDPClient;
import zsb.azb.mcm.Service.FeignService;
import zsb.azb.mcm.Service.JdIotService;
import zsb.azb.mcm.Service.TSService;
import zsb.azb.mcm.Service.TYService;
import zsb.azb.mcm.Utils.AES;
import zsb.azb.mcm.Utils.ResponseManager;
import zsb.azb.mcm.Utils.Utilty;

@Service
public class JdIotServiceImpl
        implements JdIotService
{
    @Autowired
    private FeignService feignService;
    @Autowired
    private TYService tyService;
    @Autowired
    private TSService tsService;
    @Autowired
    private ManholeMapper manholeMapper;
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private UDPClient udpClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Value("${jd.appid}")
    private String appId;
    @Value("${jd.appSecret}")
    private String appSecret;
    @Value("${mfp.ip}")
    private String mfpIp;
    @Value("${retry.times}")
    private int times;
    @Value("${push.move}")
    private int pushMove;
    @Value("${cache.time}")
    private long cacheTime;
    @Value("${save.time}")
    private long saveTime;
    @Value("${alarm.angle}")
    private int alarmAngle;

    @Value("${ts.pdk}")
    private String pdk;
    @Value("${ts.preId}")
    private String preId;
    @Value("${ts.topic}")
    private String topic;

    ObjectMapper mapper = new ObjectMapper();
    private String token = "";
    Map<String, Object> bodys = new HashMap();
    private int retry = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

    public void forwardEvents(String deviceId, String type, String value, JSONObject body)
            throws Exception
    {
        String eventType = "";String dataType = "";String cacheKey = "";String saveKey = "";String time = "";
        String imei = this.manholeMapper.getManholeImei(deviceId);
        int isBlock = this.manholeMapper.getBlock(imei);
        String rspKey = "";
        this.manholeMapper.updateManhole(deviceId);
        boolean saveFlag = false;boolean redisPush = false;
        time = this.sdf.format(new Date());
        String key;
        switch (type)
        {
            case "0":
                eventType = "电池低电量报警";
                dataType = eventType;
                break;
            case "1":
                eventType = "电池耗尽报警";
                dataType = eventType;
                break;
            case "4":
                eventType = "井盖移动报警发生";
                dataType = eventType;
                if (this.pushMove != 0)
                {
                    String moveFlag = this.manholeMapper.getMoveFlag(deviceId);
                    if ((!moveFlag.equals("异常")) && (Integer.parseInt(value) > this.alarmAngle))
                    {
                        saveFlag = true;
                        cacheKey = "Alarm:M:" + imei + ":" + time;
                        saveKey = "Data:M:" + imei + ":" + time;
                    }
                }
                this.manholeMapper.updateMove(deviceId, value, "异常");
                break;
            case "5":
                eventType = "井盖移动报警恢复";
                dataType = eventType;
                saveFlag = true;
                if (this.pushMove != 0)
                {
                    Set<String> mKeys = this.stringRedisTemplate.keys("Alarm:M:" + imei + ":*");
                    if (mKeys.size() > 0)
                    {
                        for (String mKey : mKeys)
                        {
                            this.stringRedisTemplate.delete(mKey);
                            System.out.println("delete key => " + mKey);
                        }
                    }
                    else
                    {
                        rspKey = (String)this.stringRedisTemplate.opsForValue().get("Rsp:M:" + imei);
                        redisPush = true;
                    }
                }
                this.manholeMapper.updateMove(deviceId, value, "正常");
                break;
            case "6":
                eventType = "井盖打开报警发生";
                dataType = eventType;
                String angle = this.manholeMapper.getOpenFlag(deviceId);
                int openFlag = Integer.parseInt(angle);
                if ((openFlag < this.alarmAngle) && (Integer.parseInt(value) > this.alarmAngle))
                {
                    saveFlag = true;
                    System.out.println(new Date() + "缓存了一个打开报警...");
                    cacheKey = "Alarm:O:" + imei + ":" + time;
                    saveKey = "Data:O:" + imei + ":" + time;
                }
                this.manholeMapper.updateAngle(deviceId, value);
                break;
            case "7":
                eventType = "井盖打开报警恢复";
                dataType = eventType;
                saveFlag = true;
                Set<String> oKeys = this.stringRedisTemplate.keys("Alarm:O:" + imei + ":*");
                if (oKeys.size() > 0)
                {
                    for (String oKey : oKeys)
                    {
                        this.stringRedisTemplate.delete(oKey);
                        System.out.println("delete key => " + oKey);
                    }
                }
                else
                {
                    rspKey = (String)this.stringRedisTemplate.opsForValue().get("Rsp:O:" + imei);
                    redisPush = true;
                }
                this.manholeMapper.updateAngle(deviceId, value);
                break;
            case "10":
                eventType = "水位发生变化";
                String s = "正常";
                dataType = "满溢恢复";
                if (value.equals("1"))
                {
                    s = "异常";
                    dataType = "满溢告警";
                    type = "9";

                    String overflowFlag = this.manholeMapper.getOverflowFlag(deviceId);
                    if (!overflowFlag.equals("异常"))
                    {
                        saveFlag = true;
                        System.out.println(new Date() + "缓存了一个满溢告警...");
                        cacheKey = "Alarm:W:" + imei + ":" + time;
                        saveKey = "Data:W:" + imei + ":" + time;
                    }
                }
                else
                {
                    saveFlag = true;
                    Set<String> wKeys = this.stringRedisTemplate.keys("Alarm:W:" + imei + ":*");
                    Iterator localIterator3;
                    if (wKeys.size() > 0)
                    {
                        for (localIterator3 = wKeys.iterator(); localIterator3.hasNext();)
                        {
                            key = (String)localIterator3.next();
                            this.stringRedisTemplate.delete(key);
                            System.out.println("delete key => " + key);
                        }
                    }
                    else
                    {
                        rspKey = (String)this.stringRedisTemplate.opsForValue().get("Rsp:W:" + imei);
                        redisPush = true;
                    }
                }
                this.manholeMapper.updateOverflow(deviceId, s);
                break;
            case "11":
                eventType = "可燃气体浓度超限报警";
                dataType = eventType;
                String gasFlag = this.manholeMapper.getGasFlag(deviceId);
                if (!gasFlag.equals("异常"))
                {
                    saveFlag = true;
                    cacheKey = "Alarm:G:" + imei + ":" + time;
                    saveKey = "Data:G:" + imei + ":" + time;
                }
                this.manholeMapper.updateGas(deviceId, "异常");
                break;
            case "12":
                eventType = "可燃气体浓度超限恢复";
                dataType = eventType;
                saveFlag = true;
                Set<String> gKeys = this.stringRedisTemplate.keys("Alarm:G:" + imei + ":*");
                if (((Set)gKeys).size() > 0)
                {
                    for (String gKey : gKeys)
                    {
                        this.stringRedisTemplate.delete(gKey);
                        System.out.println("delete key => " + gKey);
                    }
                }
                else
                {
                    rspKey = (String)this.stringRedisTemplate.opsForValue().get("Rsp:G:" + imei);
                    redisPush = true;
                }
                this.manholeMapper.updateGas(deviceId, "正常");
                break;
            default:
                eventType = "其他报警";
                dataType = eventType;
        }
        body.put("evtId", rspKey);

        String jsonStr = body.toJSONString();
        jsonStr = jsonStr.replace("Type", "type").replace("Value", "value");
        if (type.equals("9")) {
            jsonStr = jsonStr.replace("\"type\":10", "\"type\":9");
        }
        this.manholeMapper.insertEvent(deviceId, eventType, value);
        if (isBlock > 0) {
            return;
        }
        if (saveFlag)
        {
            JDManholeStatus jdManholeStatus = this.manholeMapper.getManholeStatus(deviceId);
            String lora = azbLora(imei.substring(9), dataType, jdManholeStatus.getAngle(), jdManholeStatus.getOverflowStatus(), jdManholeStatus.getGasStatus());
            this.udpClient.send(new InetSocketAddress(this.mfpIp, 6666), Utilty.hex2Bytes(lora));
        }
        if (!cacheKey.equals(""))
        {
            this.stringRedisTemplate.opsForValue().set(cacheKey, "1", this.cacheTime, TimeUnit.MILLISECONDS);
            this.stringRedisTemplate.opsForValue().set(saveKey, jsonStr, this.saveTime, TimeUnit.MILLISECONDS);
        }
        System.out.println(rspKey);
        if ((redisPush) && (null != rspKey))
        {
            JSONObject newJSON = JSONObject.parseObject(jsonStr);
            while (this.retry < this.times) {
                try
                {
                    System.out.println(newJSON);
                    JSONObject pushResult = this.tyService.pushTY("bearer " + this.token, newJSON);
                    System.out.println(pushResult);

                    this.retry = 99;

                    System.out.println("开始推送数据...");
                    String signature = AES.encryptHex(preId+"&"+topic ,pdk);
                    String tsBody = AES.encryptHex(newJSON.toJSONString(),pdk);
                    JSONObject tsResult = this.tsService.pushTS(preId,topic,signature, tsBody);
                    System.out.println(new Date() + "推送数据结果..." + tsResult.toJSONString());
                }
                catch (Exception ex)
                {
                    this.retry += 1;
                    System.out.println(this.retry + " times");
                    this.bodys.put("username", "hikvision");
                    this.bodys.put("password", "mVcP/R8C+tq8k1AP7oe39g==");
                    this.bodys.put("grant_type", "password");
                    this.bodys.put("client_secret", "lBTqrKS0kZixOFXeZ0HRng==");
                    this.bodys.put("client_id", "FB1312D67BE84E1BBAE0778ED9F00BJH");
                    this.bodys.put("is_encrypt", Boolean.valueOf(true));
                    this.bodys.put("auth_type", "account");
                    this.bodys.put("scope", "app");
                    JSONObject tokenJson = this.tyService.getToken(this.bodys);
                    this.token = tokenJson.getString("access_token");
                    System.out.println(this.token);
                }
            }
            if (this.retry != 99) {
                System.out.println("图元推送超过3次!!!");
            }
            this.retry = 0;
        }
        else
        {
            System.out.println("rspKey is null");
        }
    }

    public void forwardCommand(String deviceId, String value)
    {
        System.out.println(value);
        String msgId = value.substring(2, 6);
        byte[] rawData = Utilty.hex2Bytes(value);
        int length = rawData[3];
        byte cmdType = rawData[4];
        String val = null;
        String setStatus = "";
        if (length == 11)
        {
            setStatus = rawData[14] == 0 ? "设置成功" : "设置失败";
        }
        else
        {
            setStatus = rawData[14] == 0 ? "获取成功" : "获取失败";
            switch (cmdType)
            {
                case 32:
                    break;
                case 33:
                    val = "" + (((rawData[15] & 0xFF) << 8) + (rawData[16] & 0xFF));
                    break;
                case 34:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 35:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 36:
                    break;
                case 37:
                    String ip = (rawData[15] & 0xFF) + "." + (rawData[16] & 0xFF) + "." + (rawData[17] & 0xFF) + "." + (rawData[18] & 0xFF);
                    int port = ((rawData[19] & 0xFF) << 8) + (rawData[20] & 0xFF);
                    val = ip + ":" + port;
                    break;
                case 38:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 39:
                    val = "" + (((rawData[15] & 0xFF) << 8) + (rawData[16] & 0xFF));
                    break;
                case 40:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 41:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 42:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 43:
                    val = "" + (((rawData[15] & 0xFF) << 8) + (rawData[16] & 0xFF));
                    break;
                case 44:
                    val = "" + (rawData[15] & 0xFF);
                    break;
                case 45:
                    val = (rawData[15] & 0xFF) + "," + (rawData[16] & 0xFF);
            }
        }
        this.manholeMapper.updateCmd(setStatus, val, msgId, deviceId);
    }

    public void forwardProperties(JSONObject body)
            throws Exception
    {
        JSONObject payload = body.getJSONObject("payload");
        String deviceId = payload.getString("deviceId");

        JSONObject state = payload.getJSONObject("state");
        JSONObject reported = state.getJSONObject("reported");
        JDManholeStatus jdManholeStatus = new JDManholeStatus();
        try
        {
            jdManholeStatus = (JDManholeStatus)JSONObject.toJavaObject(reported, JDManholeStatus.class);
        }
        catch (Exception localException) {}
        jdManholeStatus.setDeviceId(deviceId);

        this.manholeMapper.insertProperties(jdManholeStatus);
        this.manholeMapper.updateProperties(jdManholeStatus);

        String imei = this.manholeMapper.getManholeImei(deviceId);
        JDManholeStatus newJDManholeStatus = this.manholeMapper.getManholeStatus(deviceId);
        String lora = azbLora(imei.substring(9), "心跳", newJDManholeStatus.getAngle(), newJDManholeStatus.getOverflowStatus(), newJDManholeStatus.getGasStatus());

        this.udpClient.send(new InetSocketAddress(this.mfpIp, 6666), Utilty.hex2Bytes(lora));
    }

    public ResponsePojo deviceRegister(String deviceName, String description, String productId, String imei, String road, String district, String longitude, String latitude, String contacts)
    {
        ObjectNode params = this.mapper.createObjectNode();
        ObjectNode device = this.mapper.createObjectNode();

        device.put("deviceId", "");
        device.put("deviceName", deviceName);
        device.put("description", description);
        device.put("productId", productId);
        device.put("uniqueId", imei);
        device.put("mac", "");

        params.put("device", device);

        JSONObject result = this.feignService.deviceRegister(buildBody(params, "device/register"));
        System.out.println(result.toString());
        String deviceId = result.getJSONObject("params").getString("deviceId");
        System.out.println("deviceRegister device ID=>" + deviceId);

        JDManholeEntity jdManholeEntity = new JDManholeEntity();
        jdManholeEntity.setDeviceId(deviceId);
        jdManholeEntity.setDeviceName(deviceName);
        jdManholeEntity.setContacts(contacts);
        jdManholeEntity.setImei(imei);
        jdManholeEntity.setDistrict(district);
        jdManholeEntity.setRoad(road);
        jdManholeEntity.setProductId(productId);
        jdManholeEntity.setLongitude(longitude);
        jdManholeEntity.setLatitude(latitude);
        this.manholeMapper.insertManhole(jdManholeEntity);

        return ResponseManager.buildSuccess();
    }

    public JSONObject deviceUpdate(String deviceId, String deviceName, String imei, String description, String productId, String profileName, String profileValue)
    {
        ObjectNode params = this.mapper.createObjectNode();
        ObjectNode device = this.mapper.createObjectNode();
        device.put("deviceId", deviceId);
        device.put("deviceName", deviceName);
        device.put("description", description);
        device.put("productId", productId);
        device.put("uniqueId", imei);
        ArrayNode profiles = this.mapper.createArrayNode();
        ObjectNode profile = this.mapper.createObjectNode();
        profile.put("profileName", profileName);
        profile.put("profileValue", profileValue);
        profiles.add(profile);
        device.put("profiles", profiles);
        params.put("device", device);
        return this.feignService.deviceUpdate(buildBody(params, "device/update"));
    }

    public JSONObject deviceDelete(String deviceId)
    {
        ObjectNode params = this.mapper.createObjectNode();
        params.put("deviceId", deviceId);
        ObjectNode body = buildBody(params, "device/delete");

        this.manholeMapper.deleteManhole(deviceId);
        return this.feignService.deviceDelete(body);
    }

    public JSONObject deviceInfo(String deviceId)
    {
        ObjectNode params = this.mapper.createObjectNode();
        params.put("deviceId", deviceId);
        ObjectNode body = buildBody(params, "device/get");
        System.out.println(body);
        return this.feignService.deviceInfo(body);
    }

    public JSONObject deviceList(int pageNo, int pageSize, String productId)
    {
        ObjectNode params = this.mapper.createObjectNode();
        ObjectNode page = this.mapper.createObjectNode();
        page.put("pageNo", pageNo);
        page.put("pageSize", pageSize);
        ObjectNode query = this.mapper.createObjectNode();
        query.put("productId", productId);
        params.put("page", page);
        params.put("query", query);
        ObjectNode body = buildBody(params, "device/list");
        JSONObject result = this.feignService.deviceList(body);
        System.out.println(result.toString());
        return result;
    }

    public JSONObject thingList(String productId)
    {
        ObjectNode params = this.mapper.createObjectNode();
        params.put("productId", productId);
        ObjectNode body = buildBody(params, "thing/get");
        return this.feignService.thingList(body);
    }

    public JSONObject serviceInvoke(String deviceId, String service, String controlCmd)
    {
        ObjectNode params = this.mapper.createObjectNode();
        params.put("deviceId", deviceId);
        params.put("service", service);
        ObjectNode args = this.mapper.createObjectNode();
        args.put("controlCmd", controlCmd);
        params.put("args", args);
        ObjectNode body = buildBody(params, "device/service/invoke");
        System.out.println(body);
        return this.feignService.serviceInvoke(body);
    }

    private ObjectNode buildBody(ObjectNode params, String url)
    {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        ObjectNode root = this.mapper.createObjectNode();
        ObjectNode protocol = this.mapper.createObjectNode();
        ObjectNode client = this.mapper.createObjectNode();
        protocol.put("version", "1.0");
        protocol.put("name", "JD-IOT-ESTATE");
        client.put("appid", this.appId);
        client.put("userId", "1");

        long timestamp = System.currentTimeMillis();
        String signatureStr = id + protocol + client + params + timestamp + url;
        System.out.println(signatureStr);
        String signature = null;
        try
        {
            Mac hmacSha256 = Mac.getInstance("HmacSHA256");
            byte[] keyBytes = this.appSecret.getBytes("UTF-8");
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));
            signature = new String(Base64.encodeBase64(hmacSha256.doFinal(signatureStr.getBytes("UTF-8"))));
        }
        catch (UnsupportedEncodingException|NoSuchAlgorithmException|InvalidKeyException e)
        {
            e.printStackTrace();
        }
        root.put("id", id);
        root.put("protocol", protocol);
        root.put("client", client);
        root.put("params", params);
        root.put("timestamp", timestamp);
        root.put("signature", signature);
        System.out.println(root);
        return root;
    }

    public String azbLora(String manhole, String dataType, int angle, String gasStatus, String overflowStatus)
    {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer checkBuffer = new StringBuffer();
        stringBuffer.append("5A0C");
        byte statusByte = 0;
        System.out.println(dataType);
        String ftype;
        switch (dataType)
        {
            case "井盖打开报警发生":
                ftype = "02";
                break;
            case "满溢告警":
                ftype = "03";
                break;
            case "满溢恢复":
                ftype = "04";
                break;
            case "可燃气体浓度超限报警":
                ftype = "05";
                break;
            case "可燃气体浓度超限恢复":
                ftype = "06";
                break;
            case "井盖移动报警发生":
                ftype = "07";
                break;
            case "井盖移动报警恢复":
                ftype = "08";
                break;
            case "电池低电量报警":
                ftype = "09";
                break;
            case "电池低电量恢复":
                ftype = "0A";
                break;
            default:
                ftype = "01";
        }
        if (angle > 25) {
            statusByte = (byte)(statusByte | 0x1);
        }
        if (overflowStatus.equals("异常")) {
            statusByte = (byte)(statusByte | 0x8);
        }
        if (gasStatus.equals("异常")) {
            statusByte = (byte)(statusByte | 0x10);
        }
        checkBuffer.append(ftype);
        checkBuffer.append("02207DDB0001");
        checkBuffer.append(manhole);
        String statusHex = Integer.toHexString(statusByte & 0xFF);
        if (statusHex.length() == 1) {
            statusHex = '0' + statusHex;
        }
        checkBuffer.append(statusHex.toUpperCase());
        if (angle < 16) {
            checkBuffer.append('0' + Integer.toHexString(angle));
        } else {
            checkBuffer.append(Integer.toHexString(angle));
        }
        String checkString = checkBuffer.toString();
        String checked = Utilty.FFCheck(checkString);
        stringBuffer.append(checkString);
        stringBuffer.append(checked);
        return stringBuffer.toString().toUpperCase();
    }
}
