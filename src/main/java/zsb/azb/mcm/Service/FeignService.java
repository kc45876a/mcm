package zsb.azb.mcm.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="http://172.22.244.24:8083/api", name="engine")
public abstract interface FeignService
{
    @PostMapping({"/nbiot/execute/offline"})
    public abstract JSONObject postOneNetCommand(@RequestHeader(name="api-key") String paramString1, @RequestParam("imei") String paramString2, @RequestParam("obj_id") int paramInt1, @RequestParam("obj_inst_id") int paramInt2, @RequestParam("res_id") int paramInt3, @RequestParam("expired_time") String paramString3, @RequestBody Map<String, Object> paramMap);

    @GetMapping({"/devices"})
    public abstract JSONObject getDeviceListByPage(@RequestHeader(name="api-key") String paramString, @RequestParam("page") int paramInt1, @RequestParam("per_page") int paramInt2);

    @PostMapping({"/device/register"})
    public abstract JSONObject deviceRegister(@RequestBody ObjectNode paramObjectNode);

    @PostMapping({"/device/update"})
    public abstract JSONObject deviceUpdate(@RequestBody ObjectNode paramObjectNode);

    @PostMapping({"/device/delete"})
    public abstract JSONObject deviceDelete(@RequestBody ObjectNode paramObjectNode);

    @PostMapping({"/device/get"})
    public abstract JSONObject deviceInfo(@RequestBody ObjectNode paramObjectNode);

    @PostMapping({"/device/list"})
    public abstract JSONObject deviceList(@RequestBody ObjectNode paramObjectNode);

    @PostMapping({"/thing/get"})
    public abstract JSONObject thingList(@RequestBody ObjectNode paramObjectNode);

    @PostMapping({"/device/service/invoke"})
    public abstract JSONObject serviceInvoke(@RequestBody ObjectNode paramObjectNode);
}
