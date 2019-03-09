import React, { Component } from "react";
import PropTypes from "prop-types";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

// A card that provides information about a podcast.
const CardWrapper = styled.div`
  ${tw`w-full sm:w-1/2 lg:w-1/3 max-w-sm`};
`;

const Card = styled.div`
  ${tw`flex flex-wrap shadow-lg rounded bg-grey-lightest items-center p-4 m-2`};
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
  ${tw`flex-no-shrink ml-2 md:ml-4`};
`;

const ButtonsDiv = styled.div`
  ${tw`flex w-full justify-between mt-2`};
`;

const CardBtn = styled.button`
  ${tw`text-xs font-semibold rounded px-4 py-1 leading-normal bg-white border border-purple text-purple hover:bg-purple hover:text-white`};
`;

class PodcastCard extends Component {
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

  shortenDescription(name, descrip) {
    if (name.length + descrip.length < 120) {
      return descrip;
    } else {
      const charsLeft = 120 - name.length;
      var shortDescrip = descrip.substr(0, descrip.lastIndexOf(" ", charsLeft));
      return shortDescrip.concat("...");
    }
  }

  render() {
    const aPodcast = this.props.podcast;
    const name = aPodcast.name;
    const descrip = aPodcast.description;
    console.log(aPodcast);

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
              <img
                className="w-24 lg:w-32 rounded"
                src={aPodcast.imageUrl}
                alt="podcast cover"
              />
            </ImgDiv>
          </InfoWrapper>
          <ButtonsDiv>
            <CardBtn>
              Learn more
            </CardBtn>
            <CardBtn>
              Save
            </CardBtn>
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

/* <Card>
        <img
          src={aPodcast.imageUrl}
          alt="podcast cover"
        />
      </Card> */
