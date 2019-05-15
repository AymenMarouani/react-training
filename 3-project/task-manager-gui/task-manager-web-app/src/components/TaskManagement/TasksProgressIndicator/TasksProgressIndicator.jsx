import React from 'react';
import Tooltip from '@material-ui/core/Tooltip';
import { countScheduledTasks, countFinishedTasks, countCancelledTasks } from '../../../common/utils/tasksUtils';
import styles from './TasksProgressIndicator.module.css';

export default function TasksProgressIndicator(props) {
  const { tasks } = props;
  const scheduledTasksCount = countScheduledTasks(tasks);
  const finishedTasksCount = countFinishedTasks(tasks);
  const cancelledTasksCount = countCancelledTasks(tasks);
  const totalTasksCount = tasks.length;
  const scheduledTasksPercent = totalTasksCount !== 0 ? (scheduledTasksCount / totalTasksCount) * 100 : 0;
  const finishedTasksPercent = totalTasksCount !== 0 ? (finishedTasksCount / totalTasksCount) * 100 : 0;
  const cancelledTasksPercent = totalTasksCount !== 0 ? (cancelledTasksCount / totalTasksCount) * 100 : 0;
  return (
    <React.Fragment>
      <div className={styles.mainContainer}>
        <Tooltip title={`${scheduledTasksPercent}% scheduled tasks`}>
          <div
            style={{
              backgroundColor: 'blue',
              marginRight: '1px',
              borderRadius: '5px',
              width: `${scheduledTasksPercent}%`,
            }}
          />
        </Tooltip>
        <Tooltip title={`${finishedTasksPercent}% finished tasks`}>
          <div
            style={{
              backgroundColor: 'green',
              marginRight: '1px',
              borderRadius: '5px',
              width: `${finishedTasksPercent}%`,
            }}
          />
        </Tooltip>
        <Tooltip title={`${cancelledTasksPercent}% cancelled tasks`}>
          <div style={{ backgroundColor: 'grey', borderRadius: '5px', width: `${cancelledTasksPercent}%` }} />
        </Tooltip>
      </div>
    </React.Fragment>
  );
}
