import React, { Component } from "react";
import { CardDeck } from "reactstrap";
import PodcastItem from "./PodcastItem";
import PropTypes from "prop-types";

//Render a list of podcasts.
class FeaturedPodcasts extends Component {
  
  render() {
    const someList = this.props.podcasts;
    // populate the table with podcast components
    return someList.map(podcast => (
      <CardDeck style={cardDeckStyle}>
        <PodcastItem key={podcast.id} podcast={podcast} />
      </CardDeck>
    ));
  }
}

FeaturedPodcasts.propTypes = {
  podcasts: PropTypes.array.isRequired
};

const cardDeckStyle = {
  display: "flex",
  alignItems: "center",
  justifyContent: "space-around",
  flex: "auto",
  flexFlow: "row wrap",
  margin: "0 auto",
  backgroundColor: "#FED766"
};

export default FeaturedPodcasts;
