import React, { Component } from 'react';
import PropTypes from 'prop-types';
import moment from 'moment';
import { taskPropType as task } from '../../../prop-types';
import Typography from '@material-ui/core/Typography';
import TasksProgressBar from '../TasksProgressBar';
import TasksTabbedContainer from '../TasksTabbedContainer';
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
    const { tasks } = this.props;
    return (
      <React.Fragment>
        <div className={styles.topContainer}>
          <Typography variant="h4" color="inherit">
            To Do List for {moment().format('dddd, MMMM Do YYYY')}
          </Typography>
        </div>
        <TasksProgressBar tasks={tasks} />
        <TasksTabbedContainer tasks={tasks} />
      </React.Fragment>
    );
  }
}

TasksOverview.propTypes = {
  fetchTasks: PropTypes.func,
  showErrorNotification: PropTypes.func,
  showSuccessNotification: PropTypes.func,
  tasks: PropTypes.arrayOf(task),
};
