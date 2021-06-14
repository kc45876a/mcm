package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zsb.azb.mcm.Model.UserEntity;

public abstract interface UserMapper
{
    @Select({"SELECT user_name,password FROM user_info"})
    @Results({@org.apache.ibatis.annotations.Result(property="user_name", column="user_name"), @org.apache.ibatis.annotations.Result(property="password", column="password")})
    public abstract List<UserEntity> getAll();

    @Select({"SELECT count(0) as count FROM sys_user_info where login_name = #{loginName} and is_using = '启用'"})
    //@Results({@org.apache.ibatis.annotations.Result(property="count", column="count")})
    int countUserByLoginName(@Param("loginName") String paramString);

    @Select({"SELECT user_id, user_name,role_id FROM sys_user_info where login_name = #{loginName} and password = #{password}"})
    @Results({@org.apache.ibatis.annotations.Result(property="userId", column="user_id"), @org.apache.ibatis.annotations.Result(property="userName", column="user_name"), @org.apache.ibatis.annotations.Result(property="systemId", column="system_id"), @org.apache.ibatis.annotations.Result(property="roleId", column="role_id")})
    public abstract UserEntity getUser(@Param("loginName") String paramString1, @Param("password") String paramString2);

    @Select({"<script>SELECT user_id,user_name,login_name,password,role_name,tel,email,login_at,is_using FROM view_user_info<where><when test=\"searchKey != null\">AND user_name LIKE CONCAT('%',#{searchKey},'%')</when></where></script>"})
    @Results({@org.apache.ibatis.annotations.Result(property="userId", column="user_id"), @org.apache.ibatis.annotations.Result(property="userName", column="user_name"), @org.apache.ibatis.annotations.Result(property="loginName", column="login_name"), @org.apache.ibatis.annotations.Result(property="password", column="password"), @org.apache.ibatis.annotations.Result(property="roleName", column="role_name"), @org.apache.ibatis.annotations.Result(property="tel", column="tel"), @org.apache.ibatis.annotations.Result(property="email", column="email"), @org.apache.ibatis.annotations.Result(property="loginAt", column="login_at"), @org.apache.ibatis.annotations.Result(property="isUsing", column="is_using")})
    public abstract List<UserEntity> listUser(@Param("searchKey") String paramString);

    @Select({"SELECT role_id FROM sys_role_info WHERE role_name = #{roleName}"})
    @Results({@org.apache.ibatis.annotations.Result(property="roleId", column="role_id")})
    public abstract int getRoleIdByRoleName(@Param("roleName") String paramString);

    @Update({"UPDATE sys_user_info SET login_name = #{loginName},role_id=#{roleId},role_name = #{roleName},dept_id = #{deptId},password = #{password},email=#{email},tel=#{tel},is_using=#{isUsing} WHERE user_id =#{userId}"})
    public abstract void updateUser(UserEntity paramUserEntity);

    @Insert({"INSERT INTO sys_user_info (user_name,login_name,password,role_id,tel,email) VALUES (#{userName},#{loginName},#{password},#{roleId},#{tel},#{email})"})
    public abstract void insertUser(UserEntity paramUserEntity);

    @Update({"UPDATE sys_user_info SET access_token = #{accessToken},login_at = now() WHERE user_id =#{userId}"})
    public abstract void updateAccessToken(UserEntity paramUserEntity);
}
