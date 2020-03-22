package top.hooya.shop.exception;


import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.hooya.shop.common.result.Result;
import top.hooya.shop.common.utils.PropertiesUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义异常处理器
 *
 * @author juzi
 */
@RestControllerAdvice
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
	    logger.error(ex.getMessage());
		return Result.error(ex.getMessage());
	}

	/**
	 * 处理自定义异常
	 *
	 * @return
	 */
	@ExceptionHandler(CustomException.class)
	public Result handleCustomException(CustomException ex, HttpServletRequest request) {
		logger.error(request.getRequestURI());
		logger.error(ex.getMessage());
		return Result.failed(ex.getCode(), ex.getMessage());
	}

	/**
	 * 数据校验异常
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public Result handleArgumentValidateException(BindException ex, HttpServletRequest request) {
		logger.error(request.getRequestURI());
		logger.error(ex.getMessage());
		List<String> errorList = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		return new Result(PropertiesUtil.PARAMS_ERROR_CODE, "参数绑定错误",errorList.stream().findFirst().orElse(Strings.EMPTY));
	}

	/**
	 * 绑定参数校验异常
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleArgumentValidateException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		logger.error(request.getRequestURI());
		logger.error(ex.getMessage());
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		return new Result(PropertiesUtil.PARAMS_ERROR_CODE, "参数绑定错误",errorList.stream().findFirst().orElse(Strings.EMPTY));
	}


	/**
	 * sql异常
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	public Result handleSqlException(SQLException ex, HttpServletRequest request) {
		logger.error(request.getRequestURI());
		logger.error(ex.getMessage());
		return Result.error("数据库操作异常");
	}
}
