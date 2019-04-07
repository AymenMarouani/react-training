package org.training.project.taskmanager.service;

import static org.training.project.taskmanager.dto.Status.CANCELLED;
import static org.training.project.taskmanager.dto.Status.FINISHED;
import static org.training.project.taskmanager.dto.Status.SCHEDULED;
import static org.training.project.taskmanager.dto.Window.DAY;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.project.taskmanager.dto.Statistics;
import org.training.project.taskmanager.dto.Status;
import org.training.project.taskmanager.dto.Window;
import org.training.project.taskmanager.model.Task;
import org.training.project.taskmanager.repository.TaskRepository;

@Service
@Transactional
public class TaskStatisticsService {

  private final TaskRepository taskRepository;

  public TaskStatisticsService(final TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Statistics getStatistics(final Window window) {
    if (window == null) {
      return getStatistics(DAY);
    }
    final LocalDateTime startDate = window.computeStartDate();
    final LocalDateTime endDate = window.computeEndDate();
    List<Task> tasks = taskRepository.findAllByDateBetween(startDate, endDate);
    final Long totalNumberOfTasks = (long) tasks.size();
    final Long numberOfScheduledTasks = countTasksByType(tasks, SCHEDULED);
    final Long numberOfFinishedTasks = countTasksByType(tasks, FINISHED);
    final Long numberOfCancelledTasks = countTasksByType(tasks, CANCELLED);
    return Statistics.builder()
        .type(window.name())
        .numberOfTasks(totalNumberOfTasks)
        .numberOfScheduledTasks(numberOfScheduledTasks)
        .numberOfFinishedTasks(numberOfFinishedTasks)
        .numberOfCancelledTasks(numberOfCancelledTasks)
        .build();
  }

  private Long countTasksByType(final List<Task> tasks, final Status status) {
    return tasks.stream()
        .filter(task -> task.getStatus().equals(status.name()))
        .count();
  }
}
