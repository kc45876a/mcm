package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

public class Observe
        extends CommonEntity
{
    private Boolean cancel;
    private Integer pmax;
    private Integer pmin;
    private Double gt;
    private Double lt;
    private Double st;

    public Observe(String imei, Integer objId, Boolean cancel)
    {
        this.imei = imei;
        this.objId = objId;
        this.cancel = cancel;
    }

    public void setObjInstId(Integer objInstId)
    {
        this.objInstId = objInstId;
    }

    public void setResId(Integer resId)
    {
        this.resId = resId;
    }

    public void setPmax(Integer pmax)
    {
        this.pmax = pmax;
    }

    public void setPmin(Integer pmin)
    {
        this.pmin = pmin;
    }

    public void setGt(Double gt)
    {
        this.gt = gt;
    }

    public void setLt(Double lt)
    {
        this.lt = lt;
    }

    public void setSt(Double st)
    {
        this.st = st;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/nbiot/observe?imei=").append(this.imei);
        url.append("&obj_id=").append(this.objId);
        url.append("&cancel=").append(this.cancel);
        if (this.objInstId != null) {
            url.append("&obj_inst_id=").append(this.objInstId);
        }
        if (this.resId != null) {
            url.append("&res_id=").append(this.resId);
        }
        if (this.pmax != null) {
            url.append("&pmax=").append(this.pmax);
        }
        if (this.pmin != null) {
            url.append("&pmin=").append(this.pmin);
        }
        if (this.gt != null) {
            url.append("&gt=").append(this.gt);
        }
        if (this.lt != null) {
            url.append("&lt=").append(this.lt);
        }
        if (this.st != null) {
            url.append("&st=").append(this.st);
        }
        return url.toString();
    }
}
