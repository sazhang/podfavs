import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Security, SecureRoute, ImplicitCallback } from "@okta/okta-react";
import config from "./app.config";
import { Global, css } from "@emotion/core";
import tw from "tailwind.macro";
import styled from "@emotion/styled";

import Home from "./components/pages/Home";
import LoginPage from "./components/auth/LoginPage";
import RegisterPage from "./components/auth/RegisterPage";
import Dashboard from "./components/pages/Dashboard";
import SearchBar from "./components/layout/SearchBar";
import Footer from "./components/layout/Footer";
import SearchResults from "./components/pages/SearchResults";

// Route renders UI when app location matches the path
const Main = styled.main`
  ${tw`flex flex-wrap min-h-screen w-full justify-center`};
`;

function onAuthRequired({ history }) {
  history.push("/login");
}

class App extends Component {
  render() {
    return (
      <Router>
        <Security
          issuer={config.issuer}
          client_id={config.client_id}
          redirect_uri={config.redirect_uri}
          onAuthRequired={onAuthRequired}
        >
          <Global
            styles={css`
              *,
              *:before,
              *:after {
                box-sizing: inherit;
              }
              html {
                text-rendering: optimizeLegibility;
                box-sizing: border-box;
                -ms-overflow-style: scrollbar;
                -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
                -webkit-font-smoothing: antialiased;
                -moz-osx-font-smoothing: grayscale;
              }
              html,
              body {
                ${tw`p-0 m-0 font-sans bg-indigo-darkest`};
              }
              h1,
              h2,
              h3,
              h4,
              h5 {
                ${tw`font-sans`};
              }
              h1 {
                ${tw`text-4xl sm:text-5xl lg:text-xxl text-indigo-lightest my-4`};
              }
              h2 {
                ${tw`text-3xl sm:text-4xl text-indigo-lightest my-4`};
              }
              h3 {
                ${tw`text-2xl sm:text-3xl text-indigo-lighter my-4`};
              }
              h4 {
                ${tw`text-base sm:text-lg text-indigo-darker mb-2`};
              }
              h5 {
                ${tw`text-indigo font-normal`};
              }
            `}
          />
          <Main>
            <SearchBar />
            <Switch>
              <Route exact path="/" component={Home} />
              {/* <Route path="/login" component={LoginPage} /> */}
              <Route
                path="/login"
                render={() => (
                  <LoginPage
                    baseUrl="https://dev-992080.okta.com"
                    clientId={config.client_id}
                    redirectUri={config.redirect_uri}
                  />
                )}
              />
              <Route path="/implicit/callback" component={ImplicitCallback} />
              <Route path="/register" component={RegisterPage} />
              <Route path="/api/search/:userQuery" component={SearchResults} />
              <SecureRoute path="/dashboard" component={Dashboard} />
            </Switch>
          </Main>
          <Footer />
        </Security>
      </Router>
    );
  }
}

export default App;
