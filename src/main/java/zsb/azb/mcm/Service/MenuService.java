package zsb.azb.mcm.Service;

import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface MenuService
{
    public abstract ResponsePojo frameMenu();

    public abstract ResponsePojo completeMenu();

    public abstract ResponsePojo roleMenu(int paramInt);

    public abstract ResponsePojo createRoleMenu(int paramInt1, int paramInt2);

    public abstract ResponsePojo createRoleMenus(int paramInt, String paramString);

    public abstract ResponsePojo deleteRoleMenu(String paramString);

    public abstract ResponsePojo deleteRoleMenus(int paramInt);
}
