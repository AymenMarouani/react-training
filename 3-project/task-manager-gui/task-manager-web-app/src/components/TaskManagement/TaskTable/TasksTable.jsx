import React from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

export default function TasksTable(props) {
  const { tasks } = props;
  return (
    <Table>
      <colgroup>
        <col style={{ width: '5%' }} />
        <col style={{ width: '40%' }} />
        <col style={{ width: '35%' }} />
        <col style={{ width: '15%' }} />
        <col style={{ width: '5%' }} />
        <col style={{ width: '5%' }} />
      </colgroup>
      <TableHead>
        <TableRow>
          <TableCell align="left">Priority</TableCell>
          <TableCell align="left">Summary</TableCell>
          <TableCell align="left">Description</TableCell>
          <TableCell align="left">Date</TableCell>
          <TableCell align="left">Time</TableCell>
          <TableCell align="left">Status</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {tasks.map(task => (
          <TableRow key={task.id}>
            <TableCell align="left">{task.priority}</TableCell>
            <TableCell align="left">{task.summary}</TableCell>
            <TableCell align="left">{task.description}</TableCell>
            <TableCell align="left">{task.date}</TableCell>
            <TableCell align="left">{task.time}</TableCell>
            <TableCell align="left">{task.status}</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
}
