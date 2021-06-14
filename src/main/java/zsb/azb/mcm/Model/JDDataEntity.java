package zsb.azb.mcm.Model;

public class JDDataEntity
{
    private int id;
    private String deviceId;
    private String imsi;
    private String status;
    private String defence;
    private String open;
    private String bu;
    private String bp;
    private String t;
    private String angle;
    private String aat;
    private String hb;
    private String concentration;
    private String wls;
    private String rssi;
    private String rsrp;
    private String snr;
    private String ci;
    private String eventAt;

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDeviceId()
    {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getImsi()
    {
        return this.imsi;
    }

    public void setImsi(String imsi)
    {
        this.imsi = imsi;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getDefence()
    {
        return this.defence;
    }

    public void setDefence(String defence)
    {
        this.defence = defence;
    }

    public String getOpen()
    {
        return this.open;
    }

    public void setOpen(String open)
    {
        this.open = open;
    }

    public String getBu()
    {
        return this.bu;
    }

    public void setBu(String bu)
    {
        this.bu = bu;
    }

    public String getBp()
    {
        return this.bp;
    }

    public void setBp(String bp)
    {
        this.bp = bp;
    }

    public String getT()
    {
        return this.t;
    }

    public void setT(String t)
    {
        this.t = t;
    }

    public String getAngle()
    {
        return this.angle;
    }

    public void setAngle(String angle)
    {
        this.angle = angle;
    }

    public String getAat()
    {
        return this.aat;
    }

    public void setAat(String aat)
    {
        this.aat = aat;
    }

    public String getHb()
    {
        return this.hb;
    }

    public void setHb(String hb)
    {
        this.hb = hb;
    }

    public String getConcentration()
    {
        return this.concentration;
    }

    public void setConcentration(String concentration)
    {
        this.concentration = concentration;
    }

    public String getWls()
    {
        return this.wls;
    }

    public void setWls(String wls)
    {
        this.wls = wls;
    }

    public String getRssi()
    {
        return this.rssi;
    }

    public void setRssi(String rssi)
    {
        this.rssi = rssi;
    }

    public String getRsrp()
    {
        return this.rsrp;
    }

    public void setRsrp(String rsrp)
    {
        this.rsrp = rsrp;
    }

    public String getSnr()
    {
        return this.snr;
    }

    public void setSnr(String snr)
    {
        this.snr = snr;
    }

    public String getCi()
    {
        return this.ci;
    }

    public void setCi(String ci)
    {
        this.ci = ci;
    }

    public String getEventAt()
    {
        return this.eventAt;
    }

    public void setEventAt(String eventAt)
    {
        this.eventAt = eventAt;
    }

    public String toString()
    {
        return "JDDataEntity{deviceId='" + this.deviceId + '\'' + ", imsi='" + this.imsi + '\'' + ", status='" + this.status + '\'' + ", defence='" + this.defence + '\'' + ", open='" + this.open + '\'' + ", bu='" + this.bu + '\'' + ", bp='" + this.bp + '\'' + ", t='" + this.t + '\'' + ", angle='" + this.angle + '\'' + ", aat='" + this.aat + '\'' + ", hb='" + this.hb + '\'' + ", concentration='" + this.concentration + '\'' + ", wls='" + this.wls + '\'' + ", rssi='" + this.rssi + '\'' + ", rsrp='" + this.rsrp + '\'' + ", snr='" + this.snr + '\'' + ", ci='" + this.ci + '\'' + ", eventAt='" + this.eventAt + '\'' + '}';
    }
}
