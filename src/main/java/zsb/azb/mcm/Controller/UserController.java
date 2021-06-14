package zsb.azb.mcm.Controller;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.UserService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/user"})
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping({"/hello"})
    @ResponseBody
    public void hello() {}

    @PostMapping({"/login"})
    @ResponseBody
    public ResponsePojo login(@RequestBody Map<String, String> loginInfo)
    {
        System.out.println("login");
        ResponsePojo responsePojo = this.userService.login((String)loginInfo.get("loginName"), (String)loginInfo.get("password"));

        return responsePojo;
    }

    @GetMapping({"/list"})
    @ResponseBody
    public ResponsePojo listUser(@RequestParam("searchKey") String searchKey)
            throws UnsupportedEncodingException
    {
        String depts = URLDecoder.decode(searchKey, "utf-8");
        ResponsePojo responsePojo = this.userService.listUser(depts);
        return responsePojo;
    }

    @PutMapping({"/{userId}"})
    @ResponseBody
    public ResponsePojo updateUser(@PathVariable int userId, @RequestBody Map<String, Object> userInfo)
    {
        ResponsePojo responsePojo = this.userService.updateUser(userId, userInfo);
        return responsePojo;
    }

    @PostMapping({"/create"})
    @ResponseBody
    public ResponsePojo createUser(@RequestBody Map<String, Object> userInfo)
    {
        ResponsePojo responsePojo = this.userService.createUser(userInfo);
        return responsePojo;
    }
}