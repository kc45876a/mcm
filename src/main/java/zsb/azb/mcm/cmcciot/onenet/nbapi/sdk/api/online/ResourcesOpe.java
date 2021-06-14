package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online;

import okhttp3.Callback;
import org.json.JSONObject;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.CommonEntity;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;

public class ResourcesOpe
        extends BasicOpe
{
    public ResourcesOpe(String apiKey)
    {
        super(apiKey);
    }

    public JSONObject operation(CommonEntity commonEntity, JSONObject body)
    {
        return HttpSendCenter.get(this.apiKey, commonEntity.toUrl());
    }

    public void operation(CommonEntity commonEntity, JSONObject body, Callback callback)
    {
        HttpSendCenter.getAsync(this.apiKey, commonEntity.toString(), callback);
    }
}
