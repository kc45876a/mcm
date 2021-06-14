package zsb.azb.mcm.Controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.RoleService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/role"})
public class RoleController
{
    @Autowired
    private RoleService roleService;

    @GetMapping({"/list"})
    @ResponseBody
    public ResponsePojo roleList()
    {
        ResponsePojo responsePojo = this.roleService.roleList();
        return responsePojo;
    }

    @GetMapping({"/simple"})
    @ResponseBody
    public ResponsePojo roleSimpleList()
    {
        ResponsePojo responsePojo = this.roleService.roleSimpleList();
        return responsePojo;
    }

    @PostMapping({"/create"})
    @ResponseBody
    public ResponsePojo createRole(@RequestBody Map<String, Object> data)
    {
        String roleName = data.get("roleName").toString();
        String roleCode = data.get("roleCode").toString();
        String roleExplain = data.get("roleExplain").toString();
        ResponsePojo responsePojo = this.roleService.createRole(roleName, roleCode, roleExplain);
        return responsePojo;
    }
}
