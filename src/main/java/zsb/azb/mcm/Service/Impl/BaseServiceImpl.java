package zsb.azb.mcm.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.BaseMapper;
import zsb.azb.mcm.Model.DistrictEntity;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.RoadEntity;
import zsb.azb.mcm.Service.BaseService;
import zsb.azb.mcm.Utils.ResponseManager;

@Service
public class BaseServiceImpl
        implements BaseService
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private BaseMapper baseMapper;

    public ResponsePojo listRoad()
    {
        List<RoadEntity> roadEntityList = this.baseMapper.listRoad();
        return ResponseManager.buildSuccess(roadEntityList);
    }

    public ResponsePojo listDistrict()
    {
        List<DistrictEntity> districtEntityList = this.baseMapper.listDistrict();
        return ResponseManager.buildSuccess(districtEntityList);
    }
}
