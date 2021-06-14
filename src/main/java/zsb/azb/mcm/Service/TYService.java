package zsb.azb.mcm.Service;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import zsb.azb.mcm.Config.FeignClientFormPostConfig;

@FeignClient(url="http://172.22.226.11", name="ty", configuration={FeignClientFormPostConfig.class})
public abstract interface TYService
{
    @PostMapping({"/api/mcmManholeApi/mcmManholeEvent/manholeReport"})
    public abstract JSONObject pushTY(@RequestHeader(name="Authorization") String paramString, @RequestBody JSONObject paramJSONObject);

    @PostMapping(value={"/api/filesApi/files/upload"}, produces={"application/json;charset=UTF-8"}, consumes={"multipart/form-data"})
    public abstract JSONObject pushTP(@RequestHeader(name="Authorization") String paramString, @RequestPart("files") MultipartFile paramMultipartFile);

    @PostMapping(value={"/api/oauthApi/oauth/token"}, consumes={"application/x-www-form-urlencoded"})
    public abstract JSONObject getToken(Map<String, ?> paramMap);
}
