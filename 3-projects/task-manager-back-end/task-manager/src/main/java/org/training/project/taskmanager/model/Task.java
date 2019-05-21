package org.training.project.taskmanager.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskIdGenerator")
  @SequenceGenerator(name = "taskIdGenerator", sequenceName = "TASK_ID_SEQUENCE")
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String summary;

  private String description;

  @Column(nullable = false)
  private String priority;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false)
  private String status;
}
