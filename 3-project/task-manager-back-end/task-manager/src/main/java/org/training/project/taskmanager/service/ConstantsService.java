package org.training.project.taskmanager.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.training.project.taskmanager.dto.Constant;
import org.training.project.taskmanager.dto.ConstantsList;
import org.training.project.taskmanager.dto.Priority;
import org.training.project.taskmanager.dto.Status;
import org.training.project.taskmanager.dto.Window;

@Service
public class ConstantsService {

  public ConstantsList getAll() {
    final Constant taskStatus = Constant.builder()
        .code("status")
        .values(
            Arrays.stream(Status.values()).map(Status::name).collect(Collectors.toList())
        )
        .defaultValue(Status.SCHEDULED.name())
        .build();
    final Constant taskPriority = Constant.builder()
        .code("priority")
        .values(
            Arrays.stream(Priority.values()).map(Priority::name).collect(Collectors.toList())
        )
        .defaultValue(Priority.NORMAL.name())
        .build();
    final Constant statisticsWindow = Constant.builder()
        .code("window")
        .values(
            Arrays.stream(Window.values()).map(Window::name).collect(Collectors.toList())
        )
        .defaultValue(Window.DAY.name())
        .build();
    return ConstantsList.builder()
        .constant(taskStatus)
        .constant(taskPriority)
        .constant(statisticsWindow)
        .build();
  }
}
