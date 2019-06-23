import React from 'react';
import PropTypes from 'prop-types';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { taskPropType as task } from '../../../prop-types';
import { TaskPriorityIcon, TaskStatusIcon } from '../../UI';
import styles from './TasksTable.styles';

export default function TasksTable(props) {
  const { tasks } = props;
  return (
    <Table>
      <colgroup>
        <col style={styles.priorityColumn} />
        <col style={styles.summaryColumn} />
        <col style={styles.descriptionColumn} />
        <col style={styles.dateColumn} />
        <col style={styles.timeColumn} />
        <col style={styles.statusColumn} />
      </colgroup>
      <TableHead>
        <TableRow>
          <TableCell style={styles.tableHeader} align="left">
            Priority
          </TableCell>
          <TableCell style={styles.tableHeader} align="left">
            Summary
          </TableCell>
          <TableCell style={styles.tableHeader} align="left">
            Description
          </TableCell>
          <TableCell style={styles.tableHeader} align="left">
            Date
          </TableCell>
          <TableCell style={styles.tableHeader} align="left">
            Time
          </TableCell>
          <TableCell style={styles.tableHeader} align="left">
            Status
          </TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {tasks.map(task => (
          <TableRow key={task.id}>
            <TableCell style={styles.rowContent} align="left">
              <TaskPriorityIcon taskPriority={task.priority} />
            </TableCell>
            <TableCell style={styles.rowContent} align="left">
              {task.summary}
            </TableCell>
            <TableCell style={styles.rowContent} align="left">
              {task.description}
            </TableCell>
            <TableCell style={styles.rowContent} align="left">
              {task.date}
            </TableCell>
            <TableCell style={styles.rowContent} align="left">
              {task.time}
            </TableCell>
            <TableCell style={styles.rowContent} align="left">
              <TaskStatusIcon taskStatus={task.status} />
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
}

TasksTable.propTypes = {
  tasks: PropTypes.arrayOf(task),
};
