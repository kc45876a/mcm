package zsb.azb.mcm.Model;

public class MenuEntity
{
    private int menuId;
    private String menuName;
    private int menuParentId;
    private String menuParentName;
    private String menuPath;
    private String menuIcon;
    private int menuIndex;

    public int getMenuId()
    {
        return this.menuId;
    }

    public void setMenuId(int menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuName()
    {
        return this.menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public int getMenuParentId()
    {
        return this.menuParentId;
    }

    public void setMenuParentId(int menuParentId)
    {
        this.menuParentId = menuParentId;
    }

    public String getMenuParentName()
    {
        return this.menuParentName;
    }

    public void setMenuParentName(String menuParentName)
    {
        this.menuParentName = menuParentName;
    }

    public String getMenuPath()
    {
        return this.menuPath;
    }

    public void setMenuPath(String menuPath)
    {
        this.menuPath = menuPath;
    }

    public String getMenuIcon()
    {
        return this.menuIcon;
    }

    public void setMenuIcon(String menuIcon)
    {
        this.menuIcon = menuIcon;
    }

    public int getMenuIndex()
    {
        return this.menuIndex;
    }

    public void setMenuIndex(int menuIndex)
    {
        this.menuIndex = menuIndex;
    }

    public String toString()
    {
        return "MenuEntity{menuId=" + this.menuId + ", menuName='" + this.menuName + '\'' + ", menuParentId=" + this.menuParentId + ", menuParentName='" + this.menuParentName + '\'' + ", menuPath='" + this.menuPath + '\'' + ", menuIcon='" + this.menuIcon + '\'' + ", menuIndex=" + this.menuIndex + '}';
    }
}
