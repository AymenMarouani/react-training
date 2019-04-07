package org.training.project.taskmanager.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.training.project.taskmanager.model.Task;

@Repository
@SuppressWarnings(value = "unused")
public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findAllByDateBetween(final LocalDateTime startDate, final LocalDateTime endDate);
}
