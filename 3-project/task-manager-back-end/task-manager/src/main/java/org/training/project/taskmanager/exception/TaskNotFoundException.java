package org.training.project.taskmanager.exception;

@SuppressWarnings(value = "unused")
public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException() {
    super();
  }

  public TaskNotFoundException(final Long id) {
    super("The task with id: " + id + " has not been found");
  }
}
