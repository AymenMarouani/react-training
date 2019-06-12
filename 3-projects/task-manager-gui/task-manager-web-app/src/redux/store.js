import { createStore, combineReducers, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import { tasksReducer, uiReducer } from './reducers';

export const store = createStore(
  combineReducers({
    tasks: tasksReducer,
    ui: uiReducer,
  }),
  composeWithDevTools(applyMiddleware(thunk))
);
