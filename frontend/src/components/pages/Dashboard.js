import React, { Component } from "react";
import { Container, MaxWidth } from "../styles/globalstyles";
import { withAuth } from "@okta/okta-react";
import MyPodcasts from "../layout/MyPodcasts";

// Dashboard that lets users see
export default withAuth(
  class Dashboard extends Component {
    constructor(props) {
      super(props);
      this.state = { user: null };
      this.getCurrentUser = this.getCurrentUser.bind(this);
    }

    async getCurrentUser() {
      this.props.auth.getUser().then(user => this.setState({ user }));
    }

    componentDidMount() {
      this.getCurrentUser();
    }

    render() {
      if (!this.state.user) return null;
      console.log(this.state.user);

      return (
        <Container>
          <MaxWidth>
            <h2>My saved podcasts</h2>
            <MyPodcasts auth={this.props.auth}/>
          </MaxWidth>
        </Container>
      );
    }
  }
);
