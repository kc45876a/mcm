package zsb.azb.mcm.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zsb.azb.mcm.Mapper.BatchMapper;
import zsb.azb.mcm.Mapper.ManholeMapper;
import zsb.azb.mcm.Model.BatchTaskEntity;
import zsb.azb.mcm.Model.JDManholeEntity;
import zsb.azb.mcm.Model.ResponsePojo;
import zsb.azb.mcm.Model.TokenPojo;
import zsb.azb.mcm.Service.BatchService;
import zsb.azb.mcm.Service.JdIotService;
import zsb.azb.mcm.Utils.CommandManager;
import zsb.azb.mcm.Utils.ResponseManager;
import zsb.azb.mcm.Utils.Utilty;

@Service
public class BatchServiceImpl
        implements BatchService
{
    @Autowired
    private ResponseManager responseManager;
    @Autowired
    private JdIotService jdIotService;
    @Autowired
    private BatchMapper batchMapper;
    @Autowired
    private TokenPojo tokenPojo;
    @Autowired
    private CommandManager commandManager;
    @Autowired
    private ManholeMapper manholeMapper;
    Random random = new Random();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ResponsePojo batchRegister(MultipartFile file)
    {
        if (file.isEmpty()) {
            throw new RuntimeException("文件错误");
        }
        try
        {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNums = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowNums; i++)
            {
                XSSFRow row = sheet.getRow(i);
                if (row != null)
                {
                    int cellNums = row.getPhysicalNumberOfCells();
                    JDManholeEntity jdManholeEntity = new JDManholeEntity();
                    for (int j = 0; j < 11; j++)
                    {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null)
                        {
                            cell.setCellType(CellType.STRING);
                            String value = cell.getStringCellValue();
                            if (value == null) {
                                value = "";
                            }
                            switch (j)
                            {
                                case 0:
                                    jdManholeEntity.setImei(value);
                                    System.out.println("imei is => " + value);
                                    break;
                                case 1:
                                    jdManholeEntity.setDeviceName(value);
                                    System.out.println("DeviceName is => " + value);
                                    break;
                                case 2:
                                    jdManholeEntity.setProductId(value);
                                    System.out.println("ProductId is => " + value);
                                    break;
                                case 3:
                                    jdManholeEntity.setDescription(value);
                                    System.out.println("Description is => " + value);
                                    break;
                                case 4:
                                    jdManholeEntity.setRoad(value);
                                    System.out.println("Road is => " + value);
                                    break;
                                case 5:
                                    jdManholeEntity.setDistrict(value);
                                    System.out.println("District is => " + value);
                                    break;
                                case 6:
                                    jdManholeEntity.setPosition(value);
                                    System.out.println("Position is => " + value);
                                    break;
                                case 7:
                                    jdManholeEntity.setLongitude(value);
                                    System.out.println("Longitude is => " + value);
                                    break;
                                case 8:
                                    jdManholeEntity.setLatitude(value);
                                    System.out.println("Latitude is => " + value);
                                    break;
                                case 9:
                                    jdManholeEntity.setContacts(value);
                                    System.out.println("Contacts is => " + value);
                                    break;
                                case 10:
                                    jdManholeEntity.setTelphone(value);
                                    System.out.println("Telephone is => " + value);
                            }
                        }
                    }
                    this.jdIotService.deviceRegister(jdManholeEntity
                            .getDeviceName(), jdManholeEntity
                            .getDescription(), jdManholeEntity
                            .getProductId(), jdManholeEntity
                            .getImei(), jdManholeEntity
                            .getRoad(), jdManholeEntity
                            .getDistrict(), jdManholeEntity
                            .getLongitude(), jdManholeEntity
                            .getLatitude(), jdManholeEntity
                            .getContacts());
                    try
                    {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo batchCommand(MultipartFile file)
    {
        if (file.isEmpty()) {
            throw new RuntimeException("文件错误");
        }
        try
        {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNums = sheet.getPhysicalNumberOfRows();
            int successCount = 0;int totalCount = 0;
            String start = this.sdf.format(new Date());
            String taskName = "批量指令" + System.currentTimeMillis();
            String userName = this.tokenPojo.getUserName();
            String taskType = "command";
            for (int i = 1; i < rowNums; i++)
            {
                XSSFRow row = sheet.getRow(i);
                if (row != null)
                {
                    totalCount++;
                    String deviceId = null;String commandType = null;String commandParams = null;
                    for (int j = 0; j < 3; j++)
                    {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null)
                        {
                            cell.setCellType(CellType.STRING);
                            String value = cell.getStringCellValue();
                            if (value == null) {
                                value = "";
                            }
                            switch (j)
                            {
                                case 0:
                                    deviceId = value;
                                    break;
                                case 1:
                                    commandType = value;
                                    break;
                                case 2:
                                    commandParams = value;
                            }
                        }
                    }
                    String msgId = Utilty.leftPad(Utilty.int2HexStr(this.random.nextInt(65535)), 4);
                    if (commandType.equals("透传指令")) {
                        msgId = commandParams.substring(2, 6);
                    }
                    String cmdStr = this.commandManager.postCmdToJD(commandType, commandParams, msgId);
                    JSONObject result = this.jdIotService.serviceInvoke(deviceId, "RemoteControl", cmdStr);
                    if (result.getString("code").equals("200"))
                    {
                        successCount++;
                        this.manholeMapper.insertCmd(deviceId, this.tokenPojo.getUserName(), commandType, commandParams, cmdStr, "等待应答", msgId);
                    }
                    else
                    {
                        System.out.println(deviceId + " command error!! " + result.getString("msg"));
                    }
                    try
                    {
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            int rate = (int)(successCount / totalCount) * 10000;
            String end = this.sdf.format(new Date());
            this.batchMapper.insertBatchTask(taskName, userName, start, successCount, totalCount, rate, end, taskType);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo batchDelete(MultipartFile file)
    {
        if (file.isEmpty()) {
            throw new RuntimeException("文件错误");
        }
        try
        {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNums = sheet.getPhysicalNumberOfRows();
            int successCount = 0;int totalCount = 0;
            String start = this.sdf.format(new Date());
            String taskName = "批量删除" + System.currentTimeMillis();
            String userName = this.tokenPojo.getUserName();
            String taskType = "delete";
            for (int i = 1; i < rowNums; i++)
            {
                XSSFRow row = sheet.getRow(i);
                if (row != null)
                {
                    XSSFCell cell = row.getCell(0);
                    if (cell != null)
                    {
                        cell.setCellType(CellType.STRING);
                        String value = cell.getStringCellValue();
                        if (value != null)
                        {
                            totalCount++;
                            JSONObject result = this.jdIotService.deviceDelete(value);
                            if (result.getString("code").equals("200")) {
                                successCount++;
                            } else {
                                System.out.println(value + " delete error!! " + result.getString("msg"));
                            }
                            try
                            {
                                Thread.sleep(100L);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            int rate = (int)(successCount / totalCount) * 100;
            String end = this.sdf.format(new Date());
            this.batchMapper.insertBatchTask(taskName, userName, start, successCount, totalCount, rate, end, taskType);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return ResponseManager.buildSuccess();
    }

    public ResponsePojo getBatchTask(String taskType)
    {
        List<BatchTaskEntity> batchTaskList = this.batchMapper.batchTaskList(taskType);
        return ResponseManager.buildSuccess(batchTaskList);
    }
}
