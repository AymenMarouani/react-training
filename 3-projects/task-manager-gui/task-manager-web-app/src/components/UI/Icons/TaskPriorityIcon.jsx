import React from 'react';
import { SvgIcon } from '@material-ui/core';
import { TASK_PRIORITY } from '../../../common/utils/tasksUtils';

export default function TaskPriorityIcon(props) {
  const { taskPriority } = props;
  switch (taskPriority) {
    case TASK_PRIORITY.HIGH:
      return (
        <SvgIcon>
          <path d="M16.59,9.42L12,4.83L7.41,9.42L6,8L12,2L18,8L16.59,9.42M16.59,15.42L12,10.83L7.41,15.42L6,14L12,8L18,14L16.59,15.42M16.59,21.42L12,16.83L7.41,21.42L6,20L12,14L18,20L16.59,21.42Z" />
        </SvgIcon>
      );
    case TASK_PRIORITY.LOW:
      return (
        <SvgIcon>
          <path d="M7.41,15.41L12,10.83L16.59,15.41L18,14L12,8L6,14L7.41,15.41Z" />
        </SvgIcon>
      );
    default:
      return (
        <SvgIcon>
          <path d="M7.41,18.41L6,17L12,11L18,17L16.59,18.41L12,13.83L7.41,18.41M7.41,12.41L6,11L12,5L18,11L16.59,12.41L12,7.83L7.41,12.41Z" />
        </SvgIcon>
      );
  }
}
