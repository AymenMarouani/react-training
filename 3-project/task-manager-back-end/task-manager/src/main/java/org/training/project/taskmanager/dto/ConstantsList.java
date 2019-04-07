package org.training.project.taskmanager.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
@ApiModel(description = "A list of info on constants available for this task management API.")
public class ConstantsList {

  @Singular
  @ApiModelProperty(notes = "The available constants")
  private List<Constant> constants;
}
