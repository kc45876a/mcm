package zsb.azb.mcm.Model;

public class DistrictEntity
{
    private int districtId;
    private String districtName;
    private int silence;

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
