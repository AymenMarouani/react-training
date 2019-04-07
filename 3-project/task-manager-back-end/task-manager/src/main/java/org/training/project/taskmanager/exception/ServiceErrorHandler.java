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
    String httpStatus = status.name();
    int httpCode = status.value();
    final List<RequestBodyValidationError> validationErrors =
        getRequestBodyValidationRelatedErrors(validationException);
    final FailedRequestDetails errorDetails = FailedRequestDetails.builder()
        .httpStatus(httpStatus)
        .httpCode(httpCode)
        .message("Invalid request body")
        .validationErrors(validationErrors)
        .build();
    return new ResponseEntity<>(errorDetails, BAD_REQUEST);
  }

  @ExceptionHandler(TaskNotFoundException.class)
  protected ResponseEntity<Object> handleTaskNotFoundException(
      TaskNotFoundException taskNotFoundException) {
    return buildCustomExceptionResponseEntity(taskNotFoundException, "TASK_NOT_FOUND", NOT_FOUND);
  }

  @ExceptionHandler(ArchivedTaskException.class)
  protected ResponseEntity<Object> handleArchivedTaskException(
      ArchivedTaskException archivedTaskException) {
    return buildCustomExceptionResponseEntity(archivedTaskException, "ARCHIVED_TASK", BAD_REQUEST);
  }

  private List<RequestBodyValidationError> getRequestBodyValidationRelatedErrors(
      final MethodArgumentNotValidException validationException) {
    return validationException.getBindingResult()
        .getFieldErrors().stream()
        .map(this::mapToFieldError)
        .collect(Collectors.toList());
  }

  private RequestBodyValidationError mapToFieldError(final FieldError error) {
    return RequestBodyValidationError.builder()
        .field(error.getField())
        .error(error.getDefaultMessage())
        .value(error.getRejectedValue())
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
