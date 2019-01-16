import React, { Component } from "react";
import "../App.css";
import Podcasts from "./Podcasts";
import { Container, CardDeck, Row, Col } from "reactstrap";

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

  render() {
    const { someList, isLoading } = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }

    console.log(this.state.someList);

    return (
      <div>
        <h1>Home</h1>
        <Container fluid style={containerStyle}>
          <Row>
            <Podcasts podcasts={this.state.someList}/>
          </Row>
        </Container>
        <h1>Footer</h1>
      </div>
    );
  }
}

const containerStyle = {
  alignItems: "center",
  justifyContent: "center",
}

export default Home;
