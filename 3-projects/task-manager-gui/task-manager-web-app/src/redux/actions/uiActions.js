import { SHOW_NOTIFICATION_BOX, HIDE_NOTIFICATION_BOX } from '../actionTypes';
import { NOTIFICATION_TYPE } from '../../components/UI';

export const showSuccessNotification = message => showNotificationBox(message, NOTIFICATION_TYPE.SUCCESS);

export const showErrorNotification = message => showNotificationBox(message, NOTIFICATION_TYPE.ERROR);

export const showNotificationBox = (message, notificationType) => ({
  type: SHOW_NOTIFICATION_BOX,
  message,
  notificationType,
});

export const hideNotificationBox = () => ({ type: HIDE_NOTIFICATION_BOX });
