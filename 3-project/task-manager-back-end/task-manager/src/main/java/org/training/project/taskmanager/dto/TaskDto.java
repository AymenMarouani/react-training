package org.training.project.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.training.project.taskmanager.dto.validation.NotInThePast;
import org.training.project.taskmanager.dto.validation.Scheduled;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Scheduled
@ApiModel(description = "A task for a given date.")
public class TaskDto {

  @NotBlank(message = "Task summary is mandatory and must not be blank or empty")
  @Length(max = 150, message = "Task summary must not exceed 150 characters")
  @ApiModelProperty(notes = "Short summary describing the task's objective", required = true, position = 1)
  private String summary;

  @ApiModelProperty(notes = "Additional notes and details on the task", position = 2)
  private String description;

  @NotNull(message = "Task priority is mandatory")
  @Builder.Default
  @ApiModelProperty(notes = "The priority for the task, it has the value NORMAL by default"
      , required = true, position = 3)
  private Priority priority = Priority.NORMAL;

  @NotNull(message = "Task date must be provided")
  @NotInThePast
  @Builder.Default
  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  @ApiModelProperty(notes = "The date for the task, current date by default", required = true
      , dataType = "org.joda.time.LocalDate", example = "1970-01-01", position = 4)
  private LocalDate date = LocalDate.now();

  @NotNull(message = "The time for the Task must be provided")
  @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
  @ApiModelProperty(notes = "The time for the task", required = true, dataType = "org.joda.time.LocalTime"
      , example = "09:00", position = 5)
  private LocalTime time;

  @NotNull(message = "Task status must be provided")
  @Builder.Default
  @ApiModelProperty(notes = "The status of the task, it's SCHEDULED by default", required = true, position = 6)
  private Status status = Status.SCHEDULED;
}
