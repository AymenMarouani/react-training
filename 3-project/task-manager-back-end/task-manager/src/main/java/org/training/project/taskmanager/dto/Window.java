package org.training.project.taskmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings(value = "unused")
public enum Window {
  DAY {
    @Override
    public LocalDateTime computeStartDate() {
      return LocalDate.now().atTime(LocalTime.MIDNIGHT);
    }
  },
  WEEK {
    @Override
    public LocalDateTime computeStartDate() {
      return LocalDate.now().minusWeeks(1).atTime(LocalTime.MIDNIGHT);
    }
  },
  MONTH {
    @Override
    public LocalDateTime computeStartDate() {
      return LocalDate.now().minusMonths(1).atTime(LocalTime.MIDNIGHT);
    }
  },
  YEAR {
    @Override
    public LocalDateTime computeStartDate() {
      return LocalDate.now().minusYears(1).atTime(LocalTime.MIDNIGHT);
    }
  };

  public abstract LocalDateTime computeStartDate();

  public LocalDateTime computeEndDate() {
    return LocalDate.now().atTime(LocalTime.MAX);
  }
}
