import React, { Component } from "react";
import { Container, Wrapper, HalfDiv } from "../styles/globalstyles";
import wrapLayout from "../layout/Layout";
import FeaturedPodcasts from "../layout/FeaturedPodcasts";
import { ReactComponent as WalkSvg } from "../images/walk.svg";

// Landing page with a search bar and default list of podcasts
class Home extends Component {
  
  /*  */

  render() {
    return (
      <>
        <Container>
          <Wrapper>
            <HalfDiv>
              <h1>
                Find your next favorite podcasts
              </h1>
            </HalfDiv>
            <HalfDiv>
              <WalkSvg className="w-full h-full" />
            </HalfDiv>
          </Wrapper>
          <FeaturedPodcasts />
        </Container>
      </>
    );
  }
}

export default wrapLayout(Home);
