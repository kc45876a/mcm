package zsb.azb.mcm.Service;

import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface ExecutorService
{
    public abstract ResponsePojo deleteManholes(String paramString);

    public abstract void forceDelete();

    public abstract void output();
}
