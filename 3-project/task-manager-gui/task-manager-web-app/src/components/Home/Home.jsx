import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import logo from '../../assets/images/react-logo.svg';

export default class Home extends Component {
  render() {
    return (
      <React.Fragment>
        <AppBar position="static" color="default">
          <Toolbar>
            <IconButton color="inherit" aria-label="Menu">
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" color="inherit">
              Task Manager
            </Typography>
          </Toolbar>
        </AppBar>
        <div style={{ width: '50%', height: '50%', margin: 'auto' }}>
          <img src={logo} alt="React Logo" />
        </div>
      </React.Fragment>
    );
  }
}
