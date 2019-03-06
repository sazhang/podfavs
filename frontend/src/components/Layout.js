import React, { Component } from "react";
import { Global, css } from "@emotion/core";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import Footer from "./Footer";

// Higher-order component
const Main = styled.main`
  ${tw`flex flex-wrap w-full h-full justify-center`};
`;

const wrapLayout = WrappedComponent => {
  return class extends Component {
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
              h1, h2, h3 {
                ${tw`my-4 font-sans`};
              }
              h1 {
                ${tw`text-5xl lg:text-xxl text-indigo-lightest`};
              }
              h2 {
                ${tw`text-3xl sm:text-4xl text-indigo-lighter`};
              }
              h3 {
                ${tw`text-3xl text-indigo-lighter`};
              }
              a {
                ${tw`text-indigo-lighter hover:text-indigo`};
              }
            `}
          />
          <Main>
            <WrappedComponent {...this.props} />
          </Main>
          <Footer />
        </>
      );
    }
  };
};

export default wrapLayout;
