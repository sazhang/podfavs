import React, { Component } from "react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

// Allow the user to get podcast recommendations by inputting keywords.
const Nav = styled.nav`
  ${tw`flex flex-wrap w-full items-center justify-between bg-indigo-darker shadow px-8 py-4 mb-8`};
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

const Button = styled.button`
  ${tw`border-none text-indigo-lighter hover:text-indigo rounded leading-tight`};
`;

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
      <Nav>
        <div className="hidden sm:block">
          <IconTitle>podfavs</IconTitle>
        </div>
        <div className="block sm:hidden">
          <Button>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="feather feather-home"
            >
              <title>home</title>
              <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z" />
              <polyline points="9 22 9 12 15 12 15 22" />
            </svg>
          </Button>
        </div >
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
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="24"
                  height="24"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  className="feather feather-search"
                >
                  <title>search</title>
                  <circle cx="11" cy="11" r="8" />
                  <line x1="21" y1="21" x2="16.65" y2="16.65" />
                </svg>
              </Button>
            </Search>
          </form>
        </div>
        <div>
          <Button>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="feather feather-log-in"
            >
              <title>login</title>
              <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4" />
              <polyline points="10 17 15 12 10 7" />
              <line x1="15" y1="12" x2="3" y2="12" />
            </svg>
          </Button>
        </div>
      </Nav>
    );
  }
}

export default SearchBar;
