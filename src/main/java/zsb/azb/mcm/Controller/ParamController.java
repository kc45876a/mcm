package zsb.azb.mcm.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.ParamService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/param"})
public class ParamController
{
    @Autowired
    private ParamService paramService;

    @GetMapping({"/list"})
    @ResponseBody
    public ResponsePojo listParam()
    {
        ResponsePojo responsePojo = this.paramService.listParam();
        return responsePojo;
    }

    @PutMapping({"/modify"})
    @ResponseBody
    public ResponsePojo modifyParam(@RequestBody Map<String, String> paramInfo)
    {
        ResponsePojo responsePojo = this.paramService.modifyParam(paramInfo);
        return responsePojo;
    }
}
