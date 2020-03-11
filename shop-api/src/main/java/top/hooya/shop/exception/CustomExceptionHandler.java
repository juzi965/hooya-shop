package top.hooya.shop.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理器
 *
 * @author juzi
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	/**
	 * 处理全局异常
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex, HttpServletRequest request) {
	    logger.error(request.getRequestURI());
	    logger.error(ex.getMessage(),ex);
		return Result.unauthorized(ex.getMessage());
	}

//	/**
//	 * sql异常
//	 * @param ex
//	 * @param request
//	 * @return
//	 */
//
//	public Object handleSqlException(SQLSyntaxErrorException ex, HttpServletRequest request) {
//
//        EncryptResponseValue jsonView = new EncryptResponseValue(PropertyUtil.OTHER_ERROR_CODE, "查询出错");
//
//		return jsonView.toString();
//	}

//	/**
//	 * 绑定参数校验异常
//	 *
//	 * @param ex
//	 * @param request
//	 * @return
//	 */
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public Object handleArgumentValidateException(MethodArgumentNotValidException ex, HttpServletRequest request) {
//		FieldError fieldError = ex.getBindingResult().getFieldError();
//		List<String> errorList = ex.getBindingResult().getFieldErrors()
//                .stream()
//                .map(u -> ValidationMessageUtil.getValidationMsg(u.getDefaultMessage()))
//				.collect(Collectors.toList());
//		EncryptResponseValue jsonView = new EncryptResponseValue(PropertyUtil.PARAMS_ERROR_CODE,
//                errorList.stream().findFirst().orElse(Strings.EMPTY),
//				errorList);
//		return jsonView.toString();
//	}
//
//	/**
//	 * 数据校验异常
//	 *
//	 * @param ex
//	 * @param request
//	 * @return
//	 */
//	@ExceptionHandler(BindException.class)
//	public Object handleArgumentValidateException(BindException ex, HttpServletRequest request) {
//		FieldError fieldError = ex.getBindingResult().getFieldError();
//		List<String> errorList = ex.getFieldErrors().stream()
//                .map(u -> ValidationMessageUtil.getValidationMsg(u.getDefaultMessage()))
//				.collect(Collectors.toList());
//        EncryptResponseValue jsonView = new EncryptResponseValue(PropertyUtil.PARAMS_ERROR_CODE,
//                errorList.stream().findFirst().orElse(Strings.EMPTY),
//				errorList);
//		return jsonView.toString();
//	}
//
//	/**
//	 * 处理自定义异常
//	 *
//	 * @return
//	 */
//	@ExceptionHandler(CustomException.class)
//	public Object handleCustomException(CustomException ex, HttpServletRequest request) {
//        EncryptResponseValue jsonView = new EncryptResponseValue(ex.getCode(), ex.getMessage());
//
//		return jsonView.toString();
//	}
}
