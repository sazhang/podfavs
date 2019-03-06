import React, { Component } from "react";
import { Container, Wrapper, HalfDiv } from "./styles/globalstyles";
import wrapLayout from "./Layout";
import FeaturedPodcasts from "./FeaturedPodcasts";
import SearchBar from "./SearchBar";
import { ReactComponent as HomeSvg } from "./images/home.svg";

// Landing page with a search bar and default list of podcasts.
class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      podcastList: [],
      userInput: "",
      title: "Featured",
      isLoading: true
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    this.setState({ isLoading: true });
    fetch("api/")
      .then(response => response.json())
      .then(data =>
        this.setState({ podcastList: data.content, isLoading: false })
      );
  }

  handleChange(userInput) {
    this.setState({ userInput: userInput });
  }

  handleSubmit() {
    var url = "api/search/" + encodeURI(this.state.userInput);
    console.log(url);
    fetch(url)
      .then(response => response.json())
      .then(data =>
        this.setState({ podcastList: data, title: "Podcasts you might like" })
      );
  }

  render() {
    if (this.state.isLoading) {
      return <p>Loading...</p>;
    }
    return (
      <>
        <SearchBar
          userInput={this.state.userInput}
          onHandleChange={this.handleChange}
          onHandleSubmit={this.handleSubmit}
        />
        <Container>
          <Wrapper className="items-center">
            <HalfDiv>
              <h1>
                Find your next favorite podcast.
              </h1>
            </HalfDiv>
            <HalfDiv>
              <HomeSvg className="w-full h-full" />
            </HalfDiv>
          </Wrapper>
          <h3>{this.state.title}</h3>
          <FeaturedPodcasts podcastList={this.state.podcastList} />
        </Container>
      </>
    );
  }
}

export default wrapLayout(Home);
