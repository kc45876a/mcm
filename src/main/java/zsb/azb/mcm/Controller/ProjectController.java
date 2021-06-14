package zsb.azb.mcm.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.ProjectService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/project"})
public class ProjectController
{
    @Autowired
    private ProjectService projectService;

    @GetMapping({"/list"})
    @ResponseBody
    public ResponsePojo projectListByToken()
    {
        ResponsePojo responsePojo = this.projectService.projectListByToken();
        return responsePojo;
    }

    @GetMapping({"/user"})
    @ResponseBody
    public ResponsePojo projectListByUser(@RequestParam("userId") int userId)
    {
        ResponsePojo responsePojo = this.projectService.projectListByUser(userId);
        return responsePojo;
    }

    @GetMapping({"/complete"})
    @ResponseBody
    public ResponsePojo projectListComplete()
    {
        ResponsePojo responsePojo = this.projectService.projectListByToken();
        return responsePojo;
    }

    @PostMapping({"/auth"})
    @ResponseBody
    public ResponsePojo insertUserProject(@RequestBody Map<String, Object> data)
    {
        ResponsePojo responsePojo = this.projectService.insertUserProject(((Integer)data.get("userId")).intValue(), ((Integer)data.get("projectId")).intValue());
        return responsePojo;
    }

    @DeleteMapping({"/auth"})
    @ResponseBody
    public ResponsePojo deleteUserProject(@RequestParam("userId") int userId, @RequestParam("projectId") int projectId)
    {
        ResponsePojo responsePojo = this.projectService.deleteUserProject(userId, projectId);
        return responsePojo;
    }
}
