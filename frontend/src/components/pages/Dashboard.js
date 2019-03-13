import React, { Component } from "react";
import { Container, MaxWidth } from "../styles/globalstyles";
import MyPodcasts from "../layout/MyPodcasts";
import { withAuth } from "@okta/okta-react";
import { Redirect } from "react-router-dom";

// Dashboard that lets users see
export default withAuth(
  class Dashboard extends Component {
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

    componentDidMount() {
      this.checkAuthentication();
    }

    render() {
      if (this.state.authenticated === null) return null;
      const { auth } = this.props.auth;
      console.log(auth);

      return this.state.authenticated ? (
        <Container>
          <MaxWidth>
            <h2>My saved podcasts</h2>
            <MyPodcasts auth={auth} />
          </MaxWidth>
        </Container>
      ) : (
        <Redirect to={{ pathname: "/" }} />
      );
    }
  }
);
