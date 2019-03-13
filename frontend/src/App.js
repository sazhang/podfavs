import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Security, SecureRoute, ImplicitCallback } from "@okta/okta-react";
import config from "./app.config";
import { Global, css } from "@emotion/core";
import tw from "tailwind.macro";
import styled from "@emotion/styled";

import Home from "./components/pages/Home";
import LoginPage from "./components/auth/LoginPage";
import Dashboard from "./components/pages/Dashboard";
import SearchBar from "./components/layout/SearchBar";
import Footer from "./components/layout/Footer";
import SearchResults from "./components/pages/SearchResults";

// Route renders UI when app location matches the path
const Content = styled.div`
  ${tw`w-full px-5 pb-5 sm:px-8 sm:pb-8`};
  flex: 1 0 auto;
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
              * {
                box-sizing: border-box;
              }
              html,
              body {
                ${tw`w-full h-full p-0 m-0 font-sans bg-indigo-darkest`};
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
          <SearchBar />
          <main className="flex flex-col flex-no-shrink min-h-screen justify-center">
            <Content>
              <Route exact path="/" component={Home} />
              <Route
                path="/login"
                render={() => (
                  <LoginPage baseUrl="https://dev-992080.okta.com" />
                )}
              />
              <Route path="/implicit/callback" component={ImplicitCallback} />
              <Route path="/search/:userQuery" component={SearchResults} />
              <SecureRoute path="/dashboard" component={Dashboard} />
            </Content>
            <Footer />
          </main>
        </Security>
      </Router>
    );
  }
}

export default App;
