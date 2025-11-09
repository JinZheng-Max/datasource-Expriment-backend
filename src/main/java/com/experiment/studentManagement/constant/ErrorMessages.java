package com.experiment.studentManagement.constant;

public final class ErrorMessages {

	private ErrorMessages() {}

	public static final String BAD_REQUEST = "请求参数不合法";
	public static final String UNAUTHORIZED = "未认证或令牌无效";
	public static final String FORBIDDEN = "无权限访问";
	public static final String NOT_FOUND = "资源不存在";
	public static final String METHOD_NOT_ALLOWED = "请求方法不被允许";
	public static final String SERVER_ERROR = "服务器开小差了，请稍后重试";

	// 业务相关
	public static final String USERNAME_OR_PASSWORD_ERROR = "用户名或密码错误";
	public static final String USER_NOT_FOUND = "用户不存在";
	public static final String TOKEN_MISSING = "未提供令牌";
	public static final String TOKEN_INVALID = "令牌无效或已过期";
}


