package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online;

import java.io.PrintStream;
import okhttp3.Callback;
import org.json.JSONObject;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.CommonEntity;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.utils.HttpSendCenter;

public class DeleteDeviceOpe
        extends BasicOpe
{
    public DeleteDeviceOpe(String apiKey)
    {
        super(apiKey);
    }

    public JSONObject operation(CommonEntity commonEntity, JSONObject body)
    {
        JSONObject jsonObject = HttpSendCenter.delete(this.apiKey, commonEntity.toUrl());
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    public void operation(CommonEntity commonEntity, JSONObject body, Callback callback)
    {
        HttpSendCenter.deleteAsync(this.apiKey, commonEntity.toUrl(), callback);
    }
}
