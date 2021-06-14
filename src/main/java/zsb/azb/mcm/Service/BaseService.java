package zsb.azb.mcm.Service;

import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface BaseService
{
    public abstract ResponsePojo listRoad();

    public abstract ResponsePojo listDistrict();
}
