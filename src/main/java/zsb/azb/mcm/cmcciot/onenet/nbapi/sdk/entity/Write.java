package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

public class Write
        extends CommonEntity
{
    private Integer mode;

    public Write(String imei, Integer objId, Integer objInstId, Integer mode)
    {
        this.imei = imei;
        this.objId = objId;
        this.objInstId = objInstId;
        this.mode = mode;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/nbiot?imei=").append(this.imei);
        url.append("&obj_id=").append(this.objId);
        url.append("&obj_inst_id=").append(this.objInstId);
        url.append("&mode=").append(this.mode);
        return url.toString();
    }
}
