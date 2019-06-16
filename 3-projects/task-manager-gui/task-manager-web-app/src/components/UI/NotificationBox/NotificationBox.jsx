import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import Snackbar from '@material-ui/core/Snackbar';
import SnackbarContent from '@material-ui/core/SnackbarContent';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import CheckCircleIcon from '@material-ui/icons/CheckCircle';
import WarningIcon from '@material-ui/icons/Warning';
import ErrorIcon from '@material-ui/icons/Error';
import InfoIcon from '@material-ui/icons/Info';
import { NOTIFICATION_TYPE, NOTIFICATION_TYPE_VALUES } from '../constants';
import styles from './NotificationBox.module.css';

export default class NotificationBox extends PureComponent {
  handleClose = () => this.props.hideNotificationBox();

  calculateStyle = () => {
    const { type } = this.props;
    switch (type) {
      case NOTIFICATION_TYPE.SUCCESS:
        return { backgroundColor: '#43a047' };
      case NOTIFICATION_TYPE.WARNING:
        return { backgroundColor: '#ffa000' };
      case NOTIFICATION_TYPE.ERROR:
        return { backgroundColor: '#d32f2f' };
      case NOTIFICATION_TYPE.INFO:
        return { backgroundColor: '#1769aa' };
      default:
        return;
    }
  };

  displaySnackBarIcon = () => {
    const { type } = this.props;
    switch (type) {
      case NOTIFICATION_TYPE.SUCCESS:
        return <CheckCircleIcon className={styles.icon} />;
      case NOTIFICATION_TYPE.WARNING:
        return <WarningIcon className={styles.icon} />;
      case NOTIFICATION_TYPE.ERROR:
        return <ErrorIcon className={styles.icon} />;
      case NOTIFICATION_TYPE.INFO:
        return <InfoIcon className={styles.icon} />;
      default:
        return;
    }
  };

  displaySnackBarContent = () => {
    const { message } = this.props;
    return (
      <SnackbarContent
        style={this.calculateStyle()}
        aria-describedby="client-snackbar"
        message={
          <span id="client-snackbar" className={styles.message}>
            {this.displaySnackBarIcon()}
            {message}
          </span>
        }
        action={[
          <IconButton key="close" aria-label="Close" color="inherit" onClick={this.handleClose}>
            <CloseIcon className={styles.closeIcon} />
          </IconButton>,
        ]}
      />
    );
  };

  render() {
    const { isOpen } = this.props;
    return (
      <Snackbar
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'right',
        }}
        open={isOpen}
        autoHideDuration={10000}
        onClose={this.handleClose}
      >
        {this.displaySnackBarContent()}
      </Snackbar>
    );
  }
}

NotificationBox.propTypes = {
  isOpen: PropTypes.bool,
  message: PropTypes.string,
  type: PropTypes.oneOf(NOTIFICATION_TYPE_VALUES),
  hideNotificationBox: PropTypes.func.isRequired,
};

NotificationBox.defaultProps = {
  isOpen: false,
  type: NOTIFICATION_TYPE.INFO,
};
