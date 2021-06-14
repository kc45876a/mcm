package zsb.azb.mcm.Service.Impl;

import com.alibaba.fastjson.JSON;
import java.io.PrintStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.MenuMapper;
import zsb.azb.mcm.Model.MenuEntity;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.TokenPojo;
import zsb.azb.mcm.Service.MenuService;
import zsb.azb.mcm.Utils.ResponseManager;

@Service
public class MenuServiceImpl
        implements MenuService
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private TokenPojo tokenPojo;

    public ResponsePojo frameMenu()
    {
        List<MenuEntity> menuList = this.menuMapper.listMenuByRoleId(this.tokenPojo.getRoleId());
        return ResponseManager.buildSuccess(menuList);
    }

    public ResponsePojo completeMenu()
    {
        List<MenuEntity> menuList = this.menuMapper.listMenuByRoleId(1);
        return ResponseManager.buildSuccess(menuList);
    }

    public ResponsePojo roleMenu(int roleId)
    {
        List<MenuEntity> menuList = this.menuMapper.listMenuByRoleId(roleId);
        return ResponseManager.buildSuccess(menuList);
    }

    public ResponsePojo createRoleMenu(int roleId, int menuId)
    {
        System.out.println("333");
        try
        {
            System.out.println(roleId + "-" + menuId);
            this.menuMapper.insertRoleMenu(roleId, menuId, roleId + "," + menuId);
            System.out.println("444");
            return ResponseManager.buildSuccess("保存成功");
        }
        catch (Exception ex) {}
        return ResponseManager.buildError(12001, "新增的资源已存在");
    }

    public ResponsePojo createRoleMenus(int roleId, String menusStr)
    {
        System.out.println("999");
        System.out.println(menusStr);
        List menus = (List)JSON.parse(menusStr);
        System.out.println(menus);
        try
        {
            this.menuMapper.insertRoleMenus(roleId, menus);
            System.out.println("1010");
            return ResponseManager.buildSuccess("保存成功");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildError(12001, "新增的资源已存在");
    }

    public ResponsePojo deleteRoleMenu(String roleMenu)
    {
        System.out.println("555");
        System.out.println(roleMenu);
        try
        {
            if (this.menuMapper.deleteRoleMenu(roleMenu) > 0)
            {
                System.out.println("666");
                return ResponseManager.buildSuccess("删除成功");
            }
            return ResponseManager.buildError(12002, "删除的资源不存在");
        }
        catch (Exception ex) {}
        return ResponseManager.buildError(12002, "删除的资源不存在");
    }

    public ResponsePojo deleteRoleMenus(int roleId)
    {
        try
        {
            if (this.menuMapper.deleteRoleMenus(roleId) > 0)
            {
                System.out.println("666");
                return ResponseManager.buildSuccess("删除成功");
            }
            return ResponseManager.buildError(12002, "删除的资源不存在");
        }
        catch (Exception ex) {}
        return ResponseManager.buildError(12002, "删除的资源不存在");
    }
}
