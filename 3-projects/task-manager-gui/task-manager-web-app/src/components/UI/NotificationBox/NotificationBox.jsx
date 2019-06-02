import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import Snackbar from '@material-ui/core/Snackbar';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';

export default class NotificationBox extends PureComponent {
  handleClose = () => this.props.hideNotificationBox();

  render() {
    const { isOpen, message } = this.props;
    return (
      <Snackbar
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'center',
        }}
        open={isOpen}
        autoHideDuration={10000}
        onClose={this.handleClose}
        SnackbarContentProps={{ 'aria-describedby': 'message-id' }}
        message={<span id="message-id">{message}</span>}
        action={[
          <IconButton key="close" area-label="Close" color="inherit" onClick={this.handleClose}>
            <CloseIcon />
          </IconButton>,
        ]}
      />
    );
  }
}

NotificationBox.propTypes = {
  isOpen: PropTypes.bool,
  message: PropTypes.string,
  hideNotificationBox: PropTypes.func,
};
