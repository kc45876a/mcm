package zsb.azb.mcm.Service;

import org.springframework.web.multipart.MultipartFile;
import zsb.azb.mcm.Model.ResponsePojo;

public abstract interface BatchService
{
    public abstract ResponsePojo batchRegister(MultipartFile paramMultipartFile);

    public abstract ResponsePojo batchCommand(MultipartFile paramMultipartFile);

    public abstract ResponsePojo batchDelete(MultipartFile paramMultipartFile);

    public abstract ResponsePojo getBatchTask(String paramString);
}
