import React, { Component } from 'react';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import TasksTable from '../TasksTable';
import { getScheduledTasks, getFinishedTasks, getCancelledTasks } from '../../../common/utils/tasksUtils';

export default class TasksTabbedContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedTabIndex: 0,
    };
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event, value) {
    this.setState({ selectedTabIndex: value });
  }

  render() {
    const { tasks } = this.props;
    const { selectedTabIndex } = this.state;
    const totalTasksCount = tasks.length;
    const scheduledTasks = getScheduledTasks(tasks);
    const scheduledTasksCount = scheduledTasks.length;
    const finishedTasks = getFinishedTasks(tasks);
    const finishedTasksCount = finishedTasks.length;
    const cancelledTasks = getCancelledTasks(tasks);
    const cancelledTasksCount = cancelledTasks.length;
    return (
      <React.Fragment>
        <Tabs value={selectedTabIndex} onChange={this.handleChange}>
          <Tab label={`${totalTasksCount} tasks`} />
          <Tab label={`${scheduledTasksCount} scheduled tasks`} />
          <Tab label={`${finishedTasksCount} finished tasks`} />
          <Tab label={`${cancelledTasksCount} cancelled tasks`} />
        </Tabs>
        {selectedTabIndex === 0 && <TasksTable tasks={tasks} />}
        {selectedTabIndex === 1 && <TasksTable tasks={scheduledTasks} />}
        {selectedTabIndex === 2 && <TasksTable tasks={finishedTasks} />}
        {selectedTabIndex === 3 && <TasksTable tasks={cancelledTasks} />}
      </React.Fragment>
    );
  }
}