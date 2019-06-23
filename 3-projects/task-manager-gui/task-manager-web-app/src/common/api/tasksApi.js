import axios from 'axios';
import TASKS_API_URL from './config';

export const getAllTasks = () => {
  return axios
    .get(`${TASKS_API_URL}`) //-
    .then(response => {
      return response.data.tasks;
    });
};
