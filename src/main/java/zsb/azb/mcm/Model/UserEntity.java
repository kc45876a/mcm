package zsb.azb.mcm.Model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class UserEntity
        implements Serializable
{
    private static final long serialVersionUID = 8597874926725808734L;
    private int userId;
    private String userName;
    private String loginName;
    private String password;
    private int roleId;
    private String roleName;
    private String tel;
    private String email;
    private String accessToken;
    private String loginAt;
    private String isUsing;

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

    public String getLoginName()
    {
        return this.loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getRoleId()
    {
        return this.roleId;
    }

    public void setRoleId(int roleId)
    {
        this.roleId = roleId;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getTel()
    {
        return this.tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAccessToken()
    {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getLoginAt()
    {
        return this.loginAt;
    }

    public void setLoginAt(String loginAt)
    {
        this.loginAt = loginAt;
    }

    public String getIsUsing()
    {
        return this.isUsing;
    }

    public void setIsUsing(String isUsing)
    {
        this.isUsing = isUsing;
    }

    public String toString()
    {
        return "UserEntity{userId=" + this.userId + ", userName='" + this.userName + '\'' + ", loginName='" + this.loginName + '\'' + ", password='" + this.password + '\'' + ", roleId=" + this.roleId + ", roleName='" + this.roleName + '\'' + ", tel='" + this.tel + '\'' + ", email='" + this.email + '\'' + ", accessToken='" + this.accessToken + '\'' + ", loginAt='" + this.loginAt + '\'' + ", isUsing='" + this.isUsing + '\'' + '}';
    }
}
