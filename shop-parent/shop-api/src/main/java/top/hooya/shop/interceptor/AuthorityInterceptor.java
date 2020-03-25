package top.hooya.shop.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.hooya.shop.common.pojo.PassToken;
import top.hooya.shop.common.pojo.UserLoginToken;
import top.hooya.shop.common.utils.JwtUtil;
import top.hooya.shop.common.utils.PropertiesUtil;
import top.hooya.shop.exception.CustomException;
import top.hooya.shop.pojo.UserInfo;
import top.hooya.shop.service.UserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限校验拦截器
 * 
 * @author hadoop
 *
 */
public class AuthorityInterceptor implements HandlerInterceptor {

	@Autowired
	private UserInfoService userInfoService;

	/**
	 * http请求前权限校验
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)throws Exception{
		// 从请求头中取出token
		String token = request.getHeader("token");
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod handlerMethod=(HandlerMethod)object;
		Method method=handlerMethod.getMethod();

		//检查是否有passtoken注释，有则跳过认证
		if (method.isAnnotationPresent(PassToken.class)) {
			PassToken passToken = method.getAnnotation(PassToken.class);
			if (passToken.required()) {
				return true;
			}
		}
		//检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(UserLoginToken.class)) {
			UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
			if (userLoginToken.required()) {
				// 执行认证
				if (StringUtils.isEmpty(token)) {
					throw new CustomException(PropertiesUtil.TOKEN_VERIFY_FAIL ,"请登录后再进行相关操作！");
				}
				// 获取 token 中的 user id
				int userId;
				try {
					userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
				} catch (JWTDecodeException j) {
					throw new CustomException(PropertiesUtil.TOKEN_VERIFY_FAIL ,"获取用户信息失败，请重新登录！");
				}
				UserInfo userInfo = userInfoService.getUserInfoById(userId);
				if (userInfo == null) {
					throw new CustomException(PropertiesUtil.TOKEN_VERIFY_FAIL ,"用户不存在，请重新登录！");
				}
				// 验证 token
				JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfo.getPassword())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					throw new CustomException(PropertiesUtil.TOKEN_VERIFY_FAIL ,"权限验证失败，请重新登录");
				}
				//将验证通过后的用户信息放到请求中
				userInfo.setPassword("");
				request.setAttribute("currentUser", userInfo);
				return true;

			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	                       ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
