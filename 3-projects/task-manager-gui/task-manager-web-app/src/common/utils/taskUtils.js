const valuesOf = dictionary => {
  return Object.keys(dictionary).map(key => {
    return dictionary[key];
  });
};

export const TASK_PRIORITY = {
  HIGH: 'HIGH',
  NORMAL: 'NORMAL',
  LOW: 'LOW',
};

export const TASK_PRIORITY_VALUES = valuesOf(TASK_PRIORITY);

export const TASK_STATUS = {
  SCHEDULED: 'SCHEDULED',
  FINISHED: 'FINISHED',
  CANCELLED: 'CANCELLED',
};

export const TASK_STATUS_VALUES = valuesOf(TASK_STATUS);

export const getScheduledTasks = (tasks = []) => {
  return tasks.filter(task => task.status === TASK_STATUS.SCHEDULED);
};

export const countScheduledTasks = tasks => {
  return getScheduledTasks(tasks).length;
};

export const getFinishedTasks = (tasks = []) => {
  return tasks.filter(task => task.status === TASK_STATUS.FINISHED);
};

export const countFinishedTasks = tasks => {
  return getFinishedTasks(tasks).length;
};

export const getCancelledTasks = (tasks = []) => {
  return tasks.filter(task => task.status === TASK_STATUS.CANCELLED);
};

export const countCancelledTasks = tasks => {
  return getCancelledTasks(tasks).length;
};
