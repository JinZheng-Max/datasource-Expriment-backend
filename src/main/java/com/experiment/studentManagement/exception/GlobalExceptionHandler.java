package com.experiment.studentManagement.exception;

import com.experiment.studentManagement.constant.ErrorMessages;
import com.experiment.studentManagement.result.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BizException.class)
	public Result<?> handleBizException(BizException ex) {
		return Result.error(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> handleValidationException(MethodArgumentNotValidException ex) {
		String msg = ex.getBindingResult().getFieldErrors().stream()
				.findFirst()
				.map(err -> err.getField() + ": " + err.getDefaultMessage())
				.orElse(ErrorMessages.BAD_REQUEST);
		return Result.error(msg);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result<?> handleNotReadable(HttpMessageNotReadableException ex) {
		return Result.error(ErrorMessages.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<?> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
		return Result.error(ErrorMessages.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Result<?> handleIllegalArgument(IllegalArgumentException ex) {
		return Result.error(ex.getMessage() != null ? ex.getMessage() : ErrorMessages.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public Result<?> handleRuntime(RuntimeException ex) {
		return Result.error(ex.getMessage() != null ? ex.getMessage() : ErrorMessages.SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public Result<?> handleOther(Exception ex) {
		return Result.error(ErrorMessages.SERVER_ERROR);
	}
}


