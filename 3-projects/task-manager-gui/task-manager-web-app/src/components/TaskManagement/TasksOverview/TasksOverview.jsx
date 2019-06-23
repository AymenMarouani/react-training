import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { taskPropType as task } from '../../../prop-types';
import { Spinner } from '../../UI';
import TasksProgressBar from '../TasksProgressBar';
import TasksTabbedContainer from '../TasksTabbedContainer';
import TasksControlBar from '../TasksControlBar';
import styles from './TasksOverview.module.css';

export default class TasksOverview extends Component {
  componentDidMount() {
    const { fetchTasks, showErrorNotification, showSuccessNotification } = this.props;
    fetchTasks()
      .then(response => {
        showSuccessNotification(`${response.length} tasks found`);
      })
      .catch(error => {
        showErrorNotification(error.message);
      });
  }

  render() {
    const { loading, tasks } = this.props;
    return (
      <React.Fragment>
        <TasksControlBar />
        <TasksProgressBar tasks={tasks} />
        <TasksTabbedContainer tasks={tasks} />
        {loading && (
          <React.Fragment>
            <div className={styles.contentLoading} />
            <Spinner />
          </React.Fragment>
        )}
      </React.Fragment>
    );
  }
}

TasksOverview.propTypes = {
  fetchTasks: PropTypes.func,
  showErrorNotification: PropTypes.func,
  showSuccessNotification: PropTypes.func,
  tasks: PropTypes.arrayOf(task),
  loading: PropTypes.bool,
};
