package zsb.azb.mcm.Service;

import java.util.Map;
import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface ManholeService
{
    public abstract ResponsePojo countManhole(String paramString);

    public abstract ResponsePojo listManhole(int paramInt1, int paramInt2, String paramString);

    public abstract ResponsePojo getManhole(String paramString);

    public abstract ResponsePojo manholeRecordCount(String paramString);

    public abstract ResponsePojo manholeRecordList(int paramInt1, int paramInt2, String paramString);

    public abstract ResponsePojo manholeEventCount(String paramString);

    public abstract ResponsePojo manholeEventList(int paramInt1, int paramInt2, String paramString);

    public abstract ResponsePojo manholeDataCount(String paramString);

    public abstract ResponsePojo manholeDataList(int paramInt1, int paramInt2, String paramString);

    public abstract ResponsePojo createCommand(Map<String, String> paramMap);

    public abstract ResponsePojo manholeCommandCount();

    public abstract ResponsePojo manholeCommandList(int paramInt1, int paramInt2);

    public abstract ResponsePojo manholeMapList();
}
