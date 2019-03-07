import React, { Component } from "react";
import { FormStyle, FormWrapper, FormInput, FormLabel, GradientBtn } from "../styles/globalstyles"

// Let user register for an account
class RegistrationForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      email: "",
      password: "",
      sessionToken: null
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handleEmailChange = this.handleEmailChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
  }

  handleUsernameChange(e) {
    this.setState({ username: e.target.value });
  }

  handleEmailChange(e) {
    this.setState({ email: e.target.value });
  }

  handlePasswordChange(e) {
    this.setState({ password: e.target.value });
  }

  handleSubmit(e) {
    e.preventDefault();
    console.log("Successful registration!"); //TODO: placeholder
  }

  render() {
    return (
      <FormWrapper>
        <h3 className="text-center">Create Account</h3>
        <FormStyle onSubmit={this.handleSubmit}>
          <div>
            <FormLabel htmlFor="email">Email</FormLabel>
            <FormInput
              type="email"
              value={this.state.email}
              onChange={this.handleEmailChange}
            />
          </div>
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
          <GradientBtn className="mt-3" type="button">Sign up</GradientBtn>
        </FormStyle>
      </FormWrapper>
    );
  }
}

export default RegistrationForm;
