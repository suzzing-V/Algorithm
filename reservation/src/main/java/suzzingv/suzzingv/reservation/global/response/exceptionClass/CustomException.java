package suzzingv.suzzingv.dev2.global.response.exceptionClass;

import lombok.Getter;
import suzzingv.suzzingv.dev2.global.response.properties.ErrorCode;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Throwable cause;

    public CustomException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        this.errorCode = errorCode;
        this.cause = cause;
    }
}
