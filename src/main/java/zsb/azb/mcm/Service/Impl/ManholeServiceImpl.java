package zsb.azb.mcm.Service.Impl;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.ManholeMapper;
import zsb.azb.mcm.Mapper.ProjectMapper;
import zsb.azb.mcm.Model.CommandEntity;
import zsb.azb.mcm.Model.JDDataEntity;
import zsb.azb.mcm.Model.JDEventEntity;
import zsb.azb.mcm.Model.JDManholeEntity;
import zsb.azb.mcm.Model.JDManholeStatus;
import zsb.azb.mcm.Model.ManholeRecordEntity;
import zsb.azb.mcm.Model.MapData;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.TokenPojo;
import zsb.azb.mcm.Service.FeignService;
import zsb.azb.mcm.Service.JdIotService;
import zsb.azb.mcm.Service.ManholeService;
import zsb.azb.mcm.Utils.CommandManager;
import zsb.azb.mcm.Utils.ResponseManager;
import zsb.azb.mcm.Utils.Utilty;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.ExecuteOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Execute;

@Service
public class ManholeServiceImpl
        implements ManholeService
{
    @Autowired
    private ManholeMapper manholeMapper;
    @Autowired
    private TokenPojo tokenPojo;
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private FeignService feignService;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CommandManager commandManager;
    @Autowired
    private JdIotService jdIotService;
    Random random = new Random();

    public ResponsePojo countManhole(String searchKey)
    {
        try
        {
            int count = this.manholeMapper.getCount(searchKey);
            Map<String, Integer> data = new Hashtable();
            data.put("count", Integer.valueOf(count));
            return ResponseManager.buildSuccess(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildError("获取数量失败");
    }

    public ResponsePojo listManhole(int page, int pageSize, String searchKey)
    {
        try
        {
            int limit = (page - 1) * pageSize;
            List<JDManholeEntity> manholeList = this.manholeMapper.getManholeByLimit(Integer.valueOf(limit), Integer.valueOf(pageSize), searchKey);
            return ResponseManager.buildSuccess(manholeList);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildError("error");
    }

    public ResponsePojo getManhole(String deviceId)
    {
        try
        {
            JDManholeStatus manholeStatus = this.manholeMapper.getManholeStatus(deviceId);
            return ResponseManager.buildSuccess(manholeStatus);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildError("error");
    }

    public ResponsePojo manholeRecordCount(String IMEI)
    {
        try
        {
            int count = this.manholeMapper.manholeRecordCount(IMEI);
            Map<String, Integer> data = new Hashtable();
            data.put("count", Integer.valueOf(count));
            return ResponseManager.buildSuccess(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeRecordList(int page, int pageSize, String IMEI)
    {
        try
        {
            int limit = (page - 1) * pageSize;
            List<ManholeRecordEntity> recordList = this.manholeMapper.manholeRecordByLimit(Integer.valueOf(limit), Integer.valueOf(pageSize), IMEI);
            return ResponseManager.buildSuccess(recordList);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeEventCount(String deviceId)
    {
        try
        {
            int count = this.manholeMapper.manholeEventCount(deviceId);
            Map<String, Integer> data = new Hashtable();
            data.put("count", Integer.valueOf(count));
            return ResponseManager.buildSuccess(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeEventList(int page, int pageSize, String deviceId)
    {
        try
        {
            int limit = (page - 1) * pageSize;
            List<JDEventEntity> recordList = this.manholeMapper.manholeEventByLimit(Integer.valueOf(limit), Integer.valueOf(pageSize), deviceId);
            return ResponseManager.buildSuccess(recordList);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeDataCount(String deviceId)
    {
        try
        {
            int count = this.manholeMapper.manholeDataCount(deviceId);
            Map<String, Integer> data = new Hashtable();
            data.put("count", Integer.valueOf(count));
            return ResponseManager.buildSuccess(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeDataList(int page, int pageSize, String deviceId)
    {
        try
        {
            int limit = (page - 1) * pageSize;
            List<JDDataEntity> recordList = this.manholeMapper.manholeDataByLimit(Integer.valueOf(limit), Integer.valueOf(pageSize), deviceId);
            return ResponseManager.buildSuccess(recordList);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo createCommand(Map<String, String> cmd)
    {
        String imei = (String)cmd.get("imei");
        String cmdType = (String)cmd.get("cmdType");
        String cmdParam = (String)cmd.get("cmdParam");
        String result = "";

        String msgId = Utilty.leftPad(Utilty.int2HexStr(this.random.nextInt(65535)), 4);
        String cmdStr = this.commandManager.postCmdToJD(cmdType, cmdParam, msgId);
        this.jdIotService.serviceInvoke(imei, "RemoteControl", cmdStr);
        this.manholeMapper.insertCmd(imei, this.tokenPojo.getUserName(), cmdType, cmdParam, cmdStr, "等待应答", msgId);

        return ResponseManager.buildSuccess(result);
    }

    public ResponsePojo manholeCommandCount()
    {
        try
        {
            int count = this.manholeMapper.manholeCommandCount();
            Map<String, Integer> data = new Hashtable();
            data.put("count", Integer.valueOf(count));
            return ResponseManager.buildSuccess(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeCommandList(int page, int pageSize)
    {
        try
        {
            int limit = (page - 1) * pageSize;
            List<CommandEntity> recordList = this.manholeMapper.manholeCommandByLimit(Integer.valueOf(limit), Integer.valueOf(pageSize));
            return ResponseManager.buildSuccess(recordList);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo manholeMapList()
    {
        List<MapData> mapDataList = this.manholeMapper.listManholeMap();
        return ResponseManager.buildSuccess(mapDataList);
    }

    private void postCmdToDxIot() {}

    private void postCmdToLtIot() {}

    private void postCmdToUdp() {}

    private void postCmdToDxAep() {}

    private void postCmdToYdOneNet(String imei, String cmdType, String cmdParam)
    {
        String cmdStr = getCommandStr(cmdType, cmdParam);
        String app_key = this.manholeMapper.getProjectKey(imei);

        long currentTime = System.currentTimeMillis();
        currentTime += 172800000L;
        Date date = new Date(currentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Map data = new HashMap();
        data.put("args", cmdStr);
        com.alibaba.fastjson.JSONObject jobj = this.feignService.postOneNetCommand(app_key, imei, 3300, 0, 5750, dateFormat

                .format(date), data);

        Integer objId = Integer.valueOf(3300);
        Integer objInstId = Integer.valueOf(0);
        Integer readResId = Integer.valueOf(5750);

        ExecuteOpe executeOpe = new ExecuteOpe(app_key);
        Execute execute = new Execute(imei, objId, objInstId, readResId);

        org.json.JSONObject body = new org.json.JSONObject();
        body.put("args", "ping");

        executeOpe.operation(execute, body);
        if (jobj.get("errno").toString().equals("0")) {}
    }

    private String getCommandStr(String cmdType, String cmdParam)
    {
        String cmdStr = "";
        String HEAD = "A5";String length = "";String type = "";String param = "";String acc = "";
        String checkBody = "";
        switch (cmdType)
        {
            case "系统软复位":
                length = "01";
                type = "A0";
                param = "";
                break;
            case "获取心跳周期":
                length = "01";
                type = "A1";
                param = "";
                break;
            case "设置心跳周期":
                length = "03";
                type = "A1";
                param = String.format("%4s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取告警角度":
                length = "01";
                type = "A2";
                param = "";
                break;
            case "设置告警角度":
                length = "02";
                type = "A2";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取安全角度":
                length = "01";
                type = "A3";
                param = "";
                break;
            case "设置安全角度":
                length = "02";
                type = "A3";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "角度自适应":
                length = "01";
                type = "A4";
                param = "";
                break;
            case "获取服务器地址":
                length = "01";
                type = "A5";
                param = "";
                break;
            case "设置服务器地址":
                length = "07";
                type = "A5";
                String[] ipport = cmdParam.split(":");
                String ipp = ipport[0];
                String[] ips = ipp.split("\\.");
                String ip1 = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(ips[0])) }).replace(' ', '0');
                String ip2 = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(ips[1])) }).replace(' ', '0');
                String ip3 = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(ips[2])) }).replace(' ', '0');
                String ip4 = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(ips[3])) }).replace(' ', '0');
                String port = String.format("%4s", new Object[] { Integer.toHexString(Integer.parseInt(ipport[1])) }).replace(' ', '0');
                param = ip1 + ip2 + ip3 + ip4 + port;
                break;
            case "满溢自适应":
                length = "02";
                type = "A6";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取满溢冷却":
                length = "01";
                type = "A7";
                param = "";
                break;
            case "设置满溢冷却":
                length = "03";
                type = "A7";
                param = String.format("%4s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取满溢灵敏度":
                length = "01";
                type = "A8";
                param = "";
                break;
            case "设置满溢灵敏度":
                length = "02";
                type = "A8";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取防拆灵敏度":
                length = "01";
                type = "A9";
                param = "";
                break;
            case "设置防拆灵敏度":
                length = "02";
                type = "A9";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取安装状态":
                length = "01";
                type = "AA";
                param = "";
                break;
            case "设置安装状态":
                length = "02";
                type = "AA";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取气体采集周期":
                length = "01";
                type = "AB";
                param = "";
                break;
            case "设置气体采集周期":
                length = "03";
                type = "AB";
                param = String.format("%4s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
                break;
            case "获取振动阈值":
                length = "01";
                type = "AC";
                param = "";
                break;
            case "设置阵容阈值":
                length = "02";
                type = "AC";
                param = String.format("%2s", new Object[] { Integer.toHexString(Integer.parseInt(cmdParam)) }).replace(' ', '0');
        }
        checkBody = type + param;
        acc = Utilty.SumCheck(checkBody);
        cmdStr = HEAD + length + type + param + acc;
        return cmdStr.toUpperCase();
    }
}
