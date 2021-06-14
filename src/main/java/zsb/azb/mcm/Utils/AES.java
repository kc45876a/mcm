package zsb.azb.mcm.Utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES {
//    @Value("${tuisong.pdk}")
//    private static String pdk;

    private static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException var10) {
            var10.printStackTrace();
        } catch (NoSuchPaddingException var11) {
            var11.printStackTrace();
        } catch (InvalidKeyException var12) {
            var12.printStackTrace();
        } catch (UnsupportedEncodingException var13) {
            var13.printStackTrace();
        } catch (IllegalBlockSizeException var14) {
            var14.printStackTrace();
        } catch (BadPaddingException var15) {
            var15.printStackTrace();
        }

        return null;
    }
    private static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }
    public static String encryptHex(String content,String pdk) {
        if("DEFAULT".equalsIgnoreCase(pdk)){
            return content;
        }
        byte[] encrypt = encrypt(content, pdk);
        String hexStrResult = parseByte2HexStr(encrypt);
        return hexStrResult.toLowerCase();
    }
    public static void main(String[] args) {
        String pdk = "XX";
        final String pwd = encryptHex("加密内容",pdk);
        System.out.println(pwd);
    }
}
