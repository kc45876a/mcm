package zsb.azb.mcm.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.MenuService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/menu"})
public class MenuController
{
    @Autowired
    private MenuService menuService;

    @GetMapping({"/frame"})
    @ResponseBody
    public ResponsePojo frameMenu()
    {
        ResponsePojo responsePojo = this.menuService.frameMenu();
        return responsePojo;
    }

    @GetMapping({"/complete"})
    @ResponseBody
    public ResponsePojo completeMenu()
    {
        ResponsePojo responsePojo = this.menuService.completeMenu();
        return responsePojo;
    }

    @GetMapping({"/role"})
    @ResponseBody
    public ResponsePojo roleMenu(@RequestParam("roleId") int roleId)
    {
        ResponsePojo responsePojo = this.menuService.roleMenu(roleId);
        return responsePojo;
    }

    @PostMapping({"/auth"})
    @ResponseBody
    public ResponsePojo createRoleMenu(@RequestBody Map<String, Integer> data)
    {
        ResponsePojo responsePojo = this.menuService.createRoleMenu(((Integer)data.get("roleId")).intValue(), ((Integer)data.get("menuId")).intValue());
        return responsePojo;
    }

    @PostMapping({"/auths"})
    @ResponseBody
    public ResponsePojo createRoleMenus(@RequestBody Map<String, Object> data)
    {
        ResponsePojo responsePojo = this.menuService.createRoleMenus(((Integer)data.get("roleId")).intValue(), data.get("menuIds").toString());
        return responsePojo;
    }

    @DeleteMapping({"/auth"})
    @ResponseBody
    public ResponsePojo deleteRoleMenu(@RequestParam("roleId") int roleId, @RequestParam("menuId") int menuId)
    {
        ResponsePojo responsePojo = this.menuService.deleteRoleMenu(roleId + "," + menuId);
        return responsePojo;
    }

    @DeleteMapping({"/auths"})
    @ResponseBody
    public ResponsePojo deleteRoleMenus(@RequestParam("roleId") int roleId)
    {
        ResponsePojo responsePojo = this.menuService.deleteRoleMenus(roleId);
        return responsePojo;
    }
}
