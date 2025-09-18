package suzzingv.suzzingv.apitask.global.response;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import suzzingv.suzzingv.apitask.global.response.dto.ErrorResponse;
import suzzingv.suzzingv.apitask.global.response.exceptionClass.CustomException;
import suzzingv.suzzingv.apitask.global.response.properties.ErrorCode;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
