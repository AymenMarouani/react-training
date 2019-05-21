package org.training.project.taskmanager.dto.validation;

import static org.training.project.taskmanager.dto.Status.FINISHED;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.training.project.taskmanager.dto.TaskDto;

public class ScheduledTaskValidator implements ConstraintValidator<Scheduled, TaskDto> {

  @Override
  public boolean isValid(final TaskDto taskDto,
      final ConstraintValidatorContext constraintValidatorContext) {
    return taskDto != null && notSetToDoneInTheFutue(taskDto);
  }

  private boolean notSetToDoneInTheFutue(final TaskDto taskDto) {
    final LocalDate currentDate = LocalDate.now();
    final LocalDate taskDate = taskDto.getDate();
    return taskDate.isEqual(currentDate) || (taskDate.isAfter(currentDate)
        && taskDto.getStatus() != FINISHED);
  }
}
