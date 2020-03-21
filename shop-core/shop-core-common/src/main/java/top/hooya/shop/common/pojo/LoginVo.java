package top.hooya.shop.common.pojo;


import javax.validation.constraints.NotNull;

public class LoginVo {

	@NotNull(message ="{login.accountName.null}")
	private String accountName;
	@NotNull(message ="{login.password.null}")
	private String password;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
