package zsb.azb.mcm.Utils;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class CommandManager
{
    public static String HEAD = "A5";
    public String body = "";
    public String cmd = "";
    Random random = new Random();

    private String buildReboot(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "20";
        } else {
            this.body = ("20" + payload);
        }
        this.cmd = (HEAD + msgId + "02" + this.body + Utilty.SumCheck(this.body));
        return this.cmd;
    }

    private String buildHeartBeat(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "21";
        } else {
            this.body = ("21" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildAlarm(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "22";
        } else {
            this.body = ("22" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildSafe(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "23";
        } else {
            this.body = ("23" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildAngleAdaption(String msgId)
    {
        this.body = "24";
        this.cmd = (HEAD + msgId + "0124" + Utilty.SumCheck(this.body));
        return this.cmd;
    }

    private String buildAddress(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "25";
        } else {
            this.body = ("25" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildOverflowAdaption(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "26";
        } else {
            this.body = ("26" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildOverflowCD(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "27";
        } else {
            this.body = ("27" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildOverflowSensitivity(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "28";
        } else {
            this.body = ("28" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildDismantleSensitivity(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "29";
        } else {
            this.body = ("29" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildDismantle(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "2A";
        } else {
            this.body = ("2A" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildGasCD(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "2B";
        } else {
            this.body = ("2B" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildVibrate(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "2C";
        } else {
            this.body = ("2C" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildMove(String msgId, String payload)
    {
        if (payload == null) {
            this.body = "2D";
        } else {
            this.body = ("2D" + payload);
        }
        return buildCmd(msgId, this.body);
    }

    private String buildCmd(String msgId, String body)
    {
        String length = Utilty.leftPad(Utilty.int2HexStr(body.length() / 2), 2);
        this.cmd = (HEAD + msgId + length + body + Utilty.SumCheck(body));
        return this.cmd;
    }

    public String postCmdToJD(String cmdType, Object cmdParam, String msgId)
    {
        String cmd = "";
        switch (cmdType)
        {
            case "系统复位":
                cmd = buildReboot(msgId, Utilty.leftPad(cmdParam.toString(), 2));
                break;
            case "角度自适应":
                cmd = buildAngleAdaption(msgId);
                break;
            case "满溢自适应":
                int data = Integer.parseInt(cmdParam.toString());
                cmd = buildOverflowAdaption(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "设置心跳周期":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildHeartBeat(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 4));
                break;
            case "设置告警角度":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildAlarm(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "设置安全角度":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildSafe(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "设置服务器地址":
                cmd = buildAddress(msgId, cmdParam.toString());
                break;
            case "设置满溢冷却时间":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildOverflowCD(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 4));
                break;
            case "设置满溢灵敏度":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildOverflowSensitivity(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "设置防拆灵敏度":
                break;
            case "设置安装状态":
                cmd = buildDismantle(msgId, Utilty.leftPad(cmdParam.toString(), 2));
                break;
            case "设置气体采集周期":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildGasCD(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 4));
                break;
            case "设置振动阈值":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildVibrate(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "设置位移参数":
                cmd = buildMove(msgId, cmdParam.toString());
                break;
            case "获取心跳周期":
                cmd = buildHeartBeat(msgId, null);
                break;
            case "获取告警角度":
                cmd = buildAlarm(msgId, null);
                break;
            case "获取安全角度":
                cmd = buildSafe(msgId, null);
                break;
            case "获取服务器地址":
                cmd = buildAddress(msgId, null);
                break;
            case "获取满溢冷却时间":
                cmd = buildOverflowCD(msgId, null);
                break;
            case "获取满溢灵敏度":
                cmd = buildOverflowSensitivity(msgId, null);
                break;
            case "获取防拆灵敏度":
                break;
            case "获取安装状态":
                cmd = buildDismantle(msgId, null);
                break;
            case "获取气体采集周期":
                cmd = buildGasCD(msgId, null);
                break;
            case "获取振动阈值":
                cmd = buildVibrate(msgId, null);
                break;
            case "获取位移参数":
                cmd = buildMove(msgId, null);
                break;
            case "透传指令":
                cmd = cmdParam.toString();
        }
        return cmd;
    }
}
