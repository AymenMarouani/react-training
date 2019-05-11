import React from 'react';
import Tooltip from '@material-ui/core/Tooltip';

export default function TasksProgressIndicator(props) {
  const { tasks } = props;
  const totalTasksCount = tasks.length;
  const scheduledTasksCount = tasks.filter(task => task.status === 'SCHEDULED').length;
  const finishedTasksCount = tasks.filter(task => task.status === 'FINISHED').length;
  const cancelledTasksCount = tasks.filter(task => task.status === 'CANCELLED').length;
  const scheduledTasksPercent = totalTasksCount !== 0 ? (scheduledTasksCount / totalTasksCount) * 100 : 0;
  const finishedTasksPercent = totalTasksCount !== 0 ? (finishedTasksCount / totalTasksCount) * 100 : 0;
  const cancelledTasksPercent = totalTasksCount !== 0 ? (cancelledTasksCount / totalTasksCount) * 100 : 0;
  return (
    <React.Fragment>
      <div
        style={{
          display: 'flex',
          flexDirection: 'row',
          height: '15px',
          marginTop: '2px',
          marginBottom: '2px',
        }}
      >
        <Tooltip title={`${scheduledTasksPercent}% scheduled tasks`}>
          <div style={{ backgroundColor: 'blue', width: `${scheduledTasksPercent}%` }} />
        </Tooltip>
        <Tooltip title={`${finishedTasksPercent}% finished tasks`}>
          <div style={{ backgroundColor: 'green', width: `${finishedTasksPercent}%` }} />
        </Tooltip>
        <Tooltip title={`${cancelledTasksPercent}% cancelled tasks`}>
          <div style={{ backgroundColor: 'yellow', width: `${cancelledTasksPercent}%` }} />
        </Tooltip>
      </div>
    </React.Fragment>
  );
}
