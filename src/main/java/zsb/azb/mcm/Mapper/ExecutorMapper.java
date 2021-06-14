package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zsb.azb.mcm.Model.DeviceData;

public abstract interface ExecutorMapper
{
    @Select({"SELECT project_key FROM view_manhole_info WHERE imei = #{imei}"})
    @Results({@org.apache.ibatis.annotations.Result(property="project_key", column="project_key", javaType=String.class)})
    public abstract String getProjectKey(@Param("imei") String paramString);

    @Select({"SELECT device_id FROM mcm_manhole_info WHERE imei = #{imei}"})
    @Results({@org.apache.ibatis.annotations.Result(property="deviceId", column="device_id", javaType=String.class)})
    public abstract String getDeviceId(@Param("imei") String paramString);

    @Insert({"<script>INSERT INTO temp_manhole (imei,name,device_id) VALUES <foreach collection='devices' item='device' index='index' separator=','>(#{device.imei},#{device.name},#{device.deviceId})</foreach></script>"})
    public abstract void insertManhole(@Param("devices") List<DeviceData> paramList);
}
