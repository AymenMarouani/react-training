export const TASK_PRIORITY = {
  HIGH: 'HIGH',
  NORMAL: 'NORMAL',
  LOW: 'LOW',
};

export const TASK_STATUS = {
  SCHEDULED: 'SCHEDULED',
  FINISHED: 'FINISHED',
  CANCELLED: 'CANCELLED',
};

export const getScheduledTasks = (tasks = []) => {
  return tasks.filter(task => task.status === 'SCHEDULED');
};

export const countScheduledTasks = tasks => {
  return getScheduledTasks(tasks).length;
};

export const getFinishedTasks = (tasks = []) => {
  return tasks.filter(task => task.status === 'FINISHED');
};

export const countFinishedTasks = tasks => {
  return getFinishedTasks(tasks).length;
};

export const getCancelledTasks = (tasks = []) => {
  return tasks.filter(task => task.status === 'CANCELLED');
};

export const countCancelledTasks = tasks => {
  return getCancelledTasks(tasks).length;
};
