import React, { Component } from "react";
import { Global, css } from "@emotion/core";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import SearchBar from "./SearchBar";
import Footer from "./Footer";

// Higher-order component to wrap pages for a consistent look
const Main = styled.main`
  ${tw`flex flex-wrap w-full h-full justify-center`};
`;

const wrapLayout = WrappedComponent => {
  return class extends Component {
    constructor(props) {
      super(props);
      this.state = {
        podcastRecs: [],
        userQuery: ""
      };
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(userQuery) {
      this.setState({ userQuery: userQuery });
      console.log(this.state.userQuery);
    }

    handleSubmit() {
      var url = "api/search/" + encodeURI(this.state.userQuery);
      console.log(url);
      fetch(url)
        .then(response => response.json())
        .then(data => this.setState({ podcastRecs: data }))
        .catch(err => console.error("Caught error: ", err));
      console.log(this.state.podcastRecs);
    }

    render() {
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
                ${tw`p-0 m-0 font-sans bg-indigo-darkest`};
              }
              h1, h3, h4, h5 {
                ${tw`font-sans`};
              }
              h1 {
                ${tw`text-3xl sm:text-4xl sm:text-5xl lg:text-xxl text-indigo-lightest my-4`};
              }
              h3 {
                ${tw`text-2xl sm:text-3xl text-indigo-lighter my-4`};
              }
              h4 {
                ${tw`text-base sm:text-lg text-indigo-darker mb-2`};
              }
              h5 {
                ${tw`text-indigo font-normal`};
              }
            `}
          />
          <Main>
            <SearchBar
              userQuery={this.state.userQuery}
              onHandleChange={this.handleChange}
              onHandleSubmit={this.handleSubmit}
            />
            <WrappedComponent
              {...this.props}
              podcastRecs={this.state.podcastRecs}
            />
          </Main>
          <Footer />
        </>
      );
    }
  };
};

export default wrapLayout;
