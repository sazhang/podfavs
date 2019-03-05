import React, { Component } from "react";
import PropTypes from "prop-types";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

// A card that provides information about a podcast.
const Card = styled.div`
  ${tw`w-1/2 sm:w-1/5 p-2`};
`;

const Info = styled.div`
  ${tw`px-6 py-4`};
`;

const Descrip = styled.p`
  ${tw`text-grey-darker text-base`};
`;

const Title = styled.div`
  ${tw`font-bold text-xl mb-2`};
`;

export class PodcastItem extends Component {
  constructor(props) {
    super(props);
    this.state = { opacity: 0 };
    this.handleMouseEnter = this.handleMouseEnter.bind(this);
    this.handleMouseLeave = this.handleMouseLeave.bind(this);
  }

  handleMouseEnter() {
    this.setState(state => ({
      opacity: 1
    }));
  }

  handleMouseLeave() {
    this.setState(state => ({
      opacity: 0
    }));
  }

  render() {
    const aPodcast = this.props.podcast;

    return (
      <Card>
        <img
          src={aPodcast.imageUrl}
          alt="podcast cover"
        />
      </Card>
    );
  }
}

PodcastItem.propTypes = {
  podcast: PropTypes.object.isRequired
};

export default PodcastItem;
