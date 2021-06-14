package zsb.azb.mcm.Model;

public class RoleEntity
{
    private int roleId;
    private String roleCode;
    private String roleName;
    private String roleExplain;
    private int roleIndex;

    public int getRoleId()
    {
        return this.roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleCode()
    {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleExplain()
    {
        return this.roleExplain;
    }

    public void setRoleExplain(String roleExplain)
    {
        this.roleExplain = roleExplain;
    }

    public int getRoleIndex()
    {
        return this.roleIndex;
    }

    public void setRoleIndex(int roleIndex)
    {
        this.roleIndex = roleIndex;
    }

    public String toString()
    {
        return "RoleEntity{roleId=" + this.roleId + ", roleCode='" + this.roleCode + '\'' + ", roleName='" + this.roleName + '\'' + ", roleExplain='" + this.roleExplain + '\'' + ", roleIndex=" + this.roleIndex + '}';
    }
}
