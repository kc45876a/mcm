package zsb.azb.mcm.Utils;

public class Utilty
{
    private static Utilty instance = new Utilty();
    public static final int MIN_MID_VALUE = 1;
    public static final int MAX_MID_VALUE = 65535;

    public static Utilty getInstance()
    {
        return instance;
    }

    public int bytes2Int(byte[] b, int start, int length)
    {
        int sum = 0;
        int end = start + length;
        for (int k = start; k < end; k++)
        {
            int n = b[k] & 0xFF;
            n <<= --length * 8;
            sum += n;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int length)
    {
        byte[] b = new byte[length];
        for (int k = 0; k < length; k++) {
            b[(length - k - 1)] = ((byte)(value >> 8 * k & 0xFF));
        }
        return b;
    }

    public boolean isValidofMid(int mId)
    {
        if ((mId < 1) || (mId > 65535)) {
            return false;
        }
        return true;
    }

    public static String byte2HexStr(byte[] buf)
    {
        if (null == buf) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static String int2HexStr(int i)
    {
        StringBuffer sb = new StringBuffer();
        String hex = Integer.toHexString(i);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        sb.append(hex.toUpperCase());
        return sb.toString();
    }

    public static byte[] hex2Bytes(String hexString)
    {
        if ((hexString == null) || (hexString == "")) {
            throw new IllegalArgumentException("this hexString must not be empty");
        }
        hexString = hexString.toLowerCase();
        byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++)
        {
            byte high = (byte)(Character.digit(hexString.charAt(k), 16) & 0xFF);
            byte low = (byte)(Character.digit(hexString.charAt(k + 1), 16) & 0xFF);
            byteArray[i] = ((byte)(high << 4 | low));
            k += 2;
        }
        return byteArray;
    }

    public static byte SumCheck(byte[] checkByte)
    {
        int result = 0;
        for (byte b : checkByte) {
            result += (b & 0xFF);
        }
        while (result > 255) {
            result -= 256;
        }
        return (byte)result;
    }

    public static String SumCheck(String checkStr)
    {
        byte[] checkByte = hex2Bytes(checkStr);
        int result = 0;
        for (byte b : checkByte) {
            result += (b & 0xFF);
        }
        while (result > 255) {
            result -= 256;
        }
        return int2HexStr(result);
    }

    public static String leftPad(String input, int length)
    {
        StringBuilder zero;
        if (length == 2) {
            zero = new StringBuilder("00");
        } else {
            zero = new StringBuilder("0000");
        }
        return zero.substring(0, zero.length() - input.length()) + input;
    }

    public static String FFCheck(String check)
    {
        int result = 0;
        byte[] checkByte = hex2Bytes(check);
        for (byte b : checkByte) {
            result += (b & 0xFF);
        }
        while (result > 255) {
            result -= 256;
        }
        result = 255 - result;
        String hex = Integer.toHexString(result & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex;
    }
}
