package zsb.azb.mcm.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import java.io.PrintStream;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zsb.azb.mcm.Mapper.ExecutorMapper;
import zsb.azb.mcm.Mapper.ManholeMapper;
import zsb.azb.mcm.Model.DeviceData;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Service.ExecutorService;
import zsb.azb.mcm.Service.FeignService;
import zsb.azb.mcm.Utils.ResponseManager;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.api.online.DeleteDeviceOpe;
import zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.entity.Delete;

@Service
public class ExecutorServiceImpl
        implements ExecutorService
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private ManholeMapper manholeMapper;
    @Autowired
    private ExecutorMapper executorMapper;
    @Autowired
    private FeignService feignService;

    public ResponsePojo deleteManholes(String imeis)
    {
        String[] imeiArr = imeis.split(",");
        for (String imei : imeiArr)
        {
            String app_key = this.executorMapper.getProjectKey(imei);
            String deviceId = this.executorMapper.getDeviceId(imei);
            DeleteDeviceOpe deviceOpe = new DeleteDeviceOpe(app_key);
            Delete delete = new Delete(deviceId);
            System.out.println(deviceOpe.operation(delete, delete.toJsonObject()).toString());
        }
        return ResponseManager.buildSuccess();
    }

    public void forceDelete() {}

    public void output()
    {
        ArrayList<DeviceData> deviceList = new ArrayList();
        int count = 0;
        int total = 0;
        int page = 0;
        boolean flag = true;
        while (flag)
        {
            page++;
            com.alibaba.fastjson.JSONObject jobj = this.feignService.getDeviceListByPage("ESdCcVLRhgYS=SUJnjotaxSvoJE=", page, 100);

            com.alibaba.fastjson.JSONObject data = jobj.getJSONObject("data");
            total = data.getInteger("total_count").intValue();
            JSONArray devices = data.getJSONArray("devices");
            if (total <= page * 100) {
                flag = false;
            }
            for (int j = 0; j < devices.size(); j++)
            {
                com.alibaba.fastjson.JSONObject device = devices.getJSONObject(j);

                String deviceId = device.getString("id");
                String title = device.getString("title");
                String imei = device.getString("rg_id");
                System.out.println(deviceId + " - " + title + " - " + imei);
                DeviceData deviceData = new DeviceData();
                deviceData.setImei(imei);
                deviceData.setName(title);
                deviceData.setDeviceId(deviceId);
                deviceList.add(deviceData);
                count++;
            }
        }
        this.executorMapper.insertManhole(deviceList);
        System.out.println(count);
        System.out.println(total);
    }
}
