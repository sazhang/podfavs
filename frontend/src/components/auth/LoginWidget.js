import React, { Component } from "react";
import ReactDOM from "react-dom";
import OktaSignIn from "@okta/okta-signin-widget";
import "@okta/okta-signin-widget/dist/css/okta-sign-in.min.css";
import "@okta/okta-signin-widget/dist/css/okta-theme.css";

// Okta widget handles both sign in and sign up
class LoginWidget extends Component {
  componentDidMount() {
    const el = ReactDOM.findDOMNode(this);
    this.widget = new OktaSignIn({
      baseUrl: this.props.baseUrl,
      //client_id: this.props.client_id,
      //redirect_uri: this.props.redirect_uri,
      i18n: {
        en: {
          "primaryauth.title": "Discover and save bingeworthy podcasts",
          "primaryauth.submit": "Log in",
          "primaryauth.username.placeholder": "Email",
          "primaryauth.username.tooltip": "Email"
        }
      },
      features: {
        registration: true,
        rememberMe: true
      }
      //logo:
    });
    this.widget.renderEl({ el }, this.props.onSuccess, this.props.onError);
  }

  componentWillUnmount() {
    this.widget.remove();
  }

  render() {
    return <div />;
  }
}

export default LoginWidget;
