package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zsb.azb.mcm.Model.CommandEntity;
import zsb.azb.mcm.Model.JDDataEntity;
import zsb.azb.mcm.Model.JDEventEntity;
import zsb.azb.mcm.Model.JDManholeEntity;
import zsb.azb.mcm.Model.JDManholeStatus;
import zsb.azb.mcm.Model.ManholeRecordEntity;
import zsb.azb.mcm.Model.MapData;

public abstract interface ManholeMapper
{
    @Select({"<script>SELECT count(0) as count FROM mcm_manhole_info WHERE 1=1 <when test='null != searchKey'> AND (device_id LIKE CONCAT('%',#{searchKey},'%') or imei LIKE CONCAT('%',#{searchKey},'%'))</when></script>"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int getCount(@Param("searchKey") String paramString);

    @Select({"<script>SELECT * FROM view_manhole_info WHERE 1=1 <when test='null != searchKey'>  AND (device_id LIKE CONCAT('%',#{searchKey},'%') or imei LIKE CONCAT('%',#{searchKey},'%'))</when> ORDER BY update_at desc,device_id asc LIMIT #{limit}, #{pageSize}</script>"})
    @Results({@org.apache.ibatis.annotations.Result(property="deviceId", column="device_id", javaType=String.class), @org.apache.ibatis.annotations.Result(property="deviceName", column="device_name", javaType=String.class), @org.apache.ibatis.annotations.Result(property="imei", column="imei", javaType=String.class), @org.apache.ibatis.annotations.Result(property="productId", column="product_id", javaType=String.class), @org.apache.ibatis.annotations.Result(property="road", column="road", javaType=String.class), @org.apache.ibatis.annotations.Result(property="district", column="district", javaType=String.class), @org.apache.ibatis.annotations.Result(property="proprietor", column="proprietor", javaType=String.class), @org.apache.ibatis.annotations.Result(property="contacts", column="contacts", javaType=String.class), @org.apache.ibatis.annotations.Result(property="telphone", column="telphone", javaType=String.class), @org.apache.ibatis.annotations.Result(property="longitude", column="longitude", javaType=String.class), @org.apache.ibatis.annotations.Result(property="latitude", column="latitude", javaType=String.class), @org.apache.ibatis.annotations.Result(property="description", column="description", javaType=String.class), @org.apache.ibatis.annotations.Result(property="position", column="position", javaType=String.class), @org.apache.ibatis.annotations.Result(property="createAt", column="create_at", javaType=String.class), @org.apache.ibatis.annotations.Result(property="updateAt", column="update_at", javaType=String.class), @org.apache.ibatis.annotations.Result(property="onlineStatus", column="online_status", javaType=String.class)})
    public abstract List<JDManholeEntity> getManholeByLimit(@Param("limit") Integer paramInteger1, @Param("pageSize") Integer paramInteger2, @Param("searchKey") String paramString);

    @Select({"select * from mcm_manhole_status where device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="deviceId", column="device_id", javaType=String.class), @org.apache.ibatis.annotations.Result(property="iccid", column="iccid", javaType=String.class), @org.apache.ibatis.annotations.Result(property="status", column="status", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="ci", column="ci", javaType=String.class), @org.apache.ibatis.annotations.Result(property="concentration", column="concentration", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="hardwareVer", column="hardware_ver", javaType=String.class), @org.apache.ibatis.annotations.Result(property="bp", column="bp", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="open", column="open", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="bu", column="bu", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="axeSensorSta", column="axe_sensor_sta", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="aat", column="aat", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="t", column="t", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="angle", column="angle", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="hb", column="hb", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="softVer", column="soft_ver", javaType=String.class), @org.apache.ibatis.annotations.Result(property="resetValue", column="reset_value", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="defence", column="defence", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="vibfreq", column="vibfreq", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="wls", column="wls", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="rssi", column="rssi", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="rsrp", column="rsrp", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="snr", column="snr", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="updateAt", column="update_at", javaType=String.class), @org.apache.ibatis.annotations.Result(property="overflowStatus", column="overflow_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="gasStatus", column="gas_status", javaType=String.class)})
    public abstract JDManholeStatus getManholeStatus(@Param("deviceId") String paramString);

    @Select({"SELECT count(0) AS count FROM mcm_manhole_record WHERE imei = #{imei}"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int manholeRecordCount(@Param("imei") String paramString);

    @Select({"SELECT * FROM mcm_manhole_record WHERE imei = #{imei} ORDER BY id DESC LIMIT #{limit}, #{pageSize}"})
    @Results({@org.apache.ibatis.annotations.Result(property="id", column="id", javaType=Long.class), @org.apache.ibatis.annotations.Result(property="imei", column="imei", javaType=String.class), @org.apache.ibatis.annotations.Result(property="dataType", column="data_type", javaType=String.class), @org.apache.ibatis.annotations.Result(property="angle", column="angle", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="rsrp", column="rsrp", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="rsrp", column="rsrp", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="snr", column="snr", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="phycellId", column="phycell_id", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="temp", column="temp", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="vibrate", column="vibrate", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="gas", column="gas", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="gasType", column="gas_type", javaType=String.class), @org.apache.ibatis.annotations.Result(property="voltage", column="voltage", javaType=String.class), @org.apache.ibatis.annotations.Result(property="isRetrans", column="is_retrans", javaType=String.class), @org.apache.ibatis.annotations.Result(property="gasStatus", column="gas_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="dismantleStatus", column="dismantle_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="overflowStatus", column="overflow_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="angleSensorStatus", column="angleSensor_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="tempSensorStatus", column="tempSensor_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="isRecover", column="is_recover", javaType=String.class), @org.apache.ibatis.annotations.Result(property="openStatus", column="open_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="rawData", column="raw_data", javaType=String.class), @org.apache.ibatis.annotations.Result(property="eventTime", column="event_time", javaType=String.class)})
    public abstract List<ManholeRecordEntity> manholeRecordByLimit(@Param("limit") Integer paramInteger1, @Param("pageSize") Integer paramInteger2, @Param("imei") String paramString);

    @Select({"SELECT count(0) AS count FROM mcm_manhole_event WHERE device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int manholeEventCount(@Param("deviceId") String paramString);

    @Select({"SELECT * FROM mcm_manhole_event WHERE device_id = #{deviceId} ORDER BY id DESC LIMIT #{limit}, #{pageSize}"})
    @Results({@org.apache.ibatis.annotations.Result(property="id", column="id", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="deviceId", column="device_id", javaType=String.class), @org.apache.ibatis.annotations.Result(property="eventType", column="event_type", javaType=String.class), @org.apache.ibatis.annotations.Result(property="eventValue", column="event_value", javaType=String.class), @org.apache.ibatis.annotations.Result(property="eventAt", column="event_at", javaType=String.class)})
    public abstract List<JDEventEntity> manholeEventByLimit(@Param("limit") Integer paramInteger1, @Param("pageSize") Integer paramInteger2, @Param("deviceId") String paramString);

    @Select({"SELECT count(0) AS count FROM mcm_manhole_data WHERE device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int manholeDataCount(@Param("deviceId") String paramString);

    @Select({"SELECT * FROM mcm_manhole_data WHERE device_id = #{deviceId} ORDER BY id DESC LIMIT #{limit}, #{pageSize}"})
    @Results({@org.apache.ibatis.annotations.Result(property="id", column="id", javaType=Integer.class), @org.apache.ibatis.annotations.Result(property="deviceId", column="device_id", javaType=String.class), @org.apache.ibatis.annotations.Result(property="imsi", column="imsi", javaType=String.class), @org.apache.ibatis.annotations.Result(property="status", column="status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="defence", column="defence", javaType=String.class), @org.apache.ibatis.annotations.Result(property="open", column="open", javaType=String.class), @org.apache.ibatis.annotations.Result(property="bu", column="bu", javaType=String.class), @org.apache.ibatis.annotations.Result(property="bp", column="bp", javaType=String.class), @org.apache.ibatis.annotations.Result(property="t", column="t", javaType=String.class), @org.apache.ibatis.annotations.Result(property="angle", column="angle", javaType=String.class), @org.apache.ibatis.annotations.Result(property="aat", column="aat", javaType=String.class), @org.apache.ibatis.annotations.Result(property="hb", column="hb", javaType=String.class), @org.apache.ibatis.annotations.Result(property="concentration", column="concentration", javaType=String.class), @org.apache.ibatis.annotations.Result(property="wls", column="wls", javaType=String.class), @org.apache.ibatis.annotations.Result(property="rssi", column="rssi", javaType=String.class), @org.apache.ibatis.annotations.Result(property="rsrp", column="rsrp", javaType=String.class), @org.apache.ibatis.annotations.Result(property="snr", column="snr", javaType=String.class), @org.apache.ibatis.annotations.Result(property="ci", column="ci", javaType=String.class), @org.apache.ibatis.annotations.Result(property="eventAt", column="event_at", javaType=String.class)})
    public abstract List<JDDataEntity> manholeDataByLimit(@Param("limit") Integer paramInteger1, @Param("pageSize") Integer paramInteger2, @Param("deviceId") String paramString);

    @Select({"SELECT project_key FROM view_manhole_info WHERE imei = #{imei}"})
    @Results({@org.apache.ibatis.annotations.Result(property="project_key", column="project_key", javaType=String.class)})
    public abstract String getProjectKey(@Param("imei") String paramString);

    @Select({"SELECT project_type FROM view_manhole_info WHERE imei = #{imei}"})
    @Results({@org.apache.ibatis.annotations.Result(property="projectType", column="project_type", javaType=String.class)})
    public abstract String getProjectType(@Param("imei") String paramString);

    @Insert({"INSERT INTO mcm_manhole_cmd (imei,create_time,user_name,cmd_type,cmd_param,confirm_status) VALUES (#{imei},now(),#{userName},#{cmdType},#{cmdParam},#{confirmStatus})"})
    public abstract void insertCommand(@Param("imei") String paramString1, @Param("userName") String paramString2, @Param("cmdType") String paramString3, @Param("cmdParam") String paramString4, @Param("confirmStatus") String paramString5);

    @Select({"SELECT count(0) AS count FROM mcm_manhole_cmd"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int manholeCommandCount();

    @Select({"<script>SELECT * FROM mcm_manhole_cmd ORDER BY create_time desc,cmd_id desc LIMIT #{limit}, #{pageSize}</script>"})
    @Results({@org.apache.ibatis.annotations.Result(property="cmdId", column="cmd_id", javaType=Long.class), @org.apache.ibatis.annotations.Result(property="imei", column="imei", javaType=String.class), @org.apache.ibatis.annotations.Result(property="createTime", column="create_time", javaType=String.class), @org.apache.ibatis.annotations.Result(property="userName", column="user_name", javaType=String.class), @org.apache.ibatis.annotations.Result(property="cmdType", column="cmd_type", javaType=String.class), @org.apache.ibatis.annotations.Result(property="cmdParam", column="cmd_param", javaType=String.class), @org.apache.ibatis.annotations.Result(property="cmdString", column="cmd_string", javaType=String.class), @org.apache.ibatis.annotations.Result(property="appId", column="app_id", javaType=String.class), @org.apache.ibatis.annotations.Result(property="cmdStatus", column="cmd_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="cmdResult", column="cmd_result", javaType=String.class), @org.apache.ibatis.annotations.Result(property="confirmStatus", column="confirm_status", javaType=String.class), @org.apache.ibatis.annotations.Result(property="confirmTime", column="confirm_time", javaType=String.class)})
    public abstract List<CommandEntity> manholeCommandByLimit(@Param("limit") Integer paramInteger1, @Param("pageSize") Integer paramInteger2);

    @Select({"<script>SELECT imei,map_lng,map_lat,open_status FROM mcm_manhole_info WHERE is_map = 1 </script>"})
    @Results({@org.apache.ibatis.annotations.Result(property="device", column="imei", javaType=String.class), @org.apache.ibatis.annotations.Result(property="mapLng", column="map_lng", javaType=String.class), @org.apache.ibatis.annotations.Result(property="mapLat", column="map_lat", javaType=String.class), @org.apache.ibatis.annotations.Result(property="openStatus", column="open_status", javaType=String.class)})
    public abstract List<MapData> listManholeMap();

    @Select({"SELECT count(0) AS count FROM mcm_manhole_cmd WHERE imei = #{deviceId} AND confirm_status = '等待应答'"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int existCacheCmd(@Param("deviceId") String paramString);

    @Insert({"INSERT INTO mcm_manhole_cmd (imei,create_time,user_name,cmd_type,cmd_param,cmd_string,confirm_status,msg_id) VALUES (#{deviceId},now(),#{userName},#{cmdType},#{cmdParam},#{cmdString},#{confirmStatus},#{msgId})"})
    public abstract void insertCmd(@Param("deviceId") String paramString1, @Param("userName") String paramString2, @Param("cmdType") String paramString3, @Param("cmdParam") String paramString4, @Param("cmdString") String paramString5, @Param("confirmStatus") String paramString6, @Param("msgId") String paramString7);

    @Insert({"INSERT INTO mcm_manhole_event (device_id,event_type,event_value,event_at) VALUES (#{deviceId},#{type},#{value},now())"})
    public abstract void insertEvent(@Param("deviceId") String paramString1, @Param("type") String paramString2, @Param("value") String paramString3);

    @Insert({"INSERT INTO mcm_manhole_data (device_id,imsi,status,defence,open,bu,bp,t,angle,aat,hb,concentration,wls,rssi,rsrp,snr,ci,event_at) VALUES (#{deviceId},#{iccid},#{status},#{defence},#{open},#{bu},#{bp},#{t},#{angle},#{aat},#{hb},#{concentration},#{wls},#{rssi},#{rsrp},#{snr},#{ci},now())"})
    public abstract void insertProperties(JDManholeStatus paramJDManholeStatus);

    @Insert({"INSERT INTO mcm_manhole_info (device_id,device_name,imei,product_id,description,road,district,proprietor,contacts,telphone,longitude,latitude,position,create_at) VALUES (#{deviceId},#{deviceName},#{imei},#{productId},#{description},#{road},#{district},#{proprietor},#{contacts},#{telphone},#{longitude},#{latitude},#{position},now())"})
    public abstract void insertManhole(JDManholeEntity paramJDManholeEntity);

    @Select({"SELECT imei FROM mcm_manhole_info where device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="imei", column="imei", javaType=String.class)})
    public abstract String getManholeImei(@Param("deviceId") String paramString);

    @Update({"UPDATE mcm_manhole_status SET angle = #{angle} ,online_status = '在线' WHERE device_id =#{deviceId}"})
    public abstract void updateAngle(@Param("deviceId") String paramString1, @Param("angle") String paramString2);

    @Update({"UPDATE mcm_manhole_status SET angle = #{angle},move_status = #{status},online_status = '在线' WHERE device_id =#{deviceId}"})
    public abstract void updateMove(@Param("deviceId") String paramString1, @Param("angle") String paramString2, @Param("status") String paramString3);

    @Update({"UPDATE mcm_manhole_status SET overflow_status = #{overflowStatus},online_status = '在线' WHERE device_id =#{deviceId}"})
    public abstract void updateOverflow(@Param("deviceId") String paramString1, @Param("overflowStatus") String paramString2);

    @Update({"UPDATE mcm_manhole_status SET gas_status = #{gasStatus},online_status = '在线' WHERE device_id =#{deviceId}"})
    public abstract void updateGas(@Param("deviceId") String paramString1, @Param("gasStatus") String paramString2);

    @Insert({"INSERT INTO mcm_manhole_status (device_id,iccid,status,ci,concentration,hardware_ver,bp,open,defence,bu,axe_sensor_sta,aat,t,angle,hb,vibfreq,wls,rssi,rsrp,snr,update_at) VALUES (#{deviceId},#{iccid},#{status},#{ci},#{concentration},#{hardwareVer},#{bp},#{open},#{defence},#{bu},#{axeSensorSta},#{aat},#{t},#{angle},#{hb},#{vibfreq},#{wls},#{rssi},#{rsrp},#{snr},now()) ON DUPLICATE KEY UPDATE status = #{status},open = #{open},bu = #{bu},bp = #{bp},t = #{t},defence = #{defence},angle = #{angle},rssi = #{rssi},rsrp = #{rsrp},snr = #{snr},update_at = now()"})
    public abstract void updateProperties(JDManholeStatus paramJDManholeStatus);

    @Insert({"INSERT INTO mcm_manhole_status (device_id,update_at) VALUES (#{deviceId},now()) ON DUPLICATE KEY UPDATE update_at = now(),online_status = '在线'"})
    public abstract void updateManhole(@Param("deviceId") String paramString);

    @Select({"SELECT angle FROM mcm_manhole_status where device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="angle", column="angle", javaType=String.class)})
    public abstract String getOpenFlag(@Param("deviceId") String paramString);

    @Select({"SELECT move_status FROM mcm_manhole_status where device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="moveStatus", column="move_status", javaType=String.class)})
    public abstract String getMoveFlag(@Param("deviceId") String paramString);

    @Select({"SELECT overflow_status FROM mcm_manhole_status where device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="overflowStatus", column="overflow_status", javaType=String.class)})
    public abstract String getOverflowFlag(@Param("deviceId") String paramString);

    @Select({"SELECT gas_status FROM mcm_manhole_status where device_id = #{deviceId}"})
    @Results({@org.apache.ibatis.annotations.Result(property="gasStatus", column="gas_status", javaType=String.class)})
    public abstract String getGasFlag(@Param("deviceId") String paramString);

    @Update({"UPDATE mcm_manhole_cmd SET cmd_status = #{setStatus}, cmd_result = #{val}, confirm_status = '执行完成',confirm_time = now() WHERE msg_id =#{msgId} and confirm_status = '等待应答' and imei = #{deviceId}"})
    public abstract void updateCmd(@Param("setStatus") String paramString1, @Param("val") String paramString2, @Param("msgId") String paramString3, @Param("deviceId") String paramString4);

    @Delete({"DELETE FROM mcm_manhole_info where device_id = #{deviceId}"})
    public abstract void deleteManhole(@Param("deviceId") String paramString);

    @Select({"SELECT count(0) as count FROM mcm_manhole_blocklist where imei = #{imei}"})
    @Results({@org.apache.ibatis.annotations.Result(property="count", column="count", javaType=Integer.class)})
    public abstract int getBlock(@Param("imei") String paramString);

    @Select({"SELECT JGBM FROM tmfp_jgxx where JGBH = #{JGBH}"})
    @Results({@org.apache.ibatis.annotations.Result(property="JGBM", column="JGBM", javaType=String.class)})
    public abstract String getJGBM(@Param("JGBH") String paramString);

    @Select({"SELECT FJLJ FROM tpub_fjxx where DXBM = #{JGBM}"})
    @Results({@org.apache.ibatis.annotations.Result(property="FJLJ", column="FJLJ", javaType=String.class)})
    public abstract List<String> getTPLJ(@Param("JGBM") String paramString);
}
