package zsb.azb.mcm.Model;

public class JDEventEntity
{
    private int id;
    private String deviceId;
    private String eventType;
    private String eventValue;
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

    public String getEventType()
    {
        return this.eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public String getEventValue()
    {
        return this.eventValue;
    }

    public void setEventValue(String eventValue)
    {
        this.eventValue = eventValue;
    }

    public String getEventAt()
    {
        return this.eventAt;
    }

    public void setEventAt(String eventAt)
    {
        this.eventAt = eventAt;
    }
}
