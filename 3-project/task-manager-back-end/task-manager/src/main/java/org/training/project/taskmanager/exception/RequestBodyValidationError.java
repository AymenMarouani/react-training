package org.training.project.taskmanager.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class RequestBodyValidationError {

  private String field;

  private String error;

  private Object value;
}
