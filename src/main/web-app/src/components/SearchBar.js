import React, { Component } from "react";
import { Container, Form, FormGroup, Label, Input, Button } from "reactstrap";

export class SearchBar extends Component {
  constructor(props) {
    super(props);
    this.state = { input: '', currentList: [] };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    console.log("Entered handle change...");
    this.setState({ input: event.target.value });
  }

  handleSubmit(event) {
    var url = "api/rec/" + encodeURI(this.state.input);
    console.log(url);
    fetch(url)
      .then(response => response.json());
    //  .then(data => this.setState({ currentList: data.content }));
    console.log(this.state.currentList);
    //this.props.onHandleSubmit(this.state.currentList);
    event.preventDefault();
  }

  render() {
    return (
      <Container style={barStyle}>
        <Form inline onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="searchIt" style={formLabel}>
              Find your new favorite podcast.
            </Label>
            &nbsp; &nbsp;
            <Input
              type="search"
              name="search"
              id="searchIt"
              placeholder="Search by title, keywords..."
              size="40"
              value={this.state.input}
              onChange={this.handleChange}
            />
          </FormGroup>
          &nbsp; &nbsp;
          <Button style={{ backgroundColor: "#228CDB" }}>Submit</Button>
        </Form>
      </Container>
    );
  }
}

const barStyle = {
  backgroundColor: "#D7B49E",
  marginTop: "20px",
  marginBottom: "40px"
};

const formLabel = {
  fontSize: "2em",
  textDecoration: "underline #228CDB"
};

export default SearchBar;
