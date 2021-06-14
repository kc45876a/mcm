package zsb.azb.mcm.Model;

public class BatchTaskEntity
{
    private int taskId;
    private String taskName;
    private String userName;
    private String setCount;
    private String completionRate;
    private String createAt;
    private String completionAt;
    private String taskType;

    public int getTaskId()
    {
        return this.taskId;
    }

    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }

    public String getTaskName()
    {
        return this.taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getSetCount()
    {
        return this.setCount;
    }

    public void setSetCount(String setCount)
    {
        this.setCount = setCount;
    }

    public String getCompletionRate()
    {
        return this.completionRate;
    }

    public void setCompletionRate(String completionRate)
    {
        this.completionRate = completionRate;
    }

    public String getCreateAt()
    {
        return this.createAt;
    }

    public void setCreateAt(String createAt)
    {
        this.createAt = createAt;
    }

    public String getCompletionAt()
    {
        return this.completionAt;
    }

    public void setCompletionAt(String completionAt)
    {
        this.completionAt = completionAt;
    }

    public String getTaskType()
    {
        return this.taskType;
    }

    public void setTaskType(String taskType)
    {
        this.taskType = taskType;
    }
}
