package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zsb.azb.mcm.Model.RoleEntity;

public abstract interface RoleMapper
{
    @Select({"SELECT role_id,role_code,role_name,role_explain,role_index FROM sys_role_info WHERE role_id > 1"})
    @Results({@org.apache.ibatis.annotations.Result(property="roleId", column="role_id"), @org.apache.ibatis.annotations.Result(property="roleCode", column="role_code"), @org.apache.ibatis.annotations.Result(property="roleName", column="role_name"), @org.apache.ibatis.annotations.Result(property="roleExplain", column="role_explain"), @org.apache.ibatis.annotations.Result(property="roleIndex", column="role_index")})
    public abstract List<RoleEntity> listRoleExceptAdmin();

    @Select({"SELECT role_id,role_name FROM sys_role_info WHERE role_id > 1 ORDER BY role_index ASC"})
    @Results({@org.apache.ibatis.annotations.Result(property="roleId", column="role_id"), @org.apache.ibatis.annotations.Result(property="roleName", column="role_name")})
    public abstract List<RoleEntity> listSimpleRole();

    @Insert({"INSERT INTO sys_role_info (role_name,role_code,role_explain) VALUES (#{roleName},#{roleCode},#{roleExplain})"})
    public abstract void insertRole(@Param("roleName") String paramString1, @Param("roleCode") String paramString2, @Param("roleExplain") String paramString3);
}
