package br.com.leon.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    public static final String GENERIC_ERROR_MESSAGE
            = "An unexpected internal system error has occurred. Please try again and if the problem persists, contact your system administrator.";

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public HttpErrorInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var msg = "";
        var cause = ex.getBindingResult();
        var firstProblem = cause.getAllErrors().stream().findFirst();

        if (firstProblem.isPresent()) {
            msg = firstProblem.get().getDefaultMessage();
        }

        return createHttpErrorInfo(msg);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    public HttpErrorInfo handleTransactionSystemException(TransactionSystemException ex) {
        var cause = ex.getRootCause();
        var msg = "";
        if (cause instanceof ConstraintViolationException) {
            var constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            msg = constraintViolations.stream().findFirst().map(f -> f.getMessage()).orElse("");
        }
        return createHttpErrorInfo(msg);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public HttpErrorInfo handleConstraintExceptions(ConstraintViolationException ex) {

        var constraintViolations = ex.getConstraintViolations();
        var msg = constraintViolations.stream().findFirst().map(f -> f.getMessage()).orElse("");

        return createHttpErrorInfo(msg);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public HttpErrorInfo handleRuntimeExceptions(RuntimeException ex) {
        log.info("Message: {}", ex.getMessage());
        return createHttpErrorInfo(ex.getMessage());
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpErrorInfo handleExceptions(Exception ex) {
        log.info("Message: {}", ex);
        return createHttpErrorInfo(GENERIC_ERROR_MESSAGE);
    }

    private HttpErrorInfo createHttpErrorInfo(String msg) {
        final String message = msg;

        return HttpErrorInfo.builder()
                .message(message)
                .build();
    }

}
