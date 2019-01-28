import React, { Component } from "react";
import { Button, Form, Input, Navbar, NavbarBrand, Nav, NavLink, NavItem, NavbarToggler, Collapse} from "reactstrap";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { faPodcast, faSearch } from "@fortawesome/free-solid-svg-icons";
import "../App.css";

library.add(faPodcast, faSearch, fab);

// Allow the user to get podcast recommendations by inputting keywords.
export class SearchBar extends Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false
    };
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
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
        <Navbar className="navbar navbar-expand-sm navbar-dark bg-dark" style={{marginBottom: "20px"}}>
          <NavbarBrand href="/"><FontAwesomeIcon icon="podcast" /> podfavs </NavbarBrand>
          <NavbarToggler onClick={this.toggle} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav navbar style={{ width: "100%" }}>
              <Form inline className="my-2 my-lg-0" onSubmit={this.handleSubmit}>
                <Input
                  className="form-control"
                  type="search"
                  name="search"
                  id="searchIt"
                  placeholder="Find your new favorite podcast by title or keywords"
                  value={this.props.userInput}
                  onChange={this.handleChange}
                />
                <Button style={{ backgroundColor: "#009FD4" }}><FontAwesomeIcon icon="search" /></Button>
              </Form>
            </Nav>
            <NavLink href="https://github.com/sazhang/podscraper">
              <FontAwesomeIcon icon={["fab", "github"]} className="fa-lg text-light"/>
            </NavLink>
          </Collapse>
        </Navbar>
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
