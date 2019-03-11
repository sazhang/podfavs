import React, { Component } from "react";
import { Container, Wrapper } from "../styles/globalstyles";
import { CardDeck } from "../styles/globalstyles";
import PodcastCard from "../layout/PodcastCard";
//import InputChips from "../layout/InputChips";

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
    const query = userQuery.split(",");
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
        <Wrapper>
          <div className="w-full">
            <h3>Podcasts for you</h3>
            {/* <InputChips query={this.state.query} /> */}
          </div>
          <CardDeck>{cards}</CardDeck>
        </Wrapper>
      </Container>
    );
  }
}

export default SearchResults;
