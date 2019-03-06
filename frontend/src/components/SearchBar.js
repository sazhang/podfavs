import React, { Component } from "react";
import { Link, Redirect } from "react-router-dom";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import { Button } from "./styles/globalstyles";
import { ReactComponent as HomeIcon } from "./images/home.svg";
import { ReactComponent as LoginIcon } from "./images/log-in.svg";
import { ReactComponent as SearchIcon } from "./images/search.svg";

// Allow the user to get podcast recommendations by inputting keywords.
const Nav = styled.nav`
  ${tw`flex flex-wrap h-full w-full items-center justify-between bg-indigo-darker shadow px-8 py-4`};
`;

const IconTitle = styled.span`
  ${tw`font-semibold text-2xl text-indigo-lightest`};
`;

const Search = styled.div`
  ${tw`flex items-center border-b border-b-2 border-indigo-light`}
`;

const Input = styled.input`
  ${tw`appearance-none bg-transparent border-none w-full text-indigo-lightest mr-3 py-1 px-2 leading-tight focus:outline-none`};
`;

export class SearchBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      toHome: false,
      toLoginPage: false
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  handleChange(event) {
    this.props.onHandleChange(event.target.value);
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.onHandleSubmit();
  }

  handleClick(id) {
    if (id === 0) {
      this.setState({ toHome: true });
    } else {
      this.setState({ toLoginPage: true });
    }
  }

  render() {
    if (this.state.toHome === true) {
      return <Redirect to="/" />;
    }

    if (this.state.toLoginPage === true) {
      return <Redirect to="/login" />;
    }

    return (
      <Nav>
        <div className="hidden sm:block">
          <IconTitle>podfavs</IconTitle>
        </div>
        <div className="block sm:hidden">
          <Button
            onClick={() => {
              this.handleClick(0);
            }}
          >
            <HomeIcon />
          </Button>
        </div>
        <div className="w-3/5">
          <form onSubmit={this.handleSubmit}>
            <Search>
              <Input
                type="text"
                placeholder="Find your new favorite podcast by name and keywords..."
                value={this.props.userInput}
                onChange={this.handleChange}
                aria-label="Search"
              />
              <Button>
                <SearchIcon />
              </Button>
            </Search>
          </form>
        </div>
        <div>
          <Button
            onClick={() => {
              this.handleClick(1);
            }}
          >
            <LoginIcon />
            <Link to="/login" />
          </Button>
        </div>
      </Nav>
    );
  }
}

export default SearchBar;
