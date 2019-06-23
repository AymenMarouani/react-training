import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { taskPropType as task } from '../../../prop-types';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import TasksTable from '../TasksTable';
import { getScheduledTasks, getFinishedTasks, getCancelledTasks } from '../../../common/utils/taskUtils';
import styles from './TasksTabbedContainer.styles';

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
    const totalTasksCount = tasks.length;
    const scheduledTasks = getScheduledTasks(tasks);
    const scheduledTasksCount = scheduledTasks.length;
    const finishedTasks = getFinishedTasks(tasks);
    const finishedTasksCount = finishedTasks.length;
    const cancelledTasks = getCancelledTasks(tasks);
    const cancelledTasksCount = cancelledTasks.length;
    const { selectedTabIndex } = this.state;
    return (
      <React.Fragment>
        <Tabs value={selectedTabIndex} onChange={this.handleChange}>
          <Tab style={styles.tabContent} label={`${totalTasksCount} tasks`} />
          <Tab style={styles.tabContent} label={`${scheduledTasksCount} scheduled tasks`} />
          <Tab style={styles.tabContent} label={`${finishedTasksCount} finished tasks`} />
          <Tab style={styles.tabContent} label={`${cancelledTasksCount} cancelled tasks`} />
        </Tabs>
        {selectedTabIndex === 0 && <TasksTable tasks={tasks} />}
        {selectedTabIndex === 1 && <TasksTable tasks={scheduledTasks} />}
        {selectedTabIndex === 2 && <TasksTable tasks={finishedTasks} />}
        {selectedTabIndex === 3 && <TasksTable tasks={cancelledTasks} />}
      </React.Fragment>
    );
  }
}

TasksTabbedContainer.propTypes = {
  tasks: PropTypes.arrayOf(task),
};
