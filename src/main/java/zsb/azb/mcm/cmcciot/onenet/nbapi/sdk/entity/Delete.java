package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

public class Delete
        extends CommonEntity
{
    protected String deviceId;

    public String getDeviceId()
    {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public Delete(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/devices/");
        url.append(this.deviceId);
        return url.toString();
    }
}
