package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity;

import org.json.JSONObject;

public abstract class CommonEntity
{
    protected String imei;
    protected Integer objId;
    protected Integer objInstId;
    protected Integer resId;

    public JSONObject toJsonObject()
    {
        return null;
    }

    public abstract String toUrl();
}
