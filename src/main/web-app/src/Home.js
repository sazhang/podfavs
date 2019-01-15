import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import './App.css';
import { Link } from 'react-router-dom';

// This landing page showcases a few podcasts.
class Home extends Component {

  constructor(props) {
    super(props);
    this.state = {podcasts: [], isLoading: true};
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/')
      .then(response => response.json())
      .then(data => this.setState({podcasts: data.content, isLoading: false}));
  }

  render() {
    const {podcasts, isLoading} = this.state;
    if (isLoading) {
      return <p>Loading...</p>;
    }

    // grab info from podcasts to populate the table
    const podcastList = podcasts.map(podcast => {
      return <tr key={podcast.id}>
        <td style={{whiteSpace: 'nowrap'}}>{podcast.name}</td>
        <td>{podcast.rating}</td>
        <td>{podcast.description}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/id/" + podcast.id}>Info</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <Container fluid>
          <h3>Podcasts</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="10%">Rating</th>
              <th>Description</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {podcastList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default Home;