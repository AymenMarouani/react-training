package org.training.project.taskmanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.project.taskmanager.dto.Statistics;
import org.training.project.taskmanager.dto.Window;
import org.training.project.taskmanager.service.TaskStatisticsService;

@RestController
@RequestMapping(value = "${api.baseURL}/${api.version}/statistics", produces = APPLICATION_JSON_UTF8_VALUE)
@SuppressWarnings(value = "unused")
@Api(value = "Task Statistics", description = "Statistics on the tasks for a given time window.")
public class TaskStatisticsController {

  private final TaskStatisticsService taskStatisticsService;

  public TaskStatisticsController(final TaskStatisticsService taskStatisticsService) {
    this.taskStatisticsService = taskStatisticsService;
  }

  @GetMapping
  @ApiOperation(value = "Return a statistics on the tasks for a given time window or"
      + " the statistics for the current day when no parameter is provided"
      , response = Statistics.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
  })
  public ResponseEntity<Statistics> getStatistics(
      @ApiParam(name = "window", value = "time window")
      @RequestParam(value = "window", required = false) final Window window) {
    final Statistics taskStatistics = taskStatisticsService.getStatistics(window);
    return ResponseEntity.ok(taskStatistics);
  }
}
