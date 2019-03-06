import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import wrapLayout from "../Layout";
import { Container, Wrapper, FormWrapper, HalfDiv, Button } from "../styles/globalstyles";
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import { ReactComponent as LoginSvg } from "../images/login.svg";
import { ReactComponent as BackIcon } from "../images/arrow-left.svg";

// Let user login to their account or register for an account
class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      backToHome: false
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
        <Button
          onClick={() => {
            this.handleClick();
          }}
        >
          <BackIcon className="h-12 w-12" />
        </Button>
        <Wrapper>
          <HalfDiv>
            <h1>Discover and save binge-worthy podcasts.</h1>
            <LoginSvg className="w-full h-full" />
          </HalfDiv>
          <HalfDiv>
            <LoginForm />
            <FormWrapper className="h-1 border-t-4 border-indigo-darker" />
            <RegisterForm />
          </HalfDiv>
        </Wrapper>
      </Container>
    );
  }
}

export default wrapLayout(LoginPage);
