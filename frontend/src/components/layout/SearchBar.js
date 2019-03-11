import React, { Component } from "react";
import { Link } from "react-router-dom";
import { withAuth } from "@okta/okta-react";
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

export default withAuth(
  class SearchBar extends Component {
    constructor(props) {
      super(props);
      this.state = {
        authenticated: null,
        isMenuOpen: false,
        userQuery: "",
        toRecs: false
      };
      this.checkAuthentication = this.checkAuthentication.bind(this);
      this.checkAuthentication();

      this.login = this.login.bind(this);
      this.logout = this.logout.bind(this);
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
      this.handleClickHome = this.handleClickHome.bind(this);
      this.handleClickMenu = this.handleClickMenu.bind(this);
    }

    async checkAuthentication() {
      const authenticated = await this.props.auth.isAuthenticated();
      if (authenticated !== this.state.authenticated) {
        this.setState({ authenticated });
      }
    }

    componentDidUpdate() {
      this.checkAuthentication();
    }

    async login() {
      this.props.auth.login("/");
    }

    async logout() {
      this.props.auth.logout("/");
    }

    handleChange(event) {
      this.setState({ userQuery: event.target.value });
    }

    // Pass user query to generate search results in a new page
    handleSubmit(event) {
      event.preventDefault();
      const path = "/api/search/" + this.state.userQuery;
      this.props.history.push({
        pathname: path,
        state: { userQuery: this.state.userQuery }
      });
      event.target.reset(); // clear form
    }

    // Redirect to Home page
    handleClickHome() {
      this.props.history.push("/");
    }

    // Toggle menu button to reveal rest of navbar
    handleClickMenu() {
      this.setState({ isMenuOpen: !this.state.isMenuOpen });
    }

    render() {
      // If a user is authenticated, display a logout btn, else a login btn
      const navBtns = this.state.authenticated ? (
        <div>
          <GradientBtn className="mr-4" onClick={this.logout} />
        </div>
      ) : (
        <div>
          <GradientBtn className="mr-4" onClick={this.login}>
            {/* <Link to="/login">Log in</Link> */} Log in
          </GradientBtn>
          <GradientBtnTwo>
            <Link to="/register">Sign up</Link>
          </GradientBtnTwo>
        </div>
      );

      return (
        <Nav>
          <div>
            <IconBtn onClick={this.handleClickHome}>
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
                  placeholder="Search by show name or keywords..."
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
          <BtnDiv className={this.state.isMenuOpen ? "block" : "hidden"}>
            {navBtns}
          </BtnDiv>
        </Nav>
      );
    }
  }
);
