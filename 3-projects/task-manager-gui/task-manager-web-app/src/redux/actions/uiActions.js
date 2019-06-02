import { SHOW_NOTIFICATION_BOX, HIDE_NOTIFICATION_BOX } from '../actionTypes';

export const showNotificationBox = message => ({ type: SHOW_NOTIFICATION_BOX, message });

export const hideNotificationBox = () => ({ type: HIDE_NOTIFICATION_BOX });
