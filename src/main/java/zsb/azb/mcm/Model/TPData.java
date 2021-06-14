package zsb.azb.mcm.Model;

import org.springframework.web.multipart.MultipartFile;

public class TPData
{
    private MultipartFile file;
    private String name;
    private String parentId;

    public MultipartFile getFile()
    {
        return this.file;
    }

    public void setFile(MultipartFile file)
    {
        this.file = file;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getParentId()
    {
        return this.parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }
}
