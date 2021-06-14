package zsb.azb.mcm.Model;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
@Component("TokenPojo")
public class TokenPojo
        implements Serializable
{
    private static final long serialVersionUID = 9134726889277808512L;
    private int userId;
    private String userName;
    private int systemId;
    private int roleId;
    private String systemName;
    private String roleName;
    private String owners;

    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getSystemId()
    {
        return this.systemId;
    }

    public void setSystemId(int systemId)
    {
        this.systemId = systemId;
    }

    public String getSystemName()
    {
        return this.systemName;
    }

    public void setSystemName(String systemName)
    {
        this.systemName = systemName;
    }

    public int getRoleId()
    {
        return this.roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }

    public String getOwners()
    {
        return this.owners;
    }

    public void setOwners(String owners)
    {
        this.owners = owners;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String toString()
    {
        return "TokenPojo{userId=" + this.userId + ", userName='" + this.userName + '\'' + ", systemId=" + this.systemId + ", roleId=" + this.roleId + ", systemName='" + this.systemName + '\'' + ", roleName='" + this.roleName + '\'' + ", owners='" + this.owners + '\'' + '}';
    }
}
