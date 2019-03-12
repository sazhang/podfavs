import React, { Component } from "react";
import PropTypes from "prop-types";
import { keyframes, css } from "@emotion/core";

// Lazy load and fade in images to improve web performance
const fadeIn = keyframes`
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
`;

const loading = css`
  opacity: 0;
  width: 100%;
  height: auto;
`;

const loaded = css`
  animation: ${fadeIn} 1s ease;
  position: relative;
  opacity: 0;
  animation-fill-mode: forwards;
  animation-delay: 0.1s;
`;

class PodcastImg extends Component {
  state = {
    loaded: false,
    styling: null
  };

  onLoad = () => this.setState({ loaded: true });

  render() {
    let { styling } = this.state;
    styling = this.state.loaded ? loaded : loading;
    return (
      <img
        css={css`
          ${styling};
        `}
        className="rounded"
        src={this.props.imageUrl}
        alt="podcast cover"
        onLoad={this.onLoad}
      />
    );
  }
}

PodcastImg.propTypes = {
  imageUrl: PropTypes.string
};

export default PodcastImg;
