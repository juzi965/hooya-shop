package top.hooya.shop.common.result;

import top.hooya.shop.common.utils.PropertiesUtil;

public class Result {
	private Integer code;
	private String message;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result() {
		super();
	}
	public Result(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Result(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static Result Success(Object data) {
		return new Result(PropertiesUtil.SUCCESS_CODE,PropertiesUtil.SUCCESS_MSG,data);
	}


	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
