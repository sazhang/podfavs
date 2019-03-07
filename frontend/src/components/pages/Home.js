import React, { Component } from "react";
import { Container, Wrapper, HalfDiv } from "../styles/globalstyles";
import wrapLayout from "../layout/Layout";
import FeaturedPodcasts from "../layout/FeaturedPodcasts";
import SearchBar from "../layout/SearchBar";
import { ReactComponent as WalkSvg } from "../images/walk.svg";

// Landing page with a search bar and default list of podcasts.
class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      podcastList: [],
      userInput: "",
      title: "Featured",
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
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
    return (
      <>
        <SearchBar
          userInput={this.state.userInput}
          onHandleChange={this.handleChange}
          onHandleSubmit={this.handleSubmit}
        />
        <Container>
          <Wrapper>
            <HalfDiv>
              <h1>
                Find your next favorite podcasts
              </h1>
            </HalfDiv>
            <HalfDiv>
              <WalkSvg className="w-full h-full" />
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
