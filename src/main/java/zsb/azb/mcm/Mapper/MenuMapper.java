package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zsb.azb.mcm.Model.MenuEntity;

public abstract interface MenuMapper
{
    @Select({"SELECT menu_id,menu_name,menu_parent_id,menu_parent_name,menu_path,menu_icon,menu_index FROM view_auth_role_menu WHERE role_id = #{roleId} ORDER BY menu_index "})
    @Results({@org.apache.ibatis.annotations.Result(property="menuId", column="menu_id"), @org.apache.ibatis.annotations.Result(property="menuName", column="menu_name"), @org.apache.ibatis.annotations.Result(property="menuParentId", column="menu_parent_id"), @org.apache.ibatis.annotations.Result(property="menuParentName", column="menu_parent_name"), @org.apache.ibatis.annotations.Result(property="menuPath", column="menu_path"), @org.apache.ibatis.annotations.Result(property="menuIcon", column="menu_icon"), @org.apache.ibatis.annotations.Result(property="menuIndex", column="menu_index")})
    public abstract List<MenuEntity> listMenuByRoleId(@Param("roleId") int paramInt);

    @Insert({"INSERT INTO mcm_auth_role_menu (role_id,menu_id,role_menu) VALUES (#{roleId},#{menuId},#{roleMenu})"})
    public abstract void insertRoleMenu(@Param("roleId") int paramInt1, @Param("menuId") int paramInt2, @Param("roleMenu") String paramString);

    @Insert({"<script>INSERT INTO mcm_auth_role_menu (role_id,menu_id,role_menu) VALUES <foreach collection=\"menus\" item=\"menu\" index=\"index\" separator=\",\" >(#{roleId},#{menu.menuId},CONCAT(CONCAT(#{roleId},','),#{menu.menuId}))</foreach></script>"})
    public abstract void insertRoleMenus(@Param("roleId") int paramInt, @Param("menus") List paramList);

    @Delete({"DELETE FROM mcm_auth_role_menu WHERE role_menu = #{roleMenu}"})
    public abstract int deleteRoleMenu(@Param("roleMenu") String paramString);

    @Delete({"DELETE FROM mcm_auth_role_menu WHERE role_id = #{roleId}"})
    public abstract int deleteRoleMenus(@Param("roleId") int paramInt);
}
