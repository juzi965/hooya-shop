package top.hooya.shop.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:resource.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
public class PropertiesUtil {

	/**
	 * 成功状态码
	 */
	public static Integer SUCCESS_CODE;
	/**
	 * 成功提示信息
	 */
	public static String SUCCESS_MSG;

	/**
	 * 其他错误状态码
	 */
	public static Integer OTHER_ERROR_CODE;


	@Value("${SUCCESS_CODE}")
	public void setSuccessCode(Integer successCode) {
		SUCCESS_CODE = successCode;
	}

	@Value("${SUCCESS_MSG}")
	public void setSuccessMsg(String successMsg) {
		SUCCESS_MSG = successMsg;
	}
	@Value("${OTHER_ERROR_CODE}")
	public void setOtherErrorCode(Integer otherErrorCode) {
		OTHER_ERROR_CODE = otherErrorCode;
	}
}
