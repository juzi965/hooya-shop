package top.hooya.shop.exception;

/**
 * 用户自定义异常
 * 
 * @author hadoop
 *
 */
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
