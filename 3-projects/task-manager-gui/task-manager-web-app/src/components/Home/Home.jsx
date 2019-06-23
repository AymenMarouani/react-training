import React, { Component } from 'react';
import TasksOverview from '../TaskManagement';
import { Header, NotificationBox } from '../UI';

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
