package org.training.project.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Statistics for a given period on tasks.")
public class Statistics {

  @Builder.Default
  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  @ApiModelProperty(notes = "Date from where the statistics on tasks is taken", position = 1)
  private LocalDate date = LocalDate.now();

  @ApiModelProperty(notes = "Time window for the statistics", position = 2)
  private String type;

  @ApiModelProperty(notes = "Total number of tasks in the given period", position = 3)
  private Long numberOfTasks;

  @ApiModelProperty(notes = "Number of the remaining scheduled tasks", position = 4)
  private Long numberOfScheduledTasks;

  @ApiModelProperty(notes = "Number of the finished tasks", position = 5)
  private Long numberOfFinishedTasks;

  @ApiModelProperty(notes = "Number of the cancelled tasks", position = 6)
  private Long numberOfCancelledTasks;
}
