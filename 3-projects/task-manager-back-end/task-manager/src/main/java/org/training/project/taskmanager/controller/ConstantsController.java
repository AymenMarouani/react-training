package org.training.project.taskmanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.project.taskmanager.dto.ConstantsList;
import org.training.project.taskmanager.service.ConstantsService;

@RestController
@RequestMapping(value = "${api.baseURL}/${api.version}/constants", produces = APPLICATION_JSON_UTF8_VALUE)
@SuppressWarnings(value = "unused")
@Api(value = "API Constants", description = "Exposes the available constants for the API.")
public class ConstantsController {

  private final ConstantsService constantsService;

  public ConstantsController(final ConstantsService constantsService) {
    this.constantsService = constantsService;
  }

  @GetMapping
  @ApiOperation(value = "Return the list of all constants available in this API", response = ConstantsList.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved list"),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
  })
  public ResponseEntity<ConstantsList> getAll() {
    final ConstantsList constantsList = constantsService.getAll();
    return ResponseEntity.ok(constantsList);
  }
}
