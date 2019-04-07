package org.training.project.taskmanager.exception;

@SuppressWarnings(value = "unused")
public class ArchivedTaskException extends RuntimeException {

  public ArchivedTaskException() {
    super();
  }

  public ArchivedTaskException(final Long id) {
    super("Task with id: " + id + " is already scheduled in the past");
  }
}
