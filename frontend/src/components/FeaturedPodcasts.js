import React, { Component } from "react";
import PropTypes from "prop-types";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import PodcastItem from "./PodcastItem";

//Render a list of podcasts.
const CardDeck = styled.div`
  ${tw`flex flex-wrap mb-8 -mx-2`};
`;

class FeaturedPodcasts extends Component {
  render() {
    const cards = [];
    this.props.podcastList.map(podcast =>
      cards.push(<PodcastItem key={podcast.id} podcast={podcast} />)
    );

    // populate the table with podcast components
    return <CardDeck>{cards}</CardDeck>;
  }
}

FeaturedPodcasts.propTypes = {
  podcastList: PropTypes.array.isRequired
};

export default FeaturedPodcasts;
