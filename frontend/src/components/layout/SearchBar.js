import React, { Component } from "react";
import { Link, Redirect } from "react-router-dom";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import { IconBtn, GradientBtn, GradientBtnTwo } from "../styles/globalstyles";
import { ReactComponent as HomeIcon } from "../images/headphones.svg";
import { ReactComponent as SearchIcon } from "../images/search.svg";
import { ReactComponent as MenuIcon } from "../images/menu.svg";

// Allow the user to get podcast recommendations by inputting keywords.
const Nav = styled.nav`
  ${tw`flex flex-wrap h-full w-full items-center justify-between bg-indigo-darker shadow px-5 sm:px-8 py-4`};
`;

const IconTitle = styled.span`
  ${tw`hidden sm:inline-block font-semibold text-2xl text-indigo-lightest hover:text-indigo-light no-underline`};
`;

const Search = styled.div`
  ${tw`flex items-center border-b border-b-2 border-indigo-light`}
`;

const Input = styled.input`
  ${tw`appearance-none bg-transparent border-none w-full text-indigo-lightest mr-3 py-1 px-2 leading-tight focus:outline-none`};
`;

const BtnDiv = styled.div`
  ${tw`md:flex justify-end md:items-center w-full md:w-auto`};
`;

export class SearchBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      toHome: false,
      toLoginPage: false,
      isMenuOpen: false
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleClickHome = this.handleClickHome.bind(this);
    this.handleClickMenu = this.handleClickMenu.bind(this);
  }

  handleChange(event) {
    this.props.onHandleChange(event.target.value);
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.onHandleSubmit();
  }

  handleClickHome(id) {
    if (id === 0) {
      this.setState({ toHome: true });
    } else {
      this.setState({ toLoginPage: true });
    }
  }

  handleClickMenu() {
    this.setState({ isMenuOpen: !this.state.isMenuOpen });
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
        <div>
          <IconBtn
            onClick={() => {
              this.handleClickHome(0);
            }}
          >
            <HomeIcon>
              <title>home</title>
            </HomeIcon>
            <IconTitle>podfavs</IconTitle>
          </IconBtn>
        </div>
        <div className="w-3/5 md:w-1/2 lg:w-3/5">
          <form onSubmit={this.handleSubmit}>
            <Search>
              <Input
                type="text"
                placeholder="Search by name and keywords..."
                value={this.props.userInput}
                onChange={this.handleChange}
                aria-label="Search"
              />
              <IconBtn>
                <SearchIcon>
                  <title>search</title>
                </SearchIcon>
              </IconBtn>
            </Search>
          </form>
        </div>
        <div className="block md:hidden">
          <IconBtn onClick={this.handleClickMenu}>
            <MenuIcon>
              <title>menu</title>
            </MenuIcon>
          </IconBtn>
        </div>
        <BtnDiv className={(this.state.isMenuOpen ? 'block' : 'hidden')}>
          <div>
            <GradientBtn className="mr-4">
              <Link to="/login">Log in</Link>
            </GradientBtn>
            <GradientBtnTwo>
              <Link to="/register">Sign up</Link>
            </GradientBtnTwo>
          </div>
        </BtnDiv>
      </Nav>
    );
  }
}

export default SearchBar;
