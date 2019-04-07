package org.training.project.taskmanager.dto.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {NoPastDateValidator.class})
@SuppressWarnings(value = "unused")
public @interface NotInThePast {

  String message() default "Date must not be in the past";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
