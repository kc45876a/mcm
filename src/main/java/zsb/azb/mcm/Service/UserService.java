package zsb.azb.mcm.Service;

import java.util.Map;
import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface UserService
{
    public abstract ResponsePojo login(String paramString1, String paramString2);

    public abstract ResponsePojo listUser(String paramString);

    public abstract ResponsePojo updateUser(int paramInt, Map<String, Object> paramMap);

    public abstract ResponsePojo createUser(Map<String, Object> paramMap);
}
