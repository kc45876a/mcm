package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

public class Execute
        extends CommonEntity
{
    public Execute(String imei, Integer objId, Integer objInstId, Integer resId)
    {
        this.imei = imei;
        this.objId = objId;
        this.objInstId = objInstId;
        this.resId = resId;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/nbiot/execute?imei=").append(this.imei);
        url.append("&obj_id=").append(this.objId);
        url.append("&obj_inst_id=").append(this.objInstId);
        url.append("&res_id=").append(this.resId);
        return url.toString();
    }
}
