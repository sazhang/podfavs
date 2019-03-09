import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import wrapLayout from "../layout/Layout";
import { Container, Wrapper, IconBtn, HalfDiv } from "../styles/globalstyles";
import LoginForm from "./LoginForm";
import { ReactComponent as LoginSvg } from "../images/login.svg";
import { ReactComponent as BackIcon } from "../images/arrow-left.svg";

// Let user log into their account
class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      backToHome: false,
    };
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick() {
    this.setState({ backToHome: true });
  }

  render() {
    if (this.state.backToHome === true) {
      return <Redirect to="/" />;
    }

    return (
      <Container>
        <IconBtn
          onClick={() => {
            this.handleClick();
          }}
        >
          <BackIcon className="h-12 w-12" />
        </IconBtn>
        <Wrapper>
          <div className="sm:w-1/2 px-3">
            <h1>Check out new podcasts</h1>
            <LoginSvg className="w-full h-full" />
          </div>
          <div className="my-6 sm:my-0 px-3">
            <LoginForm />
          </div>
        </Wrapper>
      </Container>
    );
  }
}

export default wrapLayout(LoginPage);
