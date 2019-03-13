import React, { Component } from "react";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../elements/PodcastCard";
import { withAuth } from "@okta/okta-react";

// Render a list of randomly selected podcasts to be featured
export default withAuth(
  class MyPodcasts extends Component {
    constructor(props) {
      super(props);
      this.state = { podcasts: [], loaded: false };
    }

    componentDidMount() {
      const token = JSON.parse(localStorage.getItem("okta-token-storage"));
      console.log(token);
      //const email = idToken.idToken.claims.email;
      //const userId = idToken.idToken.claims.sub;

      fetch("/api/mypodcasts", {
        credentials: "include",
        headers: {
          Authorization: "Bearer " + token.accessToken//this.props.auth.getAccessToken()
        }
      })
        .then(response => response.json())
        .then(data => this.setState({ podcasts: data, loaded: true }))
        .catch(err => console.error("Caught error: ", err));
    }

    render() {
      const cards = [];
      const { podcasts, loaded } = this.state;
      if (loaded) {
        podcasts.map(podcast =>
          cards.push(<PodcastCard key={podcast.id} podcast={podcast} />)
        );
      }
      return <CardDeck>{cards}</CardDeck>;
    }
  }
);

//export default MyPodcasts;
