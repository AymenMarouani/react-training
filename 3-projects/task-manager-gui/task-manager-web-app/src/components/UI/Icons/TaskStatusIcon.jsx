import React from 'react';
import { SvgIcon } from '@material-ui/core';
import { TASK_STATUS } from '../../../common/utils/taskUtils';

export default function TaskStatusIcon(props) {
  const { taskStatus } = props;
  switch (taskStatus) {
    case TASK_STATUS.FINISHED:
      return (
        <SvgIcon>
          <path
            fill="#000000"
            d="M0.41,13.41L6,19L7.41,17.58L1.83,12M22.24,5.58L11.66,16.17L7.5,12L6.07,13.41L11.66,19L23.66,7M18,7L16.59,5.58L10.24,11.93L11.66,13.34L18,7Z"
          />
        </SvgIcon>
      );
    case TASK_STATUS.CANCELLED:
      return (
        <SvgIcon>
          <path
            fill="#000000"
            d="M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2M12,4A8,8 0 0,0 4,12C4,13.85 4.63,15.55 5.68,16.91L16.91,5.68C15.55,4.63 13.85,4 12,4M12,20A8,8 0 0,0 20,12C20,10.15 19.37,8.45 18.32,7.09L7.09,18.32C8.45,19.37 10.15,20 12,20Z"
          />
        </SvgIcon>
      );
    default:
      return (
        <SvgIcon>
          <path
            fill="#000000"
            d="M12,20A8,8 0 0,0 20,12A8,8 0 0,0 12,4A8,8 0 0,0 4,12A8,8 0 0,0 12,20M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22C6.47,22 2,17.5 2,12A10,10 0 0,1 12,2M12.5,7V12.25L17,14.92L16.25,16.15L11,13V7H12.5Z"
          />
        </SvgIcon>
      );
  }
}
