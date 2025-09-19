package suzzingv.suzzingv.dev2.global.response;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import suzzingv.suzzingv.dev2.global.response.dto.ErrorResponse;
import suzzingv.suzzingv.dev2.global.response.exceptionClass.CustomException;
import suzzingv.suzzingv.dev2.global.response.properties.ErrorCode;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(
        CustomException e, HttpServletRequest request
    ) {
        log.error("CustomException 발생: {}", e);
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(
        Exception e) {
        log.error("예상치 못한 예외 발생: {}", e);
        return ErrorResponse.toResponseEntity(ErrorCode.SERVER_ERROR);
    }
}
