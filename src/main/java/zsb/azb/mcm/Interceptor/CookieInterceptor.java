package zsb.azb.mcm.Interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.TokenPojo;
import zsb.azb.mcm.Utils.ResponseManager;
import zsb.azb.mcm.Utils.TokenManager;

public class CookieInterceptor
        extends HandlerInterceptorAdapter
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private TokenPojo tokenPojo;
    @Autowired
    private ObjectMapper mapper;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access_token"))
                {
                    String cookieStr = cookie.getValue();
                    try
                    {
                        Claims claims = TokenManager.parseJWT(cookieStr);
                        this.tokenPojo.setUserId(((Integer)claims.get("userId")).intValue());
                        this.tokenPojo.setUserName((String)claims.get("userName"));
                        this.tokenPojo.setRoleId(((Integer)claims.get("roleId")).intValue());
                        this.tokenPojo.setRoleName((String)claims.get("roleName"));
                        this.tokenPojo.setOwners((String)claims.get("owners"));
                        return true;
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex);
                        ResponsePojo responsePojo = ResponseManager.buildError(10001, "token����");
                        response.getWriter().write(this.mapper.writeValueAsString(responsePojo));
                        return false;
                    }
                }
            }
            System.out.println("no access_token");
            ResponsePojo responsePojo = ResponseManager.buildError(10002, "no token");
            response.getWriter().write(this.mapper.writeValueAsString(responsePojo));
            return false;
        }
        System.out.println("no cookie");
        ResponsePojo responsePojo = ResponseManager.buildError(10003, "no cookie");
        response.getWriter().write(this.mapper.writeValueAsString(responsePojo));
        return false;
    }
}
