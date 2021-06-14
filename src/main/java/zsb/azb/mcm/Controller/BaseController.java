package zsb.azb.mcm.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.BaseService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/base"})
public class BaseController
{
    @Autowired
    private BaseService baseService;

    @GetMapping({"/road"})
    @ResponseBody
    public ResponsePojo listRoad()
    {
        return this.baseService.listRoad();
    }

    @GetMapping({"/district"})
    @ResponseBody
    public ResponsePojo listDistrict()
    {
        return this.baseService.listDistrict();
    }
}
