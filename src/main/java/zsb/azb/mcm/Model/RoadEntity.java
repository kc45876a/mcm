package zsb.azb.mcm.Model;

public class RoadEntity
{
    private int roadId;
    private String roadName;
    private int districtId;
    private String districtName;
    private int silence;

    public int getRoadId()
    {
        return this.roadId;
    }

    public void setRoadId(int roadId)
    {
        this.roadId = roadId;
    }

    public String getRoadName()
    {
        return this.roadName;
    }

    public void setRoadName(String roadName)
    {
        this.roadName = roadName;
    }

    public int getDistrictId()
    {
        return this.districtId;
    }

    public void setDistrictId(int districtId)
    {
        this.districtId = districtId;
    }

    public String getDistrictName()
    {
        return this.districtName;
    }

    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

    public int getSilence()
    {
        return this.silence;
    }

    public void setSilence(int silence)
    {
        this.silence = silence;
    }
}
