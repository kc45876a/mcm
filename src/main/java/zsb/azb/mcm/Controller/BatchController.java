package zsb.azb.mcm.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.BatchService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/batch"})
public class BatchController
{
    @Autowired
    private BatchService batchService;

    @PostMapping({"/register"})
    @ResponseBody
    public ResponsePojo batchRegister(@RequestParam("file") MultipartFile file)
    {
        return this.batchService.batchRegister(file);
    }

    @PostMapping({"/command"})
    @ResponseBody
    public ResponsePojo batchCommand(@RequestParam("file") MultipartFile file)
    {
        return this.batchService.batchCommand(file);
    }

    @PostMapping({"/delete"})
    @ResponseBody
    public ResponsePojo batchDelete(@RequestParam("file") MultipartFile file)
    {
        return this.batchService.batchDelete(file);
    }

    @GetMapping({"/task"})
    @ResponseBody
    public ResponsePojo getBatchTask(String taskType)
    {
        return this.batchService.getBatchTask(taskType);
    }
}