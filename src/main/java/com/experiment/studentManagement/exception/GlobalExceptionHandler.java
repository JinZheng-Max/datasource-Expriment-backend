package com.experiment.studentManagement.exception;

import com.experiment.studentManagement.constant.ErrorMessages;
import com.experiment.studentManagement.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BizException.class)
	public Result<?> handleBizException(BizException ex) {
		log.warn("业务异常: {}", ex.getMessage());
		return Result.error(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> handleValidationException(MethodArgumentNotValidException ex) {
		String msg = ex.getBindingResult().getFieldErrors().stream()
				.findFirst()
				.map(err -> err.getField() + ": " + err.getDefaultMessage())
				.orElse(ErrorMessages.BAD_REQUEST);
		log.warn("参数校验失败: {}", msg);
		return Result.error(msg);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result<?> handleNotReadable(HttpMessageNotReadableException ex) {
		log.warn("请求体解析失败: {}", ex.getMessage());
		return Result.error(ErrorMessages.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<?> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
		log.warn("不支持的请求方法: {}, uri={}, supported={}", ex.getMethod(), request.getRequestURI(), ex.getSupportedHttpMethods());
		return Result.error(ErrorMessages.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Result<?> handleIllegalArgument(IllegalArgumentException ex) {
		log.warn("非法参数: {}", ex.getMessage());
		return Result.error(ex.getMessage() != null ? ex.getMessage() : ErrorMessages.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public Result<?> handleRuntime(RuntimeException ex) {
		log.error("运行时异常: {}", ex.getMessage(), ex);
		return Result.error(ex.getMessage() != null ? ex.getMessage() : ErrorMessages.SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public Result<?> handleOther(Exception ex) {
		log.error("未知异常: {}", ex.getMessage(), ex);
		return Result.error(ErrorMessages.SERVER_ERROR);
	}
}


