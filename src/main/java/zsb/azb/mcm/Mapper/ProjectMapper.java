package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zsb.azb.mcm.Model.ProjectEntity;

public abstract interface ProjectMapper
{
    @Select({"SELECT project_id,project_name,project_app,project_key,project_type FROM view_auth_user_project where user_id = #{userId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="projectId", column="project_id"), @org.apache.ibatis.annotations.Result(property="projectName", column="project_name"), @org.apache.ibatis.annotations.Result(property="projectApp", column="project_app"), @org.apache.ibatis.annotations.Result(property="projectKey", column="project_key"), @org.apache.ibatis.annotations.Result(property="projectType", column="project_type")})
    public abstract List<ProjectEntity> listProjectByUser(@Param("userId") int paramInt);

    @Select({"SELECT project_id,project_name,project_app,project_key,project_type FROM view_auth_user_project where user_id = #{userId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="projectId", column="project_id"), @org.apache.ibatis.annotations.Result(property="projectName", column="project_name"), @org.apache.ibatis.annotations.Result(property="projectApp", column="project_app"), @org.apache.ibatis.annotations.Result(property="projectKey", column="project_key"), @org.apache.ibatis.annotations.Result(property="projectType", column="project_type")})
    public abstract List<ProjectEntity> projectListComplete(@Param("userId") int paramInt);

    @Insert({"INSERT INTO mcm_auth_user_project (user_id,project_id) VALUES (#{userId},#{projectId})"})
    public abstract void insertUserProject(@Param("userId") int paramInt1, @Param("projectId") int paramInt2);

    @Delete({"DELETE FROM mcm_auth_user_project WHERE user_id = #{userId} AND project_id = #{projectId}"})
    public abstract int deleteUserProject(@Param("userId") int paramInt1, @Param("projectId") int paramInt2);
}
