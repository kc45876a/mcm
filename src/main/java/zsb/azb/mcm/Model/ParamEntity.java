package zsb.azb.mcm.Model;

public class ParamEntity
{
    private int id;
    private String paramName;
    private String paramValue;
    private String paramExplain;

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getParamName()
    {
        return this.paramName;
    }

    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }

    public String getParamValue()
    {
        return this.paramValue;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public String getParamExplain()
    {
        return this.paramExplain;
    }

    public void setParamExplain(String paramExplain)
    {
        this.paramExplain = paramExplain;
    }
}
