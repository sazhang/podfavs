import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import { withAuth } from "@okta/okta-react";
import { Container, MaxWidth } from "../styles/globalstyles"
import LoginWidget from "./LoginWidget";

// If user has logged in, redirect to user dashboard, else display login form
export default withAuth(
  class LoginPage extends Component {
    constructor(props) {
      super(props);
      this.state = {
        authenticated: null
      };
      this.checkAuthentication();
      this.onSuccess = this.onSuccess.bind(this);
      this.onError = this.onError.bind(this);
    }

    async checkAuthentication() {
      const authenticated = await this.props.auth.isAuthenticated();
      if (authenticated !== this.state.authenticated) {
        this.setState({ authenticated });
      }
    }

    componentDidUpdate() {
      this.checkAuthentication();
    }

    onSuccess(res) {
      if (res.status === "SUCCESS") {
        console.log("SUCCESS");
        console.log(res);
        return this.props.auth.redirect({
          sessionToken: res.session.token
        });
      } else {
        // The user can be in another authentication state that requires further action.
        // For more information about these states, see:
        //   https://github.com/okta/okta-signin-widget#rendereloptions-success-error
      }
    }

    onError(err) {
      console.log("error logging in", err);
    }

    render() {
      if (this.state.authenticated === null) return null;
      return this.state.authenticated ? (
        <Redirect to={{ pathname: "/" }} />
      ) : (
        <Container>
          <MaxWidth>
            <LoginWidget
              baseUrl="https://dev-992080.okta.com"
              client_id={this.props.client_id}
              redirect_uri={this.props.redirect_uri}
              onSuccess={this.onSuccess}
              onError={this.onError}
            />
          </MaxWidth>
        </Container>
      );
    }
  }
);
