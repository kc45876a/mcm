package zsb.azb.mcm.Model;

import java.util.List;

public class ResponsePojo<T>
{
    private int code;
    private String msg;
    private T data;
    private List list;

    public ResponsePojo(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public ResponsePojo(int code, String msg, T data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponsePojo(int code, String msg, List list)
    {
        this.code = code;
        this.msg = msg;
        this.list = list;
    }

    public ResponsePojo(int code, String msg, T data, List list)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.list = list;
    }

    public ResponsePojo(T data, List list)
    {
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
        this.list = list;
    }

    public int getCode()
    {
        return this.code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return (T)this.data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public List getList()
    {
        return this.list;
    }

    public void setList(List list)
    {
        this.list = list;
    }

    public String toString()
    {
        return "ResponsePojo{code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + ", list=" + this.list + '}';
    }
}
