package top.hooya.shop.exception;

/**
 * 用户自定义异常
 * 
 * @author hadoop
 *
 */
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
