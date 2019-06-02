import { SHOW_NOTIFICATION_BOX, HIDE_NOTIFICATION_BOX } from '../actionTypes';

const initialState = {
  notificationBox: {
    isOpen: false,
    message: null,
  },
};

export default function uiReducer(state = initialState, action) {
  switch (action.type) {
    case SHOW_NOTIFICATION_BOX:
      return {
        ...state,
        notificationBox: { isOpen: true, message: action.message },
      };
    case HIDE_NOTIFICATION_BOX:
      return {
        ...state,
        notificationBox: { isOpen: false, message: null },
      };
    default:
      return state;
  }
}
