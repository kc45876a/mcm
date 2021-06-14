package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online;

import okhttp3.Callback;
import org.json.JSONObject;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.CommonEntity;

public abstract class BasicOpe
{
    protected String apiKey;

    public BasicOpe(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public abstract JSONObject operation(CommonEntity paramCommonEntity, JSONObject paramJSONObject);

    public abstract void operation(CommonEntity paramCommonEntity, JSONObject paramJSONObject, Callback paramCallback);
}
