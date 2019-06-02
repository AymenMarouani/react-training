import React, { Component } from 'react';
import Header from '../UI/Header';
import TasksOverview from '../TaskManagement';
import NotificationBox from '../UI/NotificationBox/NotificationBox';

export default class Home extends Component {
  render() {
    return (
      <React.Fragment>
        <Header />
        <TasksOverview />
        <NotificationBox />
      </React.Fragment>
    );
  }
}
