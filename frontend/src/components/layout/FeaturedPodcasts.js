import React, { Component } from "react";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../elements/PodcastCard";

// Render a list of randomly selected podcasts to be featured
class FeaturedPodcasts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      featured: []
    };
  }

  componentDidMount() {
    fetch("api/featured")
      .then(response => response.json())
      .then(data => this.setState({ featured: data }))
      .catch(err => console.error("Caught error: ", err));
  }

  render() {
    const cards = [];
    this.state.featured.map(podcast =>
      cards.push(<PodcastCard key={podcast.id} podcast={podcast} />)
    );

    // populate the table with podcasts
    return (
      <>
        <h3>Featured</h3>
        <CardDeck>{cards}</CardDeck>
      </>
    );
  }
}

export default FeaturedPodcasts;
