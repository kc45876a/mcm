package zsb.azb.mcm.Service;

import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface ProjectService
{
    public abstract ResponsePojo projectListByToken();

    public abstract ResponsePojo projectListByUser(int paramInt);

    public abstract ResponsePojo insertUserProject(int paramInt1, int paramInt2);

    public abstract ResponsePojo deleteUserProject(int paramInt1, int paramInt2);
}
