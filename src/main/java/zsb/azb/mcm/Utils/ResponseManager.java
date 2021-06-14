package zsb.azb.mcm.Utils;

import java.util.List;
import org.springframework.stereotype.Component;
import zsb.azb.mcm.Model.ResponsePojo;

@Component
public class ResponseManager
{
    private static final int OK = 200;
    private static final int ERROR = 400;

    public static ResponsePojo buildSuccess()
    {
        return new ResponsePojo(200, "��������");
    }

    public static ResponsePojo buildSuccess(String msg)
    {
        return new ResponsePojo(200, msg);
    }

    public static <T> ResponsePojo buildSuccess(T data)
    {
        return new ResponsePojo(200, "��������", data);
    }

    public static ResponsePojo buildSuccess(List list)
    {
        return new ResponsePojo(200, "��������", list);
    }

    public static <T> ResponsePojo buildSuccess(T data, List list)
    {
        return new ResponsePojo(200, "��������", data, list);
    }

    public static ResponsePojo buildError(String msg)
    {
        return new ResponsePojo(400, msg);
    }

    public static ResponsePojo buildError(int code, String msg)
    {
        return new ResponsePojo(code, msg);
    }

    public static <T> ResponsePojo buildError(int code, String msg, T data)
    {
        return new ResponsePojo(code, msg, data);
    }
}
