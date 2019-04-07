package org.training.project.taskmanager.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "A list of tasks.")
public class TaskList {

  @Builder.Default
  @ApiModelProperty(notes = "The tasks in this list")
  private List<ResponseTaskDto> tasks = new ArrayList<>();
}
