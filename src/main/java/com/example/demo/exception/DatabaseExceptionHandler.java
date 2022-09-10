package com.example.demo.exception;

import com.example.demo.helper.APIResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;
import java.util.Map;

@ControllerAdvice
@Slf4j
@ResponseBody
public class DatabaseExceptionHandler {
    // https://stackoverflow.com/questions/72665235/how-to-catch-connection-refused-exception-of-spring-boot-data-jpa
    // https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
    @ExceptionHandler(value = {CannotCreateTransactionException.class})
    public ResponseEntity<?> cannotCreateTransactionException(CannotCreateTransactionException e, WebRequest request) {
        if (e.contains(ConnectException.class)) {
                log.debug("ConnectException {}", e.getMessage());

                Map<String, Object> res = APIResponseUtils.buildAPIError(HttpStatus.SERVICE_UNAVAILABLE.value(),  "SERVICE_UNAVAILABLE. see log for more detail");
                return new ResponseEntity<>(res, HttpStatus.SERVICE_UNAVAILABLE);
            } else {
                Map<String, Object> res = APIResponseUtils.buildAPIError(HttpStatus.INTERNAL_SERVER_ERROR.value(),  "INTERNAL_SERVER_ERROR");
                return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
