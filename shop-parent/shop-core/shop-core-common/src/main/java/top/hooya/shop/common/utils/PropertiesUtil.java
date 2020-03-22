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

	/**
	 * 其他错误状态码
	 */
	public static Integer PARAMS_ERROR_CODE;

	/**
	 * token验证失败状态码
	 */
	public static Integer TOKEN_VERIFY_FAIL;

	/**
	 * 是否删除状态码
	 */
	public static Integer DEL_FLAG;

	/**
	 * 文件服务器域名
	 */
	public static String FILE_DOMAIN;

	/**
	 * 文件路径
	 */
	public static String FILE_PATH;

	/**
	 * 待支付状态码
	 */
	public static Integer PAY_UNPAY;
	/**
	 * 已支付状态码
	 */
	public static Integer PAY_PAY;
	/**
	 * 退货状态码
	 */
	public static Integer PAY_RETURN;

	/**
	 * 类别DICT状态码
	 */
	public static Integer DICT_CATEGORY;



	/**
	 * 已确认收货状态码
	 */
	public static Integer ORDER_CONFIRM;
	/**
	 * 待确认收货状态码
	 */
	public static Integer ORDER_UNCONFIRM;

	/**
	 * APP_ID
	 */
	public static String APP_ID;
	/**
	 * 支付宝网关
	 */
	public static String ALIPAY_URL;

	/**
	 * 应付应用私钥
	 */
	public static String APP_PRIVATE_KEY;

	/**
	 * 支付宝公钥，由支付宝生成
	 */
	public static String ALIPAY_PUBLIC_KEY;

	/**
	 * 卖家ROLEId
	 */
	public static Integer ROLE_SELLER;

	/**
	 * 买家ROLEId
	 */
	public static Integer ROLE_BUYER;


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
	@Value("${TOKEN_VERIFY_FAIL}")
	public void setTokenVerifyFail(Integer tokenVerifyFail) {
		TOKEN_VERIFY_FAIL = tokenVerifyFail;
	}
	@Value("${PARAMS_ERROR_CODE}")
	public void setParamsErrorCode(Integer paramsErrorCode) {
		PARAMS_ERROR_CODE = paramsErrorCode;
	}

	@Value("${DEL_FLAG}")
	public void setDelFlag(Integer delFlag) {
		DEL_FLAG = delFlag;
	}
	@Value("${FILE_DOMAIN}")
	public void setFileDomain(String fileDomain) {
		FILE_DOMAIN = fileDomain;
	}
	@Value("${FILE_PATH}")
	public void setFilePath(String filePath) {
		FILE_PATH = filePath;
	}
	@Value("${PAY_UNPAY}")
	public void setPayUnpay(Integer payUnpay) {
		PAY_UNPAY = payUnpay;
	}
	@Value("${PAY_PAY}")
	public void setPayPay(Integer payPay) {
		PAY_PAY = payPay;
	}
	@Value("${PAY_RETURN}")
	public void setPayReturn(Integer payReturn) {
		PAY_RETURN = payReturn;
	}
	@Value("${DICT_CATEGORY}")
	public  void setDictCategory(Integer dictCategory) {
		DICT_CATEGORY = dictCategory;
	}
	@Value("${ORDER_CONFIRM}")
	public void setOrderConfirm(Integer orderConfirm) {
		ORDER_CONFIRM = orderConfirm;
	}
	@Value("${ORDER_UNCONFIRM}")
	public void setOrderUnconfirm(Integer orderUnconfirm) {
		ORDER_UNCONFIRM = orderUnconfirm;
	}
	@Value("${APP_ID}")
	public  void setAppId(String appId) {
		APP_ID = appId;
	}
	@Value("${ALIPAY_URL}")
	public void setAlipayUrl(String alipayUrl) {
		ALIPAY_URL = alipayUrl;
	}
	@Value("${APP_PRIVATE_KEY}")
	public void setAppPrivateKey(String appPrivateKey) {
		APP_PRIVATE_KEY = appPrivateKey;
	}
	@Value("${ALIPAY_PUBLIC_KEY}")
	public void setAlipayPublicKey(String alipayPublicKey) {
		ALIPAY_PUBLIC_KEY = alipayPublicKey;
	}
	@Value("${ROLE_SELLER}")
	public  void setRoleSeller(Integer roleSeller) {
		ROLE_SELLER = roleSeller;
	}
	@Value("${ROLE_BUYER}")
	public void setRoleBuyer(Integer roleBuyer) {
		ROLE_BUYER = roleBuyer;
	}
}
