package zsb.azb.mcm.Listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import zsb.azb.mcm.Mapper.ManholeMapper;
import zsb.azb.mcm.Netty.UDPClient;
import zsb.azb.mcm.Service.EggService;
import zsb.azb.mcm.Service.FeignService;
import zsb.azb.mcm.Service.TSService;
import zsb.azb.mcm.Service.TYService;
import zsb.azb.mcm.Utils.AES;

@Component
public class RedisKeyExpirationListener
        extends KeyExpirationEventMessageListener
{
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private FeignService feignService;
    @Autowired
    private EggService eggService;
    @Autowired
    private TYService tyService;
    @Autowired
    private TSService tsService;
    @Autowired
    private ManholeMapper manholeMapper;
    @Autowired
    private UDPClient udpClient;
    @Value("${jd.appid}")
    private String appId;
    @Value("${jd.appSecret}")
    private String appSecret;
    @Value("${mfp.ip}")
    private String mfpIp;
    @Value("${retry.times}")
    private int times;

    @Value("${ts.pdk}")
    private String pdk;
    @Value("${ts.preId}")
    private String preId;
    @Value("${ts.topic}")
    private String topic;

    Map<String, Object> bodys = new HashMap();
    List<String> FJLJList = new ArrayList();
    private String token = "";
    private int retry = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer)
    {
        super(listenerContainer);
    }

    public void onMessage(Message message, byte[] pattern)
    {
        String expiredKey = message.toString();
        if (expiredKey.startsWith("Alarm:"))
        {
            String saveKey = "Data" + expiredKey.substring(5);
            String jsonStr = (String)this.stringRedisTemplate.opsForValue().get(saveKey);
            System.out.println(new Date() + " ��redis����key��" + saveKey + "��value��" + jsonStr);
            String imei = saveKey.split(":")[2];
            String JGBH = imei.substring(9);
            String JGBM = this.manholeMapper.getJGBM(JGBH);
            this.FJLJList = this.manholeMapper.getTPLJ(JGBM);

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

            JSONArray files = new JSONArray();
            for (int i = 0; i < this.FJLJList.size(); i++)
            {
                if (i > 1) {
                    break;
                }
                String FJLJ = (String)this.FJLJList.get(i);
                JSONObject body = new JSONObject();
                String fileName = imei + this.sdf.format(new Date()) + "0" + (i + 1) + ".jpg";
                body.put("token", this.token);
                body.put("name", fileName);
                body.put("path", FJLJ);

                String jpg = this.eggService.pushTp(body);
                JSONObject file = new JSONObject();
                file.put("type", "0");
                file.put("fileName", fileName);
                file.put("filePath", jpg);
                files.add(file);
                System.out.println("图元图片推送over..." + FJLJ);
            }
            JSONObject newJSON = JSONObject.parseObject(jsonStr);
            newJSON.put("files", files);
            while (this.retry < this.times) {
                try
                {
                    System.out.println(new Date() + " ��������������...");
                    System.out.println("token=>" + this.token);
                    System.out.println(newJSON.toJSONString());

                    JSONObject pushResult = this.tyService.pushTY("bearer " + this.token, newJSON);
                    System.out.println(new Date() + " ������������... " + pushResult.toJSONString());
                    System.out.println("Rsp:" + saveKey.split(":")[1] + ":" + saveKey.split(":")[2]);
                    this.stringRedisTemplate.opsForValue().set("Rsp:" + saveKey.split(":")[1] + ":" + saveKey.split(":")[2], pushResult.get("data").toString(), 7L, TimeUnit.DAYS);

                    this.retry = 99;
                    System.out.println("开始推送数据...");
                    String signature = AES.encryptHex(preId+"&"+topic ,pdk);
                    String body = AES.encryptHex(newJSON.toJSONString(),pdk);
                    JSONObject tsResult = this.tsService.pushTS(preId,topic,signature, body);
                    System.out.println(new Date() + "推送数据结果..." + tsResult.toJSONString());
                }
                catch (Exception ex)
                {
                    this.retry += 1;
                    System.out.println(this.retry + " times");
                }
            }
            if (this.retry != 99) {
                System.out.println("����������������3����!!!");
            }
            this.retry = 0;
        }
    }
}
