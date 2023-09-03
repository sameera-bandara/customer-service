package com.sameera.customerservice.presentation;

import com.sameera.customerservice.external.service.CustomerNotFoundException;
import com.sameera.swagger.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
       log.error(exception.getMessage());

       ErrorResponse err = new ErrorResponse();
       err.code("");
       err.message(exception.getMessage());
       err.detail(exception.getMessage());

       return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        log.error(exception.getMessage());

        ErrorResponse err = new ErrorResponse();
        err.code("");
        err.message(exception.getMessage());
        err.detail(exception.getMessage());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
