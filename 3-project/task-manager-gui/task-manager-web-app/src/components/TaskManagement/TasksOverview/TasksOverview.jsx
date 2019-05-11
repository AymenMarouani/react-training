import React, { Component } from 'react';
import moment from 'moment';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import TasksProgressIndicator from '../TasksProgressIndicator';
import TasksTable from '../TasksTable';
import mockTasks from '../../../shared/mocks/tasks';

export default class TasksOverview extends Component {
  constructor(props) {
    super(props);
    this.state = {
      tasks: [],
    };
  }

  componentDidMount() {
    this.setState({
      tasks: mockTasks,
    });
  }

  render() {
    const { tasks } = this.state;
    return (
      <React.Fragment>
        <div style={{ marginTop: '25px', marginBottom: '20px', marginLeft: '15px' }}>
          <Typography variant="h5" color="inherit">
            To Do List for {moment().format("dddd, MMMM Do YYYY")}
          </Typography>
        </div>
        <Divider />
        <TasksProgressIndicator tasks={tasks} />
        <Divider />
        <TasksTable tasks={tasks} />
      </React.Fragment>
    );
  }
}
