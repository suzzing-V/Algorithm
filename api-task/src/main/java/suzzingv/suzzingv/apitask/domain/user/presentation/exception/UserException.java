package suzzingv.suzzingv.apitask.domain.user.presentation.exception;

import suzzingv.suzzingv.apitask.global.response.exceptionClass.CustomException;
import suzzingv.suzzingv.apitask.global.response.properties.ErrorCode;

public class UserException extends CustomException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
