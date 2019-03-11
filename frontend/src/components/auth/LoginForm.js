import React, { Component } from "react";
import { Container, Wrapper, FormStyle, FormWrapper, FormInput, FormLabel, GradientBtn } from "../styles/globalstyles"
import { ReactComponent as LoginSvg } from "../images/login.svg";

// Let user login into their account
class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      sessionToken: null,
      error: null
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
  }

  handleSubmit(e) {
    e.preventDefault();
    console.log("Successful login!"); //TODO: placeholder
  }

  handleUsernameChange(e) {
    this.setState({ username: e.target.value });
  }

  handlePasswordChange(e) {
    this.setState({ password: e.target.value });
  }

  render() {
    return (
      <Container>
        <Wrapper>
          <div className="sm:w-1/2 px-3">
            <h1>Save podcasts to binge later</h1>
            <LoginSvg className="w-full h-full" />
          </div>
          <div className="my-6 sm:my-0 px-3">
            <FormWrapper>
              <h3 className="text-center">Welcome Back</h3>
              <FormStyle onSubmit={this.handleSubmit}>
                <div>
                  <FormLabel htmlFor="username">Username</FormLabel>
                  <FormInput
                    type="text"
                    value={this.state.username}
                    onChange={this.handleUsernameChange}
                  />
                </div>

                <div>
                  <FormLabel htmlFor="password">Password</FormLabel>
                  <FormInput
                    type="password"
                    value={this.state.password}
                    onChange={this.handlePasswordChange}
                  />
                </div>
                <GradientBtn className="mt-3" type="button">
                  Log in
                </GradientBtn>
              </FormStyle>
            </FormWrapper>
          </div>
        </Wrapper>
      </Container>
    );
  }
}

export default LoginForm;
