import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { CookiesProvider } from "react-cookie";
import Home from "./components/pages/Home";
import LoginPage from "./components/auth/LoginPage";
import RegisterPage from "./components/auth/RegisterPage";

// Route renders UI when app location matches the path
class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Router>
          <Switch>
            <Route path="/" exact={true} component={Home} />
            <Route path="/login" exact={true} component={LoginPage} />
            <Route path="/register" exact={true} component={RegisterPage} />
          </Switch>
        </Router>
      </CookiesProvider>
    );
  }
}

export default App;
