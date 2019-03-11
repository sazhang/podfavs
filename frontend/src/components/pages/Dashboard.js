import React, { Component } from "react";
import { Container, Wrapper } from "../styles/globalstyles";
import { withAuth } from "@okta/okta-react";
import FeaturedPodcasts from "../layout/FeaturedPodcasts";

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
      
      return (
        <>
          <Container>
            <Wrapper>
              <div className="w-3/4">
                <h3>{this.state.user.name}</h3>
              </div>
              {/* <div className="w-1/4">
              <FeaturedPodcasts />
            </div> */}
            </Wrapper>
          </Container>
        </>
      );
    }
  }
);
