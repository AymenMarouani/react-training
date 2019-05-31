import React, { Component } from 'react';
import { Provider } from 'react-redux';
import store from './redux';
import Home from './components/Home';

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Home />
      </Provider>
    );
  }
}

export default App;
