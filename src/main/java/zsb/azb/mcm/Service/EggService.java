package zsb.azb.mcm.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="http://127.0.0.1:7001", name="egg")
public abstract interface EggService
{
    @PostMapping({"/pushTp"})
    public abstract String pushTp(@RequestBody JSONObject paramJSONObject);
}
