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
      this.state = { user: null }; //authenticated: null
      this.checkAuthentication = this.checkAuthentication.bind(this);
      this.checkAuthentication();
      this.getCurrentUser = this.getCurrentUser.bind(this);
    }

    async getCurrentUser() {
      this.props.auth.getUser().then(user => this.setState({ user }));
    }

    componentDidMount() {
      this.getCurrentUser();
    }

    async checkAuthentication() {
      const authenticated = await this.props.auth.isAuthenticated();
      if (authenticated !== this.state.authenticated) {
        this.setState({ authenticated: authenticated });
      }
    }

    componentDidUpdate() {
      this.checkAuthentication();
    }

    render() {
      if (this.state.authenticated === null) return null;
      if (!this.state.user) return null;

      console.log(this.state)

      return this.state.authenticated ? (
        <Container>
          <MaxWidth>
            <h2>My saved podcasts</h2>
            <MyPodcasts />
          </MaxWidth>
        </Container>
      ) : (
        <Redirect to={{ pathname: "/" }} />
      );
    }
  }
);
