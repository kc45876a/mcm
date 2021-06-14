package zsb.azb.mcm.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class TokenManager
{
    public static final String SECRET = "zz1230";

    public static String createJwtToken(int userId, String userName, int roleId)
    {
        Map<String, Object> claims = new HashMap();
        claims.put("userId", Integer.valueOf(userId));
        claims.put("userName", userName);
        claims.put("roleId", Integer.valueOf(roleId));
        long ttlMillis = 32400000L;
        return createJwtToken(claims, ttlMillis);
    }

    public static String createJwtToken(int userId, String userName, int roleId, long ttlMillis)
    {
        Map<String, Object> claims = new HashMap();
        claims.put("userId", Integer.valueOf(userId));
        claims.put("userName", userName);
        claims.put("roleId", Integer.valueOf(roleId));
        return createJwtToken(claims, ttlMillis);
    }

    public static String createJwtToken(Map<String, Object> claims, long ttlMillis)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("zz1230");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        int jwt_id = (int)(1.0D + Math.random() * 1000000.0D);

        JwtBuilder builder = Jwts.builder().setClaims(claims).setId(String.valueOf(jwt_id)).setIssuedAt(now).signWith(signatureAlgorithm, signingKey);
        if (ttlMillis >= 0L)
        {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims parseJWT(String jwt)
    {
        Claims claims = (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("zz1230")).parseClaimsJws(jwt).getBody();
        return claims;
    }
}
