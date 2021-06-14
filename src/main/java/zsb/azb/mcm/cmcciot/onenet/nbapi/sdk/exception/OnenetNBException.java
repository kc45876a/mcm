package zsb.azb.mcm.cmcciot.onenet.nbapi.sdk.exception;

public class OnenetNBException
        extends RuntimeException
{
    private NBStatus status;
    private String message = null;

    public OnenetNBException(NBStatus status)
    {
        this.status = status;
    }

    public OnenetNBException(NBStatus status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public int getErrorNo()
    {
        return this.status.getErrorNo();
    }

    public String getError()
    {
        if (this.message != null) {
            return this.status.getError() + ": " + this.message;
        }
        return this.status.getError();
    }
}
