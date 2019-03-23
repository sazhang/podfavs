import React, { Component } from "react";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../elements/PodcastCard";
import { withAuth } from "@okta/okta-react";

// Render a list of randomly selected podcasts to be featured
export default withAuth(
  class MyPodcasts extends Component {
    constructor(props) {
      super(props);
      this.state = { podcasts: [] };
    }

    async componentDidMount() {
      const token = JSON.parse(localStorage.getItem("okta-token-storage"));
      console.log(token);
      //const email = idToken.idToken.claims.email;
      //const userId = idToken.idToken.claims.sub;
      try {
        const response = await fetch("/api/mypodcasts", {
          headers: {
            Authorization: "Bearer " + await this.props.auth.getAccessToken()
          }
        });
        const data = await response.json();
        this.setState({ podcasts: data });
      } catch (err) {
        // handle error as needed
      }
    }

    render() {
      const cards = [];
      const { podcasts } = this.state;
      if (!podcasts) return <div><h2>loading...</h2></div>
      podcasts.map(podcast =>
        cards.push(<PodcastCard key={podcast.id} podcast={podcast} email={this.props.email}/>)
      );
      return <CardDeck>{cards}</CardDeck>;
    }
  }
);
