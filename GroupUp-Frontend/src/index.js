import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Login from './Components/Login/Login'
import Navbar from './Components/Navbar/Navbar'
import reportWebVitals from './reportWebVitals';
import { createStore } from 'redux';
import { Provider } from 'react-redux'
import allReducers from './Reducers/index'

const store = createStore(allReducers)

ReactDOM.render(
  <React.StrictMode>
    <Provider store={ store }>
      {/* <App /> */}
      <Navbar />
      <Login />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
