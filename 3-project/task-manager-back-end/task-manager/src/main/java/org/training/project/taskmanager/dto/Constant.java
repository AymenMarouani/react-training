package org.training.project.taskmanager.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "A wrapper for the constant values used to query the task management API.")
public class Constant {

  @ApiModelProperty(notes = "Name of the constant", position = 1)
  private String code;

  @Builder.Default
  @ApiModelProperty(notes = "List of the possible values for this constant", position = 2)
  private List<String> values = new ArrayList<>();

  @ApiModelProperty(notes = "The default value that can be taken, if possible", position = 3)
  private String defaultValue;
}
