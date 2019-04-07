package org.training.project.taskmanager.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class RequestError {

  private String code;

  private String error;
}
