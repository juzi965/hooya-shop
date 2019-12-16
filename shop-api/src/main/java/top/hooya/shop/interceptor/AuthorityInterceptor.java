package top.hooya.shop.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验拦截器
 * 
 * @author hadoop
 *
 */
public class AuthorityInterceptor implements HandlerInterceptor {

	/**
	 * http请求前权限校验
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		// 登录校验
//		Object userObject = request.getSession().getAttribute("userInfo");
//		if (userObject == null) { // 未登录
//			String header = request.getHeader("x-requested-with");
//			if (StringUtils.isNotEmpty(header) && header.equalsIgnoreCase("XMLHttpRequest")) { // ajax请求
//				Result jsonView = new Result(PropertiesUtil.SUCCESS_CODE, "登录超时请重新登录");
//				String result = JsonUtils.objectToJson(jsonView);
//				response.getWriter().print(result);
//				response.getWriter().flush();
//				response.getWriter().close();
//			} else { // 非ajax请求
//				response.sendRedirect("/home/login");
//			}
//
//			return false;
//		}


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
