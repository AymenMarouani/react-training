package org.training.project.taskmanager.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.project.taskmanager.dto.ResponseTaskDto;
import org.training.project.taskmanager.dto.TaskDto;
import org.training.project.taskmanager.dto.TaskList;
import org.training.project.taskmanager.dto.mapper.TaskToTaskDtoMapper;
import org.training.project.taskmanager.exception.ArchivedTaskException;
import org.training.project.taskmanager.exception.TaskNotFoundException;
import org.training.project.taskmanager.model.Task;
import org.training.project.taskmanager.repository.TaskRepository;

@Service
@Transactional
public class TaskManagerService {

  private final TaskRepository taskRepository;

  public TaskManagerService(final TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Transactional(readOnly = true)
  public TaskList getAll() {
    final List<Task> allTasks = taskRepository.findAll();
    return TaskToTaskDtoMapper.mapToTaskList(allTasks);
  }

  @Transactional(readOnly = true)
  public TaskList getByDate(final LocalDate date) {
    final LocalDateTime startDate = date.atTime(LocalTime.MIDNIGHT);
    final LocalDateTime endDate = date.atTime(LocalTime.MAX);
    final List<Task> byDateTasks = taskRepository.findAllByDateBetween(startDate, endDate);
    return TaskToTaskDtoMapper.mapToTaskList(byDateTasks);
  }

  public ResponseTaskDto create(final TaskDto taskDto) {
    final Task taskToSave = TaskToTaskDtoMapper.mapToTask(taskDto);
    final Task savedTask = taskRepository.save(taskToSave);
    return TaskToTaskDtoMapper.mapToResponseTaskDto(savedTask);
  }

  public ResponseTaskDto update(final TaskDto taskDto, final Long id) {
    return taskRepository.findById(id)
        .map(task -> updateTask(task, taskDto))
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  public void delete(final Long id) {
    final Optional<Task> taskToDeleteOptional = taskRepository.findById(id);
    if (taskToDeleteOptional.isPresent()) {
      final Task taskToDelete = taskToDeleteOptional.get();
      deleteTask(taskToDelete);
    } else {
      throw new TaskNotFoundException(id);
    }
  }

  private void deleteTask(final Task task) {
    verifyTask(task);
    taskRepository.delete(task);
  }

  private ResponseTaskDto updateTask(final Task task, final TaskDto taskDto) {
    final Long id = task.getId();
    verifyTask(task);
    final Task refreshTask = TaskToTaskDtoMapper.mapToTask(taskDto, id);
    final Task updatedTask = taskRepository.save(refreshTask);
    return TaskToTaskDtoMapper.mapToResponseTaskDto(updatedTask);
  }

  private void verifyTask(final Task task) {
    final LocalDate currentDate = LocalDate.now();
    final LocalDate taskDate = task.getDate().toLocalDate();
    if (taskDate.isBefore(currentDate)) {
      throw new ArchivedTaskException(task.getId());
    }
  }
}
