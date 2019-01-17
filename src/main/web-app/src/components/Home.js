import React, { Component } from "react";
import "../App.css";
import FeaturedPodcasts from "./FeaturedPodcasts";
import { Container, Row } from "reactstrap";
import SearchBar from "./SearchBar";

// This landing page showcases a few podcasts.
class Home extends Component {
  constructor(props) {
    super(props);
    this.state = { someList: [], isLoading: true };
  }

  componentDidMount() {
    this.setState({ isLoading: true });
    fetch("api/")
      .then(response => response.json())
      .then(data =>
        this.setState({ someList: data.content, isLoading: false })
      );
  }

  handleSubmit(someList) {
    console.log("Entered handle submit...");
    this.setState({ someList: someList });
  }

  render() {
    if (this.state.isLoading) {
      return <p>Loading...</p>;
    }
    return (
      <div style={divStyle}>
        <SearchBar onHandleSubmit={this.handleSubmit} />
        <Container>
          <h2>Featured podcasts</h2>
          <Row>
            <FeaturedPodcasts podcasts={this.state.someList} />
          </Row>
        </Container>
      </div>
    );
  }
}

const divStyle = {
  fontFamily: "houschka-rounded, sans-serif",
  maxWidth: "100%",
  maxHeight: "100%",
  backgroundColor: "#B8D5B8"
};

export default Home;
