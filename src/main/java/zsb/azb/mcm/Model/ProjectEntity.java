package zsb.azb.mcm.Model;

public class ProjectEntity
{
    private int projectId;
    private String projectName;
    private String projectApp;
    private String projectKey;
    private String projectType;
    private int systemId;
    private String systemName;

    public int getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
    }

    public String getProjectName()
    {
        return this.projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProjectApp()
    {
        return this.projectApp;
    }

    public void setProjectApp(String projectApp)
    {
        this.projectApp = projectApp;
    }

    public String getProjectKey()
    {
        return this.projectKey;
    }

    public void setProjectKey(String projectKey)
    {
        this.projectKey = projectKey;
    }

    public String getProjectType()
    {
        return this.projectType;
    }

    public void setProjectType(String projectType)
    {
        this.projectType = projectType;
    }

    public int getSystemId()
    {
        return this.systemId;
    }

    public void setSystemId(int systemId)
    {
        this.systemId = systemId;
    }

    public String getSystemName()
    {
        return this.systemName;
    }

    public void setSystemName(String systemName)
    {
        this.systemName = systemName;
    }
}
