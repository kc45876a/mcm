package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online;

import okhttp3.Callback;
import org.json.JSONObject;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.CommonEntity;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;

public class CreateDeviceOpe
        extends BasicOpe
{
    public CreateDeviceOpe(String apiKey)
    {
        super(apiKey);
    }

    public JSONObject operation(CommonEntity commonEntity, JSONObject body)
    {
        return HttpSendCenter.post(this.apiKey, commonEntity.toUrl(), body);
    }

    public void operation(CommonEntity commonEntity, JSONObject body, Callback callback)
    {
        HttpSendCenter.postAsync(this.apiKey, commonEntity.toUrl(), body, callback);
    }
}
