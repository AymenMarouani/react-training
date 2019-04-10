package org.training.project.taskmanager.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings(value = "unused")
public class ServiceErrorHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException validationException,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    final String httpStatus = status.name();
    final int httpCode = status.value();
    final List<RequestBodyValidationError> fieldValidationErrors =
        getFieldsValidationRelatedErrors(validationException);
    final List<RequestError> requestBodyValidationErrors =
        getRequestBodyValidationErrors(validationException);
    final FailedRequestDetails errorDetails = FailedRequestDetails.builder()
        .httpStatus(httpStatus)
        .httpCode(httpCode)
        .message("Invalid request body")
        .validationErrors(fieldValidationErrors)
        .requestErrors(requestBodyValidationErrors)
        .build();
    return new ResponseEntity<>(errorDetails, BAD_REQUEST);
  }

  @ExceptionHandler(TaskNotFoundException.class)
  protected ResponseEntity<Object> handleTaskNotFoundException(
      TaskNotFoundException taskNotFoundException) {
    return buildCustomExceptionResponseEntity(taskNotFoundException, "TASK_NOT_FOUND", NOT_FOUND);
  }

  @ExceptionHandler(IllegalTaskStateException.class)
  protected ResponseEntity<Object> handleArchivedTaskException(
      IllegalTaskStateException illegalTaskStateException) {
    return buildCustomExceptionResponseEntity(illegalTaskStateException, "ILLEGAL_TASK_STATUS",
        BAD_REQUEST);
  }

  private List<RequestBodyValidationError> getFieldsValidationRelatedErrors(
      final MethodArgumentNotValidException validationException) {
    return validationException.getBindingResult()
        .getFieldErrors().stream()
        .map(this::mapToFieldError)
        .collect(Collectors.toList());
  }

  private List<RequestError> getRequestBodyValidationErrors(
      final MethodArgumentNotValidException validationException) {
    return validationException.getBindingResult()
        .getGlobalErrors().stream()
        .map(this::mapToRequestError)
        .collect(Collectors.toList());
  }

  private RequestBodyValidationError mapToFieldError(final FieldError error) {
    final String fieldName = error.getField();
    final String errorDefaultMessage = error.getDefaultMessage();
    final Object rejectedValue = error.getRejectedValue();
    return RequestBodyValidationError.builder()
        .field(fieldName)
        .error(errorDefaultMessage)
        .value(rejectedValue)
        .build();
  }

  private RequestError mapToRequestError(final ObjectError objectError) {
    final String errorCode = objectError.getCode();
    final String errorDefaultMessage = objectError.getDefaultMessage();
    return RequestError.builder()
        .code(errorCode)
        .error(errorDefaultMessage)
        .build();
  }

  private <T extends Exception> ResponseEntity<Object> buildCustomExceptionResponseEntity(
      final T exception,
      final String code,
      final HttpStatus httpStatus) {
    final String exceptionMessage = exception.getMessage();
    final String httpStatusName = httpStatus.name();
    final int httpCode = httpStatus.value();
    final RequestError exceptionRequestError = RequestError.builder()
        .code(code)
        .error(exceptionMessage)
        .build();
    final List<RequestError> requestErrors = Collections.singletonList(exceptionRequestError);
    final FailedRequestDetails errorDetails = FailedRequestDetails.builder()
        .httpStatus(httpStatusName)
        .httpCode(httpCode)
        .message(exceptionMessage)
        .requestErrors(requestErrors)
        .build();
    return new ResponseEntity<>(errorDetails, httpStatus);
  }
}
