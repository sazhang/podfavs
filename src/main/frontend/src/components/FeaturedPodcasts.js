import React, { Component } from "react";
import { CardDeck } from "reactstrap";
import PodcastItem from "./PodcastItem";
import PropTypes from "prop-types";

//Render a list of podcasts.
class FeaturedPodcasts extends Component {
  
  render() {
    // populate the table with podcast components
    return this.props.podcastList.map(podcast => (
      <CardDeck style={cardDeckStyle}>
        <PodcastItem key={podcast.id} podcast={podcast} />
      </CardDeck>
    ));
  }
}

FeaturedPodcasts.propTypes = {
  podcastList: PropTypes.array.isRequired
};

const cardDeckStyle = {
  //backgroundColor: "#FED766",
  display: "flex",
  alignItems: "center",
  justifyContent: "space-around",
  flex: "auto",
  flexFlow: "row wrap",
  margin: "0 auto"
};

export default FeaturedPodcasts;
