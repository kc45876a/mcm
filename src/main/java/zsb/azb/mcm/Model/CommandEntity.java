package zsb.azb.mcm.Model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommandEntity
{
    private Long cmdId;
    private String imei;
    private String createTime;
    private String userName;
    private String cmdType;
    private String cmdParam;
    private String cmdString;
    private String cmdStatus;
    private String cmdResult;
    private String confirmStatus;
    private String confirmTime;
    private String appId;

    public String getCmdString()
    {
        return this.cmdString;
    }

    public void setCmdString(String cmdString)
    {
        this.cmdString = cmdString;
    }

    public String getAppId()
    {
        return this.appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getImei()
    {
        return this.imei;
    }

    public void setImei(String imei)
    {
        this.imei = imei;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getCmdType()
    {
        return this.cmdType;
    }

    public void setCmdType(String cmdType)
    {
        this.cmdType = cmdType;
    }

    public String getCmdParam()
    {
        return this.cmdParam;
    }

    public void setCmdParam(String cmdParam)
    {
        this.cmdParam = cmdParam;
    }

    public String getCmdStatus()
    {
        return this.cmdStatus;
    }

    public void setCmdStatus(String cmdStatus)
    {
        this.cmdStatus = cmdStatus;
    }

    public String getConfirmTime()
    {
        return this.confirmTime;
    }

    public void setConfirmTime(String confirmTime)
    {
        this.confirmTime = confirmTime;
    }

    public Long getCmdId()
    {
        return this.cmdId;
    }

    public void setCmdId(Long cmdId)
    {
        this.cmdId = cmdId;
    }

    public String getCmdResult()
    {
        return this.cmdResult;
    }

    public void setCmdResult(String cmdResult)
    {
        this.cmdResult = cmdResult;
    }

    public String getConfirmStatus()
    {
        return this.confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus)
    {
        this.confirmStatus = confirmStatus;
    }
}
