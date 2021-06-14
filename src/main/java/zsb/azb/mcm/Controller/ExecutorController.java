package zsb.azb.mcm.Controller;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.ExecutorService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/executor"})
public class ExecutorController
{
    @Autowired
    private ExecutorService executorService;

    @PostMapping({"/batch/manholes"})
    @ResponseBody
    public ResponsePojo deleteManholes(@RequestBody Map<String, String> body)
    {
        System.out.println((String)body.get("imeis"));
        return this.executorService.deleteManholes((String)body.get("imeis"));
    }

    @DeleteMapping({"/force"})
    @ResponseBody
    public void force()
    {
        this.executorService.forceDelete();
    }

    @GetMapping({"/output"})
    @ResponseBody
    public void output()
    {
        this.executorService.output();
    }
}