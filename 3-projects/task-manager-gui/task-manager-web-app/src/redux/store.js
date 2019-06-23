import { createStore, combineReducers, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import { tasksReducer, uiReducer } from './reducers';

const reducers = combineReducers({
  tasks: tasksReducer,
  ui: uiReducer,
});

const store =
  process.env.NODE_ENV === 'production'
    ? createStore(reducers, applyMiddleware(thunk))
    : createStore(reducers, composeWithDevTools(applyMiddleware(thunk)));

export default store;
