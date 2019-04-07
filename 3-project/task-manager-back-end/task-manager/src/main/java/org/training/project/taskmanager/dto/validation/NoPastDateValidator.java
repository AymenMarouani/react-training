package org.training.project.taskmanager.dto.validation;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoPastDateValidator implements ConstraintValidator<NotInThePast, LocalDate> {

  @Override
  public boolean isValid(final LocalDate inputDate,
      final ConstraintValidatorContext constraintValidatorContext) {
    final LocalDate currentDate = LocalDate.now();
    return inputDate.isAfter(currentDate) || inputDate.isEqual(currentDate);
  }
}
