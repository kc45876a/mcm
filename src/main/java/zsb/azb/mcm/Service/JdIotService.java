package zsb.azb.mcm.Service;

import com.alibaba.fastjson.JSONObject;
import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface JdIotService
{
    public abstract ResponsePojo deviceRegister(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9);

    public abstract JSONObject deviceUpdate(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7);

    public abstract JSONObject deviceDelete(String paramString);

    public abstract JSONObject deviceInfo(String paramString);

    public abstract JSONObject deviceList(int paramInt1, int paramInt2, String paramString);

    public abstract JSONObject thingList(String paramString);

    public abstract JSONObject serviceInvoke(String paramString1, String paramString2, String paramString3);

    public abstract void forwardEvents(String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject)
            throws Exception;

    public abstract void forwardCommand(String paramString1, String paramString2);

    public abstract void forwardProperties(JSONObject paramJSONObject)
            throws Exception;
}
