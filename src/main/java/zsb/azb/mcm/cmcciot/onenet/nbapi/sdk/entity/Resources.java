package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

public class Resources
        extends CommonEntity
{
    public Resources(String imei)
    {
        this.imei = imei;
    }

    public void setObjId(Integer objId)
    {
        this.objId = objId;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/nbiot/resources");
        url.append("?imei=").append(this.imei);
        if (this.objId != null) {
            url.append("&obj_id=").append(this.objId);
        }
        return url.toString();
    }
}
