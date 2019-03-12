import React, { Component } from "react";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../elements/PodcastCard";

// Render a list of randomly selected podcasts to be featured
class MyPodcasts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      featured: []
    };
  }

  async componentDidMount() {
    try {
      const response = await fetch("api/mypodcasts", {
        headers: {
          Authorization: "Bearer " + (await this.props.auth.getAccessToken())
        }
      });
      console.log("Auth token: " + this.props.auth.getAccessToken());
      const data = await response.json();
      console.log("Response: " + data);
      this.setState({ featured: data });
    } catch (err) {
      console.error("Caught error: ", err);
    }
    /* fetch("api/mypodcasts")
      .then(response => response.json())
      .then(data => this.setState({ featured: data }))
      .catch(err => console.error("Caught error: ", err)); */
  }

  render() {
    const cards = [];
    this.state.featured.map(podcast =>
      cards.push(<PodcastCard key={podcast.id} podcast={podcast} />)
    );

    // populate the table with podcasts
    return <CardDeck>{cards}</CardDeck>;
  }
}

export default MyPodcasts;
