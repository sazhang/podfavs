import React, { Component } from "react";
import { FormStyle, FormWrapper, FormInput, FormLabel, FormBtn } from "../styles/globalstyles"

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
      <FormWrapper>
      <h3>Welcome Back</h3>
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
          <FormBtn type="button">Log in</FormBtn>
        </FormStyle>
      </FormWrapper>
    );
  }
}

export default LoginForm;
