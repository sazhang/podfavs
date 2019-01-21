import React, { Component } from "react";
import {Container, Form, FormGroup, Label, Input, Button} from "reactstrap";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

library.add(faSearch);

// Allow the user to get podcast recommendations by inputting keywords.
export class SearchBar extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.props.onHandleChange(event.target.value);
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.onHandleSubmit();
  }

  render() {
    return (
      <div>
        <Container style={barStyle}>
          <Form inline onSubmit={this.handleSubmit}>
            <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
              <Input
                type="search"
                name="search"
                id="searchIt"
                placeholder="Search by title, keywords..."
                //size="40"
                value={this.props.userInput}
                onChange={this.handleChange}
              />
            </FormGroup>
            <Button style={{ backgroundColor: "#007FFF" }}>
              <FontAwesomeIcon icon="search" />
            </Button>
          </Form>
        </Container>
      </div>
    );
  }
}

const barStyle = {
  backgroundColor: "#D7B49E"
  //marginTop: '20px',
  //marginBottom: '40px'
};

const formLabel = {
  fontSize: "2.5em",
  color: "#007FFF"
};

export default SearchBar;
