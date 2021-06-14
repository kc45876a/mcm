package zsb.azb.mcm.Model;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class ManholeEntity
        implements Serializable
{
    private static final long serialVersionUID = 68049108091148852L;
    private int id;
    private String imei;
    private String manholeName;
    private String appId;
    private String appName;
    private String deviceId;
    private int rsrp;
    private int rssi;
    private int snr;
    private int phycellId;
    private int angle;
    private String voltage;
    private int vibrate;
    private String g;
    private int gas;
    private String gasType;
    private String gasStatus;
    private String openStatus;
    private String overflowStatus;
    private String dismantleStatus;
    private String operator;
    private String iccid;
    private String version;
    private float longitude;
    private float latitude;
    private float mapLng;
    private float mapLat;
    private int dailyUpdate;
    private String coverType;
    private String material;
    private String position;
    private String isOnline;
    private int isMap;
    private String updateAt;

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
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

    public String getManholeName()
    {
        return this.manholeName;
    }

    public void setManholeName(String manholeName)
    {
        this.manholeName = manholeName;
    }

    public String getAppId()
    {
        return this.appId;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppName()
    {
        return this.appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getDeviceId()
    {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
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

    public int getAngle()
    {
        return this.angle;
    }

    public void setAngle(int angle)
    {
        this.angle = angle;
    }

    public String getVoltage()
    {
        return this.voltage;
    }

    public void setVoltage(String voltage)
    {
        this.voltage = voltage;
    }

    public int getVibrate()
    {
        return this.vibrate;
    }

    public void setVibrate(int vibrate)
    {
        this.vibrate = vibrate;
    }

    public int getGas()
    {
        return this.gas;
    }

    public void setGas(int gas)
    {
        this.gas = gas;
    }

    public String getGasType()
    {
        return this.gasType;
    }

    public void setGasType(String gasType)
    {
        this.gasType = gasType;
    }

    public String getGasStatus()
    {
        return this.gasStatus;
    }

    public void setGasStatus(String gasStatus)
    {
        this.gasStatus = gasStatus;
    }

    public String getOpenStatus()
    {
        return this.openStatus;
    }

    public void setOpenStatus(String openStatus)
    {
        this.openStatus = openStatus;
    }

    public String getOverflowStatus()
    {
        return this.overflowStatus;
    }

    public void setOverflowStatus(String overflowStatus)
    {
        this.overflowStatus = overflowStatus;
    }

    public String getDismantleStatus()
    {
        return this.dismantleStatus;
    }

    public void setDismantleStatus(String dismantleStatus)
    {
        this.dismantleStatus = dismantleStatus;
    }

    public String getOperator()
    {
        return this.operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getIccid()
    {
        return this.iccid;
    }

    public void setIccid(String iccid)
    {
        this.iccid = iccid;
    }

    public String getVersion()
    {
        return this.version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public float getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

    public float getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    public float getMapLng()
    {
        return this.mapLng;
    }

    public void setMapLng(float mapLng)
    {
        this.mapLng = mapLng;
    }

    public float getMapLat()
    {
        return this.mapLat;
    }

    public void setMapLat(float mapLat)
    {
        this.mapLat = mapLat;
    }

    public int getDailyUpdate()
    {
        return this.dailyUpdate;
    }

    public void setDailyUpdate(int dailyUpdate)
    {
        this.dailyUpdate = dailyUpdate;
    }

    public String getCoverType()
    {
        return this.coverType;
    }

    public void setCoverType(String coverType)
    {
        this.coverType = coverType;
    }

    public String getMaterial()
    {
        return this.material;
    }

    public void setMaterial(String material)
    {
        this.material = material;
    }

    public String getPosition()
    {
        return this.position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getIsOnline()
    {
        return this.isOnline;
    }

    public void setIsOnline(String isOnline)
    {
        this.isOnline = isOnline;
    }

    public int getIsMap()
    {
        return this.isMap;
    }

    public void setIsMap(int isMap)
    {
        this.isMap = isMap;
    }

    public String getUpdateAt()
    {
        return this.updateAt;
    }

    public void setUpdateAt(String updateAt)
    {
        this.updateAt = updateAt;
    }

    public String getG()
    {
        return this.g;
    }

    public void setG(String g)
    {
        this.g = g;
    }
}
