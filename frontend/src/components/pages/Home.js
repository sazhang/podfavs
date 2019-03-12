import React, { Component } from "react";
import { Container, Wrapper, MaxWidth } from "../styles/globalstyles";
import FeaturedPodcasts from "../layout/FeaturedPodcasts";
import { ReactComponent as WalkSvg } from "../images/walk.svg";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

// Landing page with a search bar and default list of podcasts
const HalfDiv = styled.div`
  ${tw`w-full h-full sm:w-1/2`};
`;

class Home extends Component {
  render() {
    return (
      <Container>
        <MaxWidth>
          <Wrapper>
            <HalfDiv>
              <h1>Find your next favorite podcasts</h1>
            </HalfDiv>
            <HalfDiv>
              <WalkSvg className="w-full h-full" />
            </HalfDiv>
          </Wrapper>
          <FeaturedPodcasts />
        </MaxWidth>
      </Container>
    );
  }
}

export default Home;
