import React, { Component } from 'react';
import Header from '../UI/Header';
import TasksTable from '../TaskManagement';
import mockTasks from '../../shared/mocks/tasks';

export default class Home extends Component {
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
        <Header />
        <div style={{ height: '100%', width: '100%' }}>
          <TasksTable tasks={tasks} />
        </div>
      </React.Fragment>
    );
  }
}
