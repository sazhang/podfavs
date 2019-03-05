import React, { Component } from "react";
import { Global, css } from "@emotion/core";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import FeaturedPodcasts from "./FeaturedPodcasts";
import SearchBar from "./SearchBar";
import Footer from "./Footer";

// Landing page with a search bar and default list of podcasts.
const Main = styled.main`
  ${tw`flex flex-wrap w-full h-full justify-center bg-indigo-darkest`};
`;

const Container = styled.div`
  ${tw`min-h-screen w-full mx-5 max-w-2xl`};
`;

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      podcastList: [],
      userInput: "",
      title: "Featured podcasts",
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
        <Global
          styles={css`
            *,
            *:before,
            *:after {
              box-sizing: inherit;
            }
            html {
              text-rendering: optimizeLegibility;
              box-sizing: border-box;
              -ms-overflow-style: scrollbar;
              -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
              -webkit-font-smoothing: antialiased;
              -moz-osx-font-smoothing: grayscale;
            }
            html,
            body {
              ${tw`p-0 m-0 font-sans bg-indigo-lightest`};
            }
            h2 {
              ${tw`my-4 font-sans text-3xl sm:text-4xl text-indigo-lighter`};
            }
          `}
        />
        <Main>
          <SearchBar
            userInput={this.state.userInput}
            onHandleChange={this.handleChange}
            onHandleSubmit={this.handleSubmit}
          />
          <Container>
            <h2>{this.state.title}</h2>
            <FeaturedPodcasts podcastList={this.state.podcastList} />
          </Container>
          <Footer />
        </Main>
      </>
    );
  }
}

export default Home;
