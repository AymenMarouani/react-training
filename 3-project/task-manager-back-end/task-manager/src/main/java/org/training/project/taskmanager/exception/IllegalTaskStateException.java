package org.training.project.taskmanager.exception;

@SuppressWarnings(value = "unused")
public class IllegalTaskStateException extends RuntimeException {

  public IllegalTaskStateException() {
  }

  public IllegalTaskStateException(final String message) {
    super(message);
  }
}
