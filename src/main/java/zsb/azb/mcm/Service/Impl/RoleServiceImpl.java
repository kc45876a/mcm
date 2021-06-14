package zsb.azb.mcm.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.RoleMapper;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.RoleEntity;
import zsb.azb.mcm.Service.RoleService;
import zsb.azb.mcm.Utils.ResponseManager;

@Service
public class RoleServiceImpl
        implements RoleService
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private RoleMapper roleMapper;

    public ResponsePojo roleList()
    {
        List<RoleEntity> rolelist = this.roleMapper.listRoleExceptAdmin();
        return ResponseManager.buildSuccess(rolelist);
    }

    public ResponsePojo roleSimpleList()
    {
        List<RoleEntity> rolelist = this.roleMapper.listSimpleRole();
        return ResponseManager.buildSuccess(rolelist);
    }

    public ResponsePojo createRole(String roleName, String roleCode, String roleExplain)
    {
        try
        {
            this.roleMapper.insertRole(roleName, roleCode, roleExplain);
            return ResponseManager.buildSuccess();
        }
        catch (Exception ex)
        {
            return ResponseManager.buildError(ex.toString());
        }
    }
}
