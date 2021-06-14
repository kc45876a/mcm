package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.config.Config;

    public class Device
            extends CommonEntity
    {
        private String title;
        private String desc;
        private List<String> tags;
        private String protocol;
    private JSONObject location;
    private String imsi;
    private Boolean obsv;
    private JSONObject other;

    public Device(String title, String imei, String imsi)
    {
        this.title = title;
        this.imei = imei;
        this.imsi = imsi;
        this.protocol = "LWM2M";
    }

    public void setObsv(Boolean obsv)
    {
        this.obsv = obsv;
    }

    public void setOther(JSONObject other)
    {
        this.other = other;
    }

    public void setLocation(JSONObject location)
    {
        this.location = location;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.title);
        if (StringUtils.isNotBlank(this.desc)) {
            jsonObject.put("desc", this.desc);
        }
        if (CollectionUtils.isNotEmpty(this.tags)) {
            jsonObject.put("tags", this.tags);
        }
        jsonObject.put("protocol", this.protocol);
        if (this.location != null) {
            jsonObject.put("location", this.location);
        }
        JSONObject authInfo = new JSONObject();
        authInfo.put(this.imei, this.imsi);
        jsonObject.put("auth_info", authInfo);
        if (this.obsv != null) {
            jsonObject.put("obsv", this.obsv);
        }
        if (this.other != null) {
            jsonObject.put("other", this.other);
        }
        return jsonObject;
    }

    public String toUrl()
    {
        StringBuilder url = new StringBuilder(Config.getDomainName());
        url.append("/devices");
        return url.toString();
    }
}
