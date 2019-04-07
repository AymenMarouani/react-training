package org.training.project.taskmanager.dto.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.training.project.taskmanager.dto.Priority;
import org.training.project.taskmanager.dto.ResponseTaskDto;
import org.training.project.taskmanager.dto.Status;
import org.training.project.taskmanager.dto.TaskDto;
import org.training.project.taskmanager.dto.TaskList;
import org.training.project.taskmanager.model.Task;

@UtilityClass
public class TaskToTaskDtoMapper {

  public static ResponseTaskDto mapToResponseTaskDto(final Task task) {
    final Long id = task.getId();
    final String summary = task.getSummary();
    final String description = task.getDescription();
    final Priority priority = Priority.valueOf(task.getPriority());
    final LocalDate date = task.getDate().toLocalDate();
    final LocalTime time = task.getDate().toLocalTime();
    final Status status = Status.valueOf(task.getStatus());
    return ResponseTaskDto.getBuilder()
        .id(id)
        .summary(summary)
        .description(description)
        .priority(priority)
        .date(date)
        .time(time)
        .status(status)
        .build();
  }

  public static TaskList mapToTaskList(List<Task> tasks) {
    List<ResponseTaskDto> taskDtos = tasks.stream()
        .map(TaskToTaskDtoMapper::mapToResponseTaskDto)
        .collect(Collectors.toList());
    return TaskList.builder()
        .tasks(taskDtos)
        .build();
  }

  public static Task mapToTask(final TaskDto taskDto) {
    final String summary = taskDto.getSummary();
    final String description = taskDto.getDescription();
    final String priority = taskDto.getPriority().name();
    final LocalDateTime date = LocalDateTime.of(taskDto.getDate(), taskDto.getTime());
    final String status = taskDto.getStatus().name();
    return Task.builder()
        .summary(summary)
        .description(description)
        .priority(priority)
        .date(date)
        .status(status)
        .build();
  }

  public static Task mapToTask(final TaskDto taskDto, final Long id) {
    final String summary = taskDto.getSummary();
    final String description = taskDto.getDescription();
    final String priority = taskDto.getPriority().name();
    final LocalDateTime date = LocalDateTime.of(taskDto.getDate(), taskDto.getTime());
    final String status = taskDto.getStatus().name();
    return Task.builder()
        .id(id)
        .summary(summary)
        .description(description)
        .priority(priority)
        .date(date)
        .status(status)
        .build();
  }
}
