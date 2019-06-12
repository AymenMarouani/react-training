import axios from 'axios';

export const getAllTasks = () => {
  return axios
    .get('http://localhost:9090/task-manager/api/v1/tasks') //-
    .then(response => {
      return response.data.tasks;
    });
};
