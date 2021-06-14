package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zsb.azb.mcm.Model.BatchTaskEntity;

public abstract interface BatchMapper
{
    @Select({"SELECT * FROM mcm_batch_task where task_type = #{taskType}"})
    @Results({@org.apache.ibatis.annotations.Result(property="taskId", column="task_id"), @org.apache.ibatis.annotations.Result(property="taskName", column="task_name"), @org.apache.ibatis.annotations.Result(property="userName", column="user_name"), @org.apache.ibatis.annotations.Result(property="createAt", column="create_at"), @org.apache.ibatis.annotations.Result(property="setCount", column="set_count"), @org.apache.ibatis.annotations.Result(property="completionRate", column="completion_rate"), @org.apache.ibatis.annotations.Result(property="completionAt", column="completion_at"), @org.apache.ibatis.annotations.Result(property="taskType", column="task_type")})
    public abstract List<BatchTaskEntity> batchTaskList(@Param("taskType") String paramString);

    @Insert({"INSERT INTO mcm_batch_task (task_name,user_name,create_at,set_count,total_count,completion_rate,completion_at,task_type) VALUES ('${taskName}','${userName}','${createAt}','${setCount}','${totalCount}','${completionRate}','${completionAt}','${taskType}')"})
    public abstract void insertBatchTask(@Param("taskName") String paramString1, @Param("userName") String paramString2, @Param("createAt") String paramString3, @Param("setCount") int paramInt1, @Param("totalCount") int paramInt2, @Param("completionRate") int paramInt3, @Param("completionAt") String paramString4, @Param("taskType") String paramString5);
}
