package org.training.project.taskmanager.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.training.project.taskmanager.dto.Priority.NORMAL;
import static org.training.project.taskmanager.dto.Status.FINISHED;
import static org.training.project.taskmanager.dto.Status.SCHEDULED;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;
import org.training.project.taskmanager.dto.TaskDto;

public class TaskManagerControllerTest extends AbstractControllerTest {

  public TaskManagerControllerTest() {
    this.serviceName = "/tasks";
  }

  @Test
  public void should_return_bad_request_when_inserting_a_task_with_null_summary()
      throws Exception {
    // Given
    final TaskDto taskDto = TaskDto.builder()
        .description("A task with a null summary")
        .time(LocalTime.NOON)
        .build();
    // When
    performPost(taskDto)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_inserting_a_task_with_blank_summary()
      throws Exception {
    // Given
    final TaskDto taskDto = TaskDto.builder()
        .summary("")
        .description("Task with blank summary")
        .build();
    // When
    performPost(taskDto)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_inserting_a_task_with_date_in_the_past()
      throws Exception {
    // Given
    final LocalDate pastDate = LocalDate.now().minusMonths(3);
    final TaskDto taskDto = TaskDto.builder()
        .summary("A task with a date in the past")
        .date(pastDate)
        .time(LocalTime.NOON)
        .build();
    // When
    performPost(taskDto)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_ok_and_default_status_and_priority_when_inserting_a_valid_task()
      throws Exception {
    // Given
    final LocalDate plannedDate = LocalDate.now().plusMonths(3);
    final TaskDto taskDto = TaskDto.builder()
        .summary("A valid task")
        .date(plannedDate)
        .time(LocalTime.NOON)
        .build();
    // When
    performPost(taskDto)
        // Then
        .andExpect(ok)
        .andExpect(jsonPath("$.status", is(SCHEDULED.name())))
        .andExpect(jsonPath("$.priority", is(NORMAL.name())))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andReturn();
  }

  @Test
  public void should_return_not_found_when_updating_a_non_existing_task()
      throws Exception {
    // Given
    final Long id = 3L;
    final TaskDto taskDto = TaskDto.builder()
        .summary("A task supposed to be existing")
        .date(LocalDate.now())
        .time(LocalTime.NOON)
        .build();
    // When
    performPut(taskDto, id)
        // Then
        .andExpect(notFound)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_updating_a_task_with_a_null_summary()
      throws Exception {
    // Given
    final TaskDto taskDto = TaskDto.builder()
        .date(LocalDate.now())
        .time(LocalTime.NOON)
        .build();
    // When
    performPut(taskDto, 1L)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_updating_a_task_with_a_blank_summary()
      throws Exception {
    // Given
    final TaskDto taskDto = TaskDto.builder()
        .summary(" ")
        .date(LocalDate.now())
        .time(LocalTime.NOON)
        .build();
    // When
    performPut(taskDto, 1L)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_updating_with_date_in_the_past()
      throws Exception {
    // Given
    final LocalDate pastDate = LocalDate.now().minusMonths(3);
    final TaskDto taskDto = TaskDto.builder()
        .summary("A task with a date in the past")
        .date(pastDate)
        .time(LocalTime.NOON)
        .build();
    // When
    performPut(taskDto, 7L)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_updating_a_past_task()
      throws Exception {
    // Given
    final Long id = 9L;
    final LocalDate amendDate = LocalDate.now().plusWeeks(3);
    final TaskDto taskDto = TaskDto.builder()
        .summary("A task with a valid date but the task to update is in the past")
        .date(amendDate)
        .time(LocalTime.NOON)
        .build();
    // When
    performPut(taskDto, id)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_marking_a_future_task_as_done()
      throws Exception {
    // Given
    final Long id = 7L;
    final LocalDate amendDate = LocalDate.now().plusWeeks(3);
    final TaskDto taskDto = TaskDto.builder()
        .summary("A task in the future to be marked as done")
        .date(amendDate)
        .time(LocalTime.NOON)
        .status(FINISHED)
        .build();
    // When
    performPut(taskDto, id)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_ok_when_updating_a_task_with_correct_values()
      throws Exception {
    // Given
    final Long id = 5L;
    final String amendSummary = "A task with valid data for update";
    final LocalDate amendDate = LocalDate.now().plusWeeks(3);
    final LocalTime amendTime = LocalTime.NOON;
    final String description = "Task with correct data";
    final TaskDto taskDto = TaskDto.builder()
        .summary(amendSummary)
        .description(description)
        .date(amendDate)
        .time(amendTime)
        .priority(NORMAL)
        .build();
    // When
    performPut(taskDto, id)
        // Then
        .andExpect(ok)
        .andExpect(jsonPath("$.summary", is(amendSummary)))
        .andExpect(jsonPath("$.description", is(description)))
        .andExpect(jsonPath("$.date", is(amendDate.toString())))
        .andExpect(jsonPath("$.time", is(amendTime.toString())))
        .andExpect(jsonPath("$.status", is(SCHEDULED.name())))
        .andExpect(jsonPath("$.priority", is(NORMAL.name())))
        .andReturn();
  }

  @Test
  public void should_return_not_found_when_deleting_a_non_existing_task() throws Exception {
    // Given
    final Long id = 3L;
    // When
    performDelete(id)
        // Then
        .andExpect(notFound)
        .andReturn();
  }

  @Test
  public void should_return_bad_request_when_deleting_a_task_in_the_past() throws Exception {
    // Given
    final Long id = 9L;
    // When
    performDelete(id)
        // Then
        .andExpect(badRequest)
        .andReturn();
  }

  @Test
  public void should_return_ok_when_deleting_a_future_task() throws Exception {
    // Given
    final Long id = 11L;
    // When
    performDelete(id)
        // Then
        .andExpect(ok)
        .andReturn();
  }
}
