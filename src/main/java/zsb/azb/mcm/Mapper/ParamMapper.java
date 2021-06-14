package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zsb.azb.mcm.Model.ParamEntity;

public abstract interface ParamMapper
{
    @Select({"SELECT * FROM mcm_system_param"})
    @Results({@org.apache.ibatis.annotations.Result(property="id", column="id"), @org.apache.ibatis.annotations.Result(property="paramName", column="param_name"), @org.apache.ibatis.annotations.Result(property="paramValue", column="param_value"), @org.apache.ibatis.annotations.Result(property="paramExplain", column="param_explain")})
    public abstract List<ParamEntity> listParam();

    @Update({"UPDATE mcm_system_param SET param_value = #{paramValue} WHERE param_name =#{paramName}"})
    public abstract void modifyParam(@Param("paramName") String paramString1, @Param("paramValue") String paramString2);
}
