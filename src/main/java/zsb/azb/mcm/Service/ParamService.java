package zsb.azb.mcm.Service;

import java.util.Map;
import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface ParamService
{
    public abstract ResponsePojo listParam();

    public abstract ResponsePojo modifyParam(Map<String, String> paramMap);
}
