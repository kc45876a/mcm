package zsb.azb.mcm.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import zsb.azb.mcm.Config.FeignClientFormPostConfig;

import java.util.Map;

@FeignClient(url="${ts.base-url}", name="ts", configuration={FeignClientFormPostConfig.class})
public interface TSService
{
    @PostMapping({"ts.url"})
     JSONObject pushTS(
            @RequestHeader(name="c-topic")String topic,
            @RequestHeader(name="c-preId")String preId,
            @RequestHeader(name="c-signature")String signature,
            @RequestBody String body);
}
