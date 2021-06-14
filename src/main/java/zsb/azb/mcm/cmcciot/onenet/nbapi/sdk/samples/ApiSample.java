package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.samples;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.CreateDeviceOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.ExecuteOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.ObserveOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.ReadOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.ResourcesOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.WriteOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Device;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Execute;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Observe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Read;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Resources;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Write;

public class ApiSample
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiSample.class);

    public static void main(String[] args)
    {
        String apiKey = "***********************";
        String imei = "*******";
        Integer objId = Integer.valueOf(3200);
        Integer objInstId = Integer.valueOf(0);
        Integer readResId = Integer.valueOf(5500);
        Integer executeResId = Integer.valueOf(5501);
        Integer writeResId = Integer.valueOf(5750);
        Integer writeMode = Integer.valueOf(2);

        CreateDeviceOpe deviceOpe = new CreateDeviceOpe(apiKey);
        Device device = new Device("samples", "imeitest", "320023320");
        LOGGER.info(deviceOpe.operation(device, device.toJsonObject()).toString());

        ReadOpe readOperation = new ReadOpe(apiKey);
        Read read = new Read(imei, objId);
        read.setObjInstId(objInstId);
        read.setResId(readResId);
        LOGGER.info(readOperation.operation(read, null).toString());

        WriteOpe writeOpe = new WriteOpe(apiKey);
        Write write = new Write(imei, objId, objInstId, writeMode);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res_id", writeResId);
        jsonObject.put("val", "data1");
        jsonArray.put(jsonObject);
        JSONObject data = new JSONObject();
        data.put("data", jsonArray);
        LOGGER.info(writeOpe.operation(write, data).toString());

        ExecuteOpe executeOpe = new ExecuteOpe(apiKey);
        Execute execute = new Execute(imei, objId, objInstId, executeResId);

        JSONObject body = new JSONObject();
        body.put("args", "ping");
        LOGGER.info(executeOpe.operation(execute, body).toString());

        ResourcesOpe resourcesOpe = new ResourcesOpe(apiKey);
        Resources resources = new Resources(imei);
        LOGGER.info(resourcesOpe.operation(resources, null).toString());

        ObserveOpe observeOpe = new ObserveOpe(apiKey);
        Observe observe = new Observe(imei, objId, Boolean.valueOf(false));
        LOGGER.info(observeOpe.operation(observe, null).toString());
    }
}
