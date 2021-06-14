package zsb.azb.mcm.Service.Impl;

import java.io.PrintStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.UserMapper;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.TokenPojo;
import zsb.azb.mcm.Model.UserEntity;
import zsb.azb.mcm.Service.UserService;
import zsb.azb.mcm.Utils.ResponseManager;
import zsb.azb.mcm.Utils.TokenManager;

@Service
public class UserServiceImpl
        implements UserService
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenPojo tokenPojo;
    @Autowired
    private ResponseManager responseManager;

    public ResponsePojo login(String loginName, String password)
    {
        try
        {
            int userExist = this.userMapper.countUserByLoginName(loginName);
            if (userExist == 0) {
                return ResponseManager.buildError(11001, "用户不存在或已禁用");
            }
            UserEntity user = this.userMapper.getUser(loginName, password);
            if (user == null) {
                return ResponseManager.buildError(11002, "密码错误");
            }
            int userId = user.getUserId();
            String userName = user.getUserName();
            int roleId = user.getRoleId();

            String accessToken = TokenManager.createJwtToken(userId, userName, roleId);
            Map<String, String> data = new Hashtable();
            data.put("userName", userName);
            data.put("accessToken", accessToken);
            user.setAccessToken(accessToken);
            this.userMapper.updateAccessToken(user);
            System.out.println(new Date() + " " + userName + "登录成功");
            return ResponseManager.buildSuccess(data);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return ResponseManager.buildError("登录失败");
    }

    public ResponsePojo listUser(String searchKey)
    {
        List<UserEntity> userList = this.userMapper.listUser(searchKey);
        return ResponseManager.buildSuccess(userList);
    }

    public ResponsePojo updateUser(int userId, Map<String, Object> userInfo)
    {
        String loginName = userInfo.get("loginName").toString();
        String roleName = userInfo.get("roleName").toString();
        int systemId = ((Integer)userInfo.get("systemId")).intValue();
        String password = userInfo.get("password").toString();
        String email = userInfo.get("email").toString();
        String tel = userInfo.get("tel").toString();
        String isUsing = userInfo.get("isUsing").toString();
        int roleId = this.userMapper.getRoleIdByRoleName(roleName);

        UserEntity newUser = new UserEntity();
        newUser.setUserId(userId);
        newUser.setLoginName(loginName);
        newUser.setRoleId(roleId);
        newUser.setRoleName(roleName);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setTel(tel);
        newUser.setIsUsing(isUsing);
        try
        {
            this.userMapper.updateUser(newUser);
            return ResponseManager.buildSuccess();
        }
        catch (Exception ex)
        {
            return ResponseManager.buildError("更新用户信息失败");
        }
        finally
        {
            newUser = null;
        }
    }

    public ResponsePojo createUser(Map<String, Object> userInfo)
    {
        String userName = userInfo.get("userName").toString();
        String loginName = userInfo.get("loginName").toString();
        String password = userInfo.get("password").toString();

        String email = (String)userInfo.get("email");
        String tel = (String)userInfo.get("tel");
        int roleId = Integer.parseInt(String.valueOf(userInfo.get("roleId")));

        UserEntity newUser = new UserEntity();
        newUser.setUserName(userName);
        newUser.setLoginName(loginName);
        newUser.setRoleId(roleId);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setTel(tel);
        try
        {
            this.userMapper.insertUser(newUser);
            return ResponseManager.buildSuccess();
        }
        catch (Exception ex)
        {
            return ResponseManager.buildError("更新用户信息失败");
        }
        finally
        {
            newUser = null;
        }
    }
}
