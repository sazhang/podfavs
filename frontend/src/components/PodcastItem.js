import React, { Component } from "react";
import PropTypes from "prop-types";
import { Card, CardImg, CardImgOverlay, CardTitle, CardText } from "reactstrap";
import "../App.css";

// A card that provides information about a podcast.
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
      <div>
        <Card inverse 
        style={cardStyle} 
        onMouseEnter={this.handleMouseEnter}
        onMouseLeave={this.handleMouseLeave}>
          <CardImg src={aPodcast.imageUrl} style={{backgroundColor: 'transparent'}} alt="Card image cap"/>
          <CardImgOverlay style={{ backgroundColor: "#01172F", opacity: this.state.opacity }}>
            <CardTitle style={{ fontSize: "1.1em", color: "#C9E4E7", opacity: this.state.opacity }}>{aPodcast.name}</CardTitle>
            <CardText><a href={aPodcast.url} style={linkStyle}>Learn more</a></CardText>
          </CardImgOverlay>
        </Card>
        <br/>
      </div>
    );
  }
}

PodcastItem.propTypes = {
  podcast: PropTypes.object.isRequired
};

const linkStyle = {
  position: 'absolute',
  top: '150px',
  left: '100px'
  /* 
  <CardTitle>{aPodcast.name}</CardTitle>
  <CardText>{aPodcast.description}</CardText>
  <CardText>{aPodcast.rating} out of 5</CardText> </CardImgOverlay>*/
}

const cardStyle = {
  width: "200px",
  height: "200px",
  margin: "0 auto"
};

export default PodcastItem;
