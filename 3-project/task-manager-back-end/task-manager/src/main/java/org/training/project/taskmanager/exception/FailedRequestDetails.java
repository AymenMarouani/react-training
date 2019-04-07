package org.training.project.taskmanager.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
class FailedRequestDetails {

  @Builder.Default
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime timeStamp = LocalDateTime.now();

  private String httpStatus;

  private int httpCode;

  private String message;

  @Builder.Default
  private List<RequestError> requestErrors = new ArrayList<>();

  @Builder.Default
  private List<RequestBodyValidationError> validationErrors = new ArrayList<>();
}
