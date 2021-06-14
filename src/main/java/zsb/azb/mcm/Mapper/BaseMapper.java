package zsb.azb.mcm.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import zsb.azb.mcm.Model.DistrictEntity;
import zsb.azb.mcm.Model.RoadEntity;

public abstract interface BaseMapper
{
    @Select({"SELECT * FROM view_base_road"})
    @Results({@org.apache.ibatis.annotations.Result(property="roadId", column="road_id"), @org.apache.ibatis.annotations.Result(property="roadName", column="road_name"), @org.apache.ibatis.annotations.Result(property="districtId", column="district_id"), @org.apache.ibatis.annotations.Result(property="districtName", column="district_name"), @org.apache.ibatis.annotations.Result(property="silence", column="silence")})
    public abstract List<RoadEntity> listRoad();

    @Select({"SELECT * FROM mcm_base_district"})
    @Results({@org.apache.ibatis.annotations.Result(property="districtId", column="district_id"), @org.apache.ibatis.annotations.Result(property="districtName", column="district_name"), @org.apache.ibatis.annotations.Result(property="silence", column="silence")})
    public abstract List<DistrictEntity> listDistrict();
}
