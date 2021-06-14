package zsb.azb.mcm.Controller;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.ManholeService;

@RestController
@EnableAutoConfiguration
@RequestMapping({"/manhole"})
public class ManholeController
{
    @Autowired
    private ManholeService manholeService;

    @GetMapping({"/count"})
    @ResponseBody
    public ResponsePojo countManhole(@RequestParam String searchKey)
    {
        return this.manholeService.countManhole(searchKey);
    }

    @GetMapping({"/list"})
    @ResponseBody
    public ResponsePojo listManhole(@RequestParam int page, int pageSize, String searchKey)
    {
        return this.manholeService.listManhole(page, pageSize, searchKey);
    }

    @GetMapping({"/info"})
    @ResponseBody
    public ResponsePojo getoManhole(@RequestParam String deviceId)
    {
        return this.manholeService.getManhole(deviceId);
    }

    @GetMapping({"/record/count"})
    @ResponseBody
    public ResponsePojo recordCount(@RequestParam String imei)
    {
        return this.manholeService.manholeRecordCount(imei);
    }

    @GetMapping({"/record/list"})
    @ResponseBody
    public ResponsePojo recordList(@RequestParam int page, int pageSize, String imei)
    {
        return this.manholeService.manholeRecordList(page, pageSize, imei);
    }

    @GetMapping({"/event/count"})
    @ResponseBody
    public ResponsePojo eventCount(@RequestParam String deviceId)
    {
        return this.manholeService.manholeEventCount(deviceId);
    }

    @GetMapping({"/event/list"})
    @ResponseBody
    public ResponsePojo eventList(@RequestParam int page, int pageSize, String deviceId)
    {
        System.out.println(page + "--" + pageSize + "--" + deviceId);
        return this.manholeService.manholeEventList(page, pageSize, deviceId);
    }

    @GetMapping({"/data/count"})
    @ResponseBody
    public ResponsePojo dataCount(@RequestParam String deviceId)
    {
        return this.manholeService.manholeDataCount(deviceId);
    }

    @GetMapping({"/data/list"})
    @ResponseBody
    public ResponsePojo dataList(@RequestParam int page, int pageSize, String deviceId)
    {
        return this.manholeService.manholeDataList(page, pageSize, deviceId);
    }

    @PostMapping({"/test"})
    @ResponseBody
    public ResponsePojo command(@RequestBody Map<String, String> cmd)
    {
        return this.manholeService.createCommand(cmd);
    }

    @PostMapping({"/command"})
    @ResponseBody
    public ResponsePojo createCommand(@RequestBody Map<String, String> cmd)
    {
        return this.manholeService.createCommand(cmd);
    }

    @GetMapping({"/command/count"})
    @ResponseBody
    public ResponsePojo countManholeCommand()
    {
        return this.manholeService.manholeCommandCount();
    }

    @GetMapping({"/command/list"})
    @ResponseBody
    public ResponsePojo listManholeCommand(@RequestParam int page, int pageSize)
    {
        return this.manholeService.manholeCommandList(page, pageSize);
    }

    @GetMapping({"/map/list"})
    @ResponseBody
    public ResponsePojo listManholeMap()
    {
        return this.manholeService.manholeMapList();
    }
}
