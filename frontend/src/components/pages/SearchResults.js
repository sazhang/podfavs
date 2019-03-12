import React, { Component } from "react";
import { Container, MaxWidth } from "../styles/globalstyles";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../elements/PodcastCard";
import InputChips from "../elements/InputChips";

// Render a list of podcast recommendations
class SearchResults extends Component {
  constructor(props) {
    super(props);
    this.state = {
      query: [],
      results: []
    };
    this.fetchData = this.fetchData.bind(this);
  }

  componentDidUpdate(prevProps) {
    if (this.props !== prevProps) {
      this.fetchData(this.props.location.state.userQuery);
    }
  }

  componentDidMount() {
    this.fetchData(this.props.location.state.userQuery);
  }

  fetchData(userQuery) {
    const query = userQuery.split(",").filter(String);
    this.setState({ query: query });

    fetch("")
      .then(response => response.json())
      .then(data => this.setState({ results: data }))
      .catch(err => console.error("Caught error: ", err));
  }

  // populate the table with podcasts
  render() {
    const cards = [];
    this.state.results.map(podcast =>
      cards.push(<PodcastCard key={podcast.id} podcast={podcast} />)
    );

    return (
      <Container>
        <MaxWidth>
          <h2>Podcasts related to:</h2>
          <InputChips query={this.state.query} />
          <CardDeck>{cards}</CardDeck>
        </MaxWidth>
      </Container>
    );
  }
}

export default SearchResults;
