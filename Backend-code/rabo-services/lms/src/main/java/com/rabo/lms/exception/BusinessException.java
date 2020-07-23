package com.rabo.lms.exception;

public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ErrorCode errorCode;
    String message;
    Exception originalException;

    public BusinessException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BusinessException(ErrorCode errorCode, String message, Exception exception) {
        this.errorCode = errorCode;
        this.message = message;
        this.originalException = exception;
    }
}