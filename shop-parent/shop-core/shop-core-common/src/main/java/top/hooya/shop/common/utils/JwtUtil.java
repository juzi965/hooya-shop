package top.hooya.shop.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import top.hooya.shop.pojo.UserInfo;

/**
 * @author juzi9
 * @date 2020-03-09 16:22
 */
public class JwtUtil {

    public static String getToken(UserInfo user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
