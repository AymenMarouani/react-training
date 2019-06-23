import { connect } from 'react-redux';
import NotificationBox from './NotificationBox';
import { hideNotificationBox, showNotificationBox } from '../../../redux/actions/uiActions';

const mapStateToProps = state => {
  return {
    isOpen: state.ui.notificationBox.isOpen,
    message: state.ui.notificationBox.message,
    type: state.ui.notificationBox.notificationType,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    showNotificationBox: message => dispatch(showNotificationBox(message)),
    hideNotificationBox: () => dispatch(hideNotificationBox()),
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NotificationBox);
