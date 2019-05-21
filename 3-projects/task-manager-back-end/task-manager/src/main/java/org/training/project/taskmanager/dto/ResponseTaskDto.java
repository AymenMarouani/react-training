package org.training.project.taskmanager.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "A task given as a response by the API.")
public class ResponseTaskDto extends TaskDto {

  @ApiModelProperty(notes = "Technical identifier of the persisted task")
  private Long id;

  @Builder(builderMethodName = "getBuilder")
  ResponseTaskDto(
      final String summary,
      final String description,
      final Priority priority,
      final LocalDate date,
      final LocalTime time,
      final Status status,
      final Long id) {
    super(summary, description, priority, date, time, status);
    this.id = id;
  }
}
