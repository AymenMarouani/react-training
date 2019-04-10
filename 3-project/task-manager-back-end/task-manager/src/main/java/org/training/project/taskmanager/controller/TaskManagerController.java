package org.training.project.taskmanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.project.taskmanager.dto.ResponseTaskDto;
import org.training.project.taskmanager.dto.TaskDto;
import org.training.project.taskmanager.dto.TaskList;
import org.training.project.taskmanager.service.TaskManagerService;

@RestController
@RequestMapping(value = "${api.baseURL}/${api.version}/tasks", produces = APPLICATION_JSON_UTF8_VALUE)
@SuppressWarnings(value = "unused")
@Api(value = "Task Manager", description = "Operations on daily to do tasks.")
public class TaskManagerController {

  private final TaskManagerService taskManagerService;

  public TaskManagerController(final TaskManagerService taskManagerService) {
    this.taskManagerService = taskManagerService;
  }

  @GetMapping
  @ApiOperation(value = "Return the list of tasks for a given date or all the tasks if no date is provided"
      , response = TaskList.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
  })
  public ResponseEntity<TaskList> getByDate(
      @ApiParam(name = "date", value = "task date", format = "yyyy-MM-dd")
      @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate date) {
    if (date == null) {
      final TaskList allTasks = taskManagerService.getAll();
      return ResponseEntity.ok(allTasks);
    }
    final TaskList tasksByDate = taskManagerService.getByDate(date);
    return ResponseEntity.ok(tasksByDate);
  }

  @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation(value = "Create a new task", response = ResponseTaskDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully inserted task"),
      @ApiResponse(code = 401, message = "You are not authorized to do this operation"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 400, message =
          "Can happen for the following reasons:"
              + "\n - the task to insert is malformed"
              + "\n - some mandatory fields are missing"
              + "\n - the task is scheduled in the past"
              + "\n - the task is in the status FINISHED but in the future")
  })
  public ResponseEntity<TaskDto> create(
      @ApiParam(value = "Task to save by the application", required = true)
      @Valid @RequestBody final TaskDto scheduledTaskDto) {
    final ResponseTaskDto createdTaskDto = taskManagerService.create(scheduledTaskDto);
    return ResponseEntity.ok(createdTaskDto);
  }

  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
  @ApiOperation(value = "Update an existing task", response = ResponseTaskDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully updated task"),
      @ApiResponse(code = 401, message = "You are not authorized to do this operation"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The task to update is not found"),
      @ApiResponse(code = 400, message =
          "Can happen for the following reasons:"
              + "\n - the task to update is malformed"
              + "\n - some mandatory fields are missing"
              + "\n - the task to amend is in the past"
              + "\n - marking a future task as done")
  })
  public ResponseEntity<TaskDto> update(
      @ApiParam(value = "Task data for the update", required = true)
      @Valid @RequestBody final TaskDto scheduledTaskDto,
      @ApiParam(value = "Id of the task to update", required = true)
      @PathVariable final Long id) {
    final ResponseTaskDto updatedTaskDto = taskManagerService.update(scheduledTaskDto, id);
    return ResponseEntity.ok(updatedTaskDto);
  }

  @DeleteMapping(path = "/{id}")
  @ApiOperation(value = "Delete an existing task", response = Long.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully deleted task"),
      @ApiResponse(code = 401, message = "You are not authorized to do this operation"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The task to delete is not found"),
      @ApiResponse(code = 400, message = "Can happen if the task to delete is in the past")
  })
  public ResponseEntity<Long> delete(
      @ApiParam(value = "Id of the task to delete", required = true)
      @PathVariable final Long id) {
    taskManagerService.delete(id);
    return ResponseEntity.ok(id);
  }
}
