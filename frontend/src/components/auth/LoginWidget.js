import React, { Component } from "react";
import ReactDOM from "react-dom";
import OktaSignIn from "@okta/okta-signin-widget";
import "@okta/okta-signin-widget/dist/css/okta-sign-in.min.css";
import "@okta/okta-signin-widget/dist/css/okta-theme.css";

class LoginWidget extends Component {
  componentDidMount() {
    const el = ReactDOM.findDOMNode(this);
    this.widget = new OktaSignIn({
      baseUrl: this.props.baseUrl,
      clientId: this.props.clientId,
      redirectUri: this.props.redirectUri,
      i18n: {
        en: {
          "primaryauth.title": "Find podcasts you'll enjoy",
          "primaryauth.submit": "Log in"
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
