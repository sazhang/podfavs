import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Home from './components/Home';
import LoginPage from "./components/auth/LoginPage"

// Route renders UI when app location matches the path
class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/login' exact={true} component={LoginPage}/>
        </Switch>
      </Router>
    )
  }
}

export default App;