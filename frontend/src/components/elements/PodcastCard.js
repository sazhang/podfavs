import React, { Component } from "react";
import PropTypes from "prop-types";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import LazyLoad from "react-lazy-load";
import PodcastImg from "./PodcastImg";
import SaveBtn from "./SaveBtn";

// A card that provides information about a podcast
const CardWrapper = styled.div`
  ${tw`w-full sm:w-1/2 lg:w-1/3`};
`;

const Card = styled.div`
  ${tw`flex flex-wrap shadow-lg rounded bg-grey-lightest items-center p-4 m-3`};
`;

const InfoWrapper = styled.div`
  ${tw`flex h-32 w-full justify-between`};
`;

const Info = styled.div`
  ${tw`flex-1 overflow-hidden`};
`;

const Description = styled.p`
  ${tw`text-sm sm:text-base text-grey-dark leading-tight`};
`;

const ImgDiv = styled.div`
  ${tw`flex-no-shrink ml-2 md:ml-4 w-24 lg:w-32`};
`;

const ButtonsDiv = styled.div`
  ${tw`flex w-full justify-between mt-3`};
`;

const CardBtn = styled.a`
  ${tw`text-indigo font-semibold bg-indigo-lightest border border-indigo-lightest no-underline text-sm rounded hover:bg-transparent hover:border-indigo-lighter p-2`};
`;

class PodcastCard extends Component {
  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      userId: "",
      isSaved: false
    };
    this.handleClickSave = this.handleClickSave.bind(this);
  }

  shortenDescription(name, descrip) {
    if (name.length + descrip.length < 120) {
      return descrip;
    } else {
      const charsLeft = 120 - name.length;
      var shortDescrip = descrip.substr(0, descrip.lastIndexOf(" ", charsLeft));
      return shortDescrip.concat("...");
    }
  }

  handleClickSave() {
    if (this.state.userId.length > 0) {
      this.setState({ isSaved: !this.state.isSaved });
    } else {
      console.log("Not auth!");
    }
  }

  render() {
    const aPodcast = this.props.podcast;
    const name = aPodcast.name;
    const descrip = aPodcast.description;
    const link = aPodcast.url;
    const fill = this.state.isSaved ? "#6574cd" : "none";

    return (
      <CardWrapper>
        <Card>
          <InfoWrapper>
            <Info>
              <h5>{aPodcast.categories[0].category.toUpperCase()}</h5>
              <h4>{name}</h4>
              <Description>
                {this.shortenDescription(name, descrip)}
              </Description>
            </Info>
            <ImgDiv>
              <LazyLoad debounce={false} offsetVertical={100}>
                <PodcastImg imageUrl={aPodcast.imageUrl} />
              </LazyLoad>
            </ImgDiv>
          </InfoWrapper>
          <ButtonsDiv>
            <CardBtn href={link} target="_blank">
              Learn more
            </CardBtn>
            <SaveBtn onClick={this.handleClickSave} fill={fill} />
          </ButtonsDiv>
        </Card>
      </CardWrapper>
    );
  }
}

PodcastCard.propTypes = {
  podcast: PropTypes.object.isRequired
};

export default PodcastCard;
