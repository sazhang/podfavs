import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import { withAuth } from "@okta/okta-react";
import { Container, Wrapper } from "../styles/globalstyles";
import { ReactComponent as LoginSvg } from "../images/login.svg";
import LoginWidget from "./LoginWidget";
// import LoginForm from "./LoginForm";

// If user has logged in, redirect to user dashboard, else display login form
export default withAuth(
  class Login extends Component {
    constructor(props) {
      super(props);
      this.state = { authenticated: null };
      this.checkAuthentication = this.checkAuthentication.bind(this);
      this.checkAuthentication();
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

    onSuccess = res => {
      return this.props.auth.redirect({
        sessionToken: res.session.token
      });
    };

    onError = err => {
      console.log("error logging in", err);
    };

    render() {
      if (this.state.authenticated === null) return null;
      return this.state.authenticated ? (
        <Redirect to={{ pathname: "/dashboard" }} />
      ) : (
        <Container>
          <Wrapper>
            <div className="w-full md:flex-1">
              <h1>Save podcasts to binge later</h1>
              <LoginSvg className="w-full h-full" />
            </div>
            <div className="md:flex-1">
              <LoginWidget
                baseUrl={this.props.baseUrl}
                clientId={this.props.clientId}
                redirectUri={this.props.redirectUri}
                onSuccess={this.onSuccess}
                onError={this.onError}
              />
            </div>
          </Wrapper>
        </Container>
      );
    }
  }
);
/* <LoginForm baseUrl={this.props.baseUrl} /> */
