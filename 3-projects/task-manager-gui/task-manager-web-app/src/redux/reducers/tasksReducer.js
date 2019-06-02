import {
  FETCH_TASKS,
  FETCH_TASKS_SUCCESS,
  FETCH_TASKS_FAILURE,
  ADD_TASK,
  ADD_TASK_SUCCESS,
  ADD_TASK_FAILURE,
} from '../actionTypes';

const initialState = {
  content: [],
  error: null,
  loading: false,
};

export default function tasksReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_TASKS:
    case ADD_TASK:
      return {
        ...state,
        ...state.tasks,
        loading: true,
      };
    case FETCH_TASKS_SUCCESS:
      return {
        ...state,
        content: action.tasks,
        error: null,
        loading: false,
      };
    case ADD_TASK_SUCCESS:
      return {
        ...state,
        content: [...state.tasks.content, action.tasks],
        error: null,
        loading: false,
      };
    case FETCH_TASKS_FAILURE:
    case ADD_TASK_FAILURE:
      return {
        ...state,
        ...state.tasks,
        error: action.error,
        loading: false,
      };
    default:
      return state;
  }
}
