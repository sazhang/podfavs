import React, { Component } from "react";
import { Container, CardDeck, Row, Col } from "reactstrap";
import { Link } from "react-router-dom";
import PodcastItem from "./PodcastItem";
import PropTypes from "prop-types";

//Render a list of podcasts.
class Podcasts extends Component {
  render() {
    // populate the table with podcast components
    return this.props.podcasts.map(podcast => (
      <CardDeck style={cardDeckStyle}>
        <PodcastItem key={podcast.id} podcast={podcast} />
      </CardDeck>
    ));
  }
}

Podcasts.propTypes = {
  podcasts: PropTypes.array.isRequired
};

const cardDeckStyle = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  flex: "auto",
  flexFlow: "row wrap"
};

export default Podcasts;
