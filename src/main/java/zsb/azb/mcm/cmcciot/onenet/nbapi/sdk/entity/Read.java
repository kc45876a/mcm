package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

public class Read
        extends CommonEntity
{
    public void setObjInstId(Integer objInstId)
    {
        this.objInstId = objInstId;
    }

    public void setResId(Integer resId)
    {
        this.resId = resId;
    }

    public Read(String imei, Integer objId)
    {
        this.imei = imei;
        this.objId = objId;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/nbiot?imei=").append(this.imei);
        url.append("&obj_id=").append(this.objId);
        if (this.objInstId != null) {
            url.append("&obj_inst_id=").append(this.objInstId);
        }
        if (this.resId != null) {
            url.append("&res_id=").append(this.resId);
        }
        return url.toString();
    }
}
