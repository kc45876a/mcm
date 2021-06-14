package zsb.azb.mcm.Service.Impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.ParamMapper;
import zsb.azb.mcm.Model.ParamEntity;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.ParamService;
import zsb.azb.mcm.Utils.ResponseManager;

@Service
public class ParamServiceImpl
        implements ParamService
{
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    private ResponseManager responseManager;

    public ResponsePojo listParam()
    {
        List<ParamEntity> paramList = this.paramMapper.listParam();
        return ResponseManager.buildSuccess(paramList);
    }

    public ResponsePojo modifyParam(Map<String, String> paramInfo)
    {
        String paramName = (String)paramInfo.get("paramName");
        String paramValue = (String)paramInfo.get("paramValue");
        this.paramMapper.modifyParam(paramName, paramValue);
        return ResponseManager.buildSuccess();
    }
}
