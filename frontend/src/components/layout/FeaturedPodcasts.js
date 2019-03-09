import React, { Component } from "react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import PodcastCard from "../layout/PodcastCard";

//Render a list of podcasts
const CardDeck = styled.div`
  ${tw`flex flex-wrap -m-3 h-full justify-center`};
`;

class FeaturedPodcasts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      featured: [],
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
