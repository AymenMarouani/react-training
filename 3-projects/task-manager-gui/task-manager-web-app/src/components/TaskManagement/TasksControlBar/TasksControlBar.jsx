import React, { Component } from 'react';
import moment from 'moment';
import Typography from '@material-ui/core/Typography';
import styles from './TasksControlBar.module.css';

export default class TasksControlBar extends Component {
  render() {
    return (
      <div className={styles.topContainer}>
        <Typography variant="h4" color="inherit">
          To Do List for {moment().format('dddd, MMMM Do YYYY')}
        </Typography>
      </div>
    );
  }
}
