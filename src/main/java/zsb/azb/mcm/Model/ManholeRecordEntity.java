package zsb.azb.mcm.Model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class ManholeRecordEntity
        implements Serializable
{
    private static final long serialVersionUID = 4653289532811571951L;
    private long id;
    private String imei;
    private String dataType;
    private String operator;
    private int angle;
    private int rsrp;
    private int rssi;
    private int snr;
    private int phycellId;
    private int temp;
    private int gas;
    private int vibrate;
    private String gasType;
    private String voltage;
    private String isRetrans;
    private String gasStatus;
    private String dismantleStatus;
    private String overflowStatus;
    private String angleSensorStatus;
    private String tempSensorStatus;
    private String isRecover;
    private String openStatus;
    private String rawData;
    private String eventTime;

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getImei()
    {
        return this.imei;
    }

    public void setImei(String imei)
    {
        this.imei = imei;
    }

    public String getDataType()
    {
        return this.dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getOperator()
    {
        return this.operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public int getAngle()
    {
        return this.angle;
    }

    public void setAngle(int angle)
    {
        this.angle = angle;
    }

    public int getRsrp()
    {
        return this.rsrp;
    }

    public void setRsrp(int rsrp)
    {
        this.rsrp = rsrp;
    }

    public int getRssi()
    {
        return this.rssi;
    }

    public void setRssi(int rssi)
    {
        this.rssi = rssi;
    }

    public int getSnr()
    {
        return this.snr;
    }

    public void setSnr(int snr)
    {
        this.snr = snr;
    }

    public int getPhycellId()
    {
        return this.phycellId;
    }

    public void setPhycellId(int phycellId)
    {
        this.phycellId = phycellId;
    }

    public int getTemp()
    {
        return this.temp;
    }

    public void setTemp(int temp)
    {
        this.temp = temp;
    }

    public int getGas()
    {
        return this.gas;
    }

    public void setGas(int gas)
    {
        this.gas = gas;
    }

    public int getVibrate()
    {
        return this.vibrate;
    }

    public void setVibrate(int vibrate)
    {
        this.vibrate = vibrate;
    }

    public String getGasType()
    {
        return this.gasType;
    }

    public void setGasType(String gasType)
    {
        this.gasType = gasType;
    }

    public String getVoltage()
    {
        return this.voltage;
    }

    public void setVoltage(String voltage)
    {
        this.voltage = voltage;
    }

    public String getIsRetrans()
    {
        return this.isRetrans;
    }

    public void setIsRetrans(String isRetrans)
    {
        this.isRetrans = isRetrans;
    }

    public String getGasStatus()
    {
        return this.gasStatus;
    }

    public void setGasStatus(String gasStatus)
    {
        this.gasStatus = gasStatus;
    }

    public String getDismantleStatus()
    {
        return this.dismantleStatus;
    }

    public void setDismantleStatus(String dismantleStatus)
    {
        this.dismantleStatus = dismantleStatus;
    }

    public String getOverflowStatus()
    {
        return this.overflowStatus;
    }

    public void setOverflowStatus(String overflowStatus)
    {
        this.overflowStatus = overflowStatus;
    }

    public String getAngleSensorStatus()
    {
        return this.angleSensorStatus;
    }

    public void setAngleSensorStatus(String angleSensorStatus)
    {
        this.angleSensorStatus = angleSensorStatus;
    }

    public String getTempSensorStatus()
    {
        return this.tempSensorStatus;
    }

    public void setTempSensorStatus(String tempSensorStatus)
    {
        this.tempSensorStatus = tempSensorStatus;
    }

    public String getIsRecover()
    {
        return this.isRecover;
    }

    public void setIsRecover(String isRecover)
    {
        this.isRecover = isRecover;
    }

    public String getOpenStatus()
    {
        return this.openStatus;
    }

    public void setOpenStatus(String openStatus)
    {
        this.openStatus = openStatus;
    }

    public String getRawData()
    {
        return this.rawData;
    }

    public void setRawData(String rawData)
    {
        this.rawData = rawData;
    }

    public String getEventTime()
    {
        return this.eventTime;
    }

    public void setEventTime(String eventTime)
    {
        this.eventTime = eventTime;
    }
}
