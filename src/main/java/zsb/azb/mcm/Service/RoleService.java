package zsb.azb.mcm.Service;

import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface RoleService
{
    public abstract ResponsePojo roleList();

    public abstract ResponsePojo roleSimpleList();

    public abstract ResponsePojo createRole(String paramString1, String paramString2, String paramString3);
}
