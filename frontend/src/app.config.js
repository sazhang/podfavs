export default {
  url: 'https://dev-992080.okta.com',
  issuer: "https://dev-992080.okta.com/oauth2/default",
  redirect_uri: window.location.origin + "/implicit/callback",
  client_id: process.env.REACT_APP_CLIENT_ID
};
