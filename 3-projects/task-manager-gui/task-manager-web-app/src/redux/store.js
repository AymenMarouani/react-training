import { createStore, combineReducers, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import tasksReducer from './reducers/tasksReducer';
import uiReducer from './reducers/uiReducer';

export const store = createStore(
  combineReducers({
    tasks: tasksReducer,
    ui: uiReducer,
  }),
  composeWithDevTools(applyMiddleware(thunk))
);
