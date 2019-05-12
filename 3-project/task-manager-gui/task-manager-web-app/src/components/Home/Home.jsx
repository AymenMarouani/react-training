import React, { Component } from 'react';
import Header from '../UI/Header';
import TasksOverview from '../TaskManagement';

export default class Home extends Component {
  render() {
    return (
      <React.Fragment>
        <Header />
        <div style={{ height: '100%', width: '100%' }}>
          <TasksOverview />
        </div>
      </React.Fragment>
    );
  }
}
