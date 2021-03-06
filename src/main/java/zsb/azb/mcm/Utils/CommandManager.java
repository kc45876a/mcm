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
            case "????????????":
                cmd = buildReboot(msgId, Utilty.leftPad(cmdParam.toString(), 2));
                break;
            case "???????????????":
                cmd = buildAngleAdaption(msgId);
                break;
            case "???????????????":
                int data = Integer.parseInt(cmdParam.toString());
                cmd = buildOverflowAdaption(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "??????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildHeartBeat(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 4));
                break;
            case "??????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildAlarm(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "??????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildSafe(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "?????????????????????":
                cmd = buildAddress(msgId, cmdParam.toString());
                break;
            case "????????????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildOverflowCD(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 4));
                break;
            case "?????????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildOverflowSensitivity(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "?????????????????????":
                break;
            case "??????????????????":
                cmd = buildDismantle(msgId, Utilty.leftPad(cmdParam.toString(), 2));
                break;
            case "????????????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildGasCD(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 4));
                break;
            case "??????????????????":
                data = Integer.parseInt(cmdParam.toString());
                cmd = buildVibrate(msgId, Utilty.leftPad(Utilty.int2HexStr(data), 2));
                break;
            case "??????????????????":
                cmd = buildMove(msgId, cmdParam.toString());
                break;
            case "??????????????????":
                cmd = buildHeartBeat(msgId, null);
                break;
            case "??????????????????":
                cmd = buildAlarm(msgId, null);
                break;
            case "??????????????????":
                cmd = buildSafe(msgId, null);
                break;
            case "?????????????????????":
                cmd = buildAddress(msgId, null);
                break;
            case "????????????????????????":
                cmd = buildOverflowCD(msgId, null);
                break;
            case "?????????????????????":
                cmd = buildOverflowSensitivity(msgId, null);
                break;
            case "?????????????????????":
                break;
            case "??????????????????":
                cmd = buildDismantle(msgId, null);
                break;
            case "????????????????????????":
                cmd = buildGasCD(msgId, null);
                break;
            case "??????????????????":
                cmd = buildVibrate(msgId, null);
                break;
            case "??????????????????":
                cmd = buildMove(msgId, null);
                break;
            case "????????????":
                cmd = cmdParam.toString();
        }
        return cmd;
    }
}
