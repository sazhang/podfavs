import React, { Component } from "react";
import PropTypes from "prop-types";
import { Card, CardTitle, CardText, CardImg, CardImgOverlay } from "reactstrap";

export class PodcastItem extends Component {

  render() {
    const aPodcast = this.props.podcast;

    return (
      <div>
        <Card inverse style={cardStyle}>
          <CardImg src={aPodcast.imageUrl} alt="Card image cap"/>
          <CardImgOverlay style={{ backgroundColor: "#333", opacity: "0.5" }}></CardImgOverlay>
        </Card>
        <br/>
      </div>
    );
  }
}

PodcastItem.propTypes = {
  podcast: PropTypes.object.isRequired
};

const overlayStyle = {
  /* 
  <CardTitle>{aPodcast.name}</CardTitle>
  <CardText>{aPodcast.description}</CardText>
  <CardText>{aPodcast.rating} out of 5</CardText> </CardImgOverlay>*/
}

const cardStyle = {
  height: "auto",
  width: "14rem",
  alignSelf: "center"
};

export default PodcastItem;
