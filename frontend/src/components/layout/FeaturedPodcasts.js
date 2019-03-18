import React, { Component } from "react";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../elements/PodcastCard";

// Render a list of randomly selected podcasts to be featured
class FeaturedPodcasts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      featured: [],
      errThrown: false
    };
  }

  componentDidMount() {
    fetch("/featured")
      .then(response => response.json())
      .then(data => this.setState({ featured: data }))
      .catch(err => this.setState({ errThrown: true }));
  }

  render() {
    const cards = [];
    if (this.state.errThrown) return <div><h2>woops</h2></div>
    if (!this.state.featured) return <div><h2>loading...</h2></div>
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
