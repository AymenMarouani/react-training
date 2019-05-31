import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import tasksReducer from './reducers/tasksReducer';

export const store = createStore(tasksReducer, applyMiddleware(thunk));
