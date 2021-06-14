package zsb.azb.mcm.Controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.JdIotService;
import zsb.azb.mcm.Service.TYService;
import zsb.azb.mcm.Utils.ResponseManager;

@RestController
@EnableAutoConfiguration
public class JdIotController
{
    @Autowired
    JdIotService jdIotService;
    @Autowired
    TYService tyService;
    @Autowired
    ResponseManager responseManager;
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping({"/gettoken"})
    @ResponseBody
    public JSONObject getToken()
    {
        Map<String, Object> bodys = new HashMap();
        bodys.put("username", "znspadmin");
        bodys.put("password", "z1iFvR+ZO73cuHC3kVLDKA==");
        bodys.put("grant_type", "password");
        bodys.put("client_secret", "lBTqrKS0kZixOFXeZ0HRng==");
        bodys.put("client_id", "279AF9B6261A408FA8233D80990B14DG");
        bodys.put("is_encrypt", Boolean.valueOf(true));
        bodys.put("auth_type", "account");
        bodys.put("scope", "app");
        JSONObject result = this.tyService.getToken(bodys);
        return result;
    }

    @PostMapping({"/data/forward/events"})
    @ResponseBody
    public ObjectNode forwardEvents(@RequestBody JSONObject body)
            throws Exception
    {
        System.out.println(body);
        JSONObject payload = body.getJSONObject("payload");
        JSONObject eventPayload = payload.getJSONObject("eventPayload");
        String type = eventPayload.getString("Type");
        String value = eventPayload.getString("Value");
        String deviceId = payload.getString("deviceId");
        String eventName = payload.getString("eventName");
        if (eventName.equals("Alarm"))
        {
            this.jdIotService.forwardEvents(deviceId, type, value, body);
        }
        else
        {
            System.out.println("收到CmdRsp");
            this.jdIotService.forwardCommand(deviceId, value);
        }
        return buildSuccess(body.get("id").toString());
    }

    @PostMapping({"/data/forward/properties"})
    @ResponseBody
    public ObjectNode forwardProperties(@RequestBody JSONObject body)
            throws Exception
    {
        System.out.println(body);
        this.jdIotService.forwardProperties(body);
        return buildSuccess(body.get("id").toString());
    }

    @PostMapping({"/device/register"})
    @ResponseBody
    public ResponsePojo deviceRegister(@RequestBody JSONObject body)
    {
        System.out.println(body);

        return this.jdIotService.deviceRegister(body
                .getString("deviceName"), body
                .getString("description"), body
                .getString("productId"), body
                .getString("imei"), body
                .getJSONArray("quyu").getString(1), body
                .getJSONArray("quyu").getString(0), body
                .getString("longitude"), body
                .getString("latitude"), body
                .getString("contacts"));
    }

    @PutMapping({"/device/update"})
    @ResponseBody
    public JSONObject deviceUpdate(@RequestBody Map<String, String> body)
    {
        return this.jdIotService.deviceUpdate(
                (String)body.get("deviceId"),
                (String)body.get("deviceName"),
                (String)body.get("imei"),
                (String)body.get("description"),
                (String)body.get("productId"),
                (String)body.get("profileName"),
                (String)body.get("profileValue"));
    }

    @DeleteMapping({"/device/delete"})
    @ResponseBody
    public JSONObject deviceDelete(@RequestParam String deviceId)
    {
        return this.jdIotService.deviceDelete(deviceId);
    }

    @GetMapping({"/device/info"})
    @ResponseBody
    public JSONObject deviceInfo(@RequestParam String deviceId)
    {
        return this.jdIotService.deviceInfo(deviceId);
    }

    @GetMapping({"/device/list"})
    @ResponseBody
    public JSONObject deviceList(@RequestParam int pageNo, int pageSize, String productId)
    {
        return this.jdIotService.deviceList(pageNo, pageSize, productId);
    }

    @GetMapping({"/thing/list"})
    @ResponseBody
    public JSONObject thingList(@RequestBody Map<String, String> body)
    {
        return this.jdIotService.thingList((String)body.get("productId"));
    }

    @PostMapping({"/device/service/invoke"})
    @ResponseBody
    public JSONObject serviceInvoke(@RequestBody Map<String, String> body)
    {
        return this.jdIotService.serviceInvoke((String)body.get("deviceId"), (String)body.get("service"), (String)body.get("controlCmd"));
    }

    private ObjectNode buildSuccess(String id)
    {
        ObjectNode params = this.mapper.createObjectNode();
        params.put("id", id);
        params.put("code", 200);
        params.put("message", "success");
        params.put("timestamp", System.currentTimeMillis());
        return params;
    }
}