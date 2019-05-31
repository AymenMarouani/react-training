import { getAllTasks } from '../../common/api/tasksApi';
import { FETCH_TASKS, FETCH_TASKS_SUCCESS, FETCH_TASKS_FAILURE } from '../actionTypes';

export const fetchAllTasksSuccess = tasks => ({ type: FETCH_TASKS_SUCCESS, tasks });

export const fetchAllTasksFailure = error => ({ type: FETCH_TASKS_FAILURE, error });

export const fetchAllTasks = () => dispatch => {
  dispatch({ type: FETCH_TASKS });
  return getAllTasks()
    .then(response => dispatch(fetchAllTasksSuccess(response)))
    .catch(error => dispatch(fetchAllTasksFailure(error)));
};
