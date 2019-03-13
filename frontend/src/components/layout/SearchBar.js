import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { withAuth } from "@okta/okta-react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import { IconBtn } from "../styles/globalstyles";
import { ReactComponent as HomeIcon } from "../images/headphones.svg";
import { ReactComponent as SearchIcon } from "../images/search.svg";
import { ReactComponent as LogoutIcon } from "../images/log-out.svg";
import { ReactComponent as UserIcon } from "../images/user.svg";

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
  ${tw`block md:flex justify-end md:items-center w-full md:w-auto`};
`;

export default withAuth(
  withRouter(
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
        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.toHome = this.toHome.bind(this);
        this.toDashboard = this.toDashboard.bind(this);
      }

      async checkAuthentication() {
        const authenticated = await this.props.auth.isAuthenticated();
        if (authenticated !== this.state.authenticated) {
          this.setState({ authenticated });
        }
      }

      async componentDidMount() {
        await this.checkAuthentication();
      }
    
      async componentDidUpdate() {
        await this.checkAuthentication();
      }

      async login() {
        this.props.auth.login("/dashboard");
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
        const path = "/search/" + this.state.userQuery;
        this.props.history.push({
          pathname: path,
          state: { userQuery: this.state.userQuery }
        });
        event.target.reset(); // clear form
      }

      // Redirect to Home
      toHome() {
        this.props.history.push("/");
      }

      // Redirect to Dashboard
      toDashboard() {
        this.props.history.push("/dashboard");
      }

      render() {
        // If a user is authenticated, display a logout btn, else a login btn
        const navBtns = this.state.authenticated ? (
          <div>
            <IconBtn className="mr-4" onClick={this.toDashboard}>
              <UserIcon>
                <title>dashboard</title>
              </UserIcon>
            </IconBtn>
            <IconBtn onClick={this.logout}>
              <LogoutIcon>
                <title>log out</title>
              </LogoutIcon>
            </IconBtn>
          </div>
        ) : (
          <div>
            <IconBtn onClick={this.login}>
              <UserIcon>
                <title>dashboard</title>
              </UserIcon>
            </IconBtn>
          </div>
        );

        return (
          <Nav>
            <div>
              <IconBtn onClick={this.toHome}>
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
            {navBtns}
          </Nav>
        );
      }
    }
  )
);
