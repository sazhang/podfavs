import React from "react";
import { Container, Wrapper } from "../styles/globalstyles";
import RegisterForm from "./RegisterForm";
import { ReactComponent as RegisterSvg } from "../images/audience.svg";

// Let user register for an account
const RegisterPage = () => {
  return (
    <Container>
      <Wrapper>
        <div className="sm:w-1/2 px-3">
          <h1>Find podcasts you will enjoy</h1>
          <RegisterSvg className="w-full h-full" />
        </div>
        <div className="my-6 sm:my-0 px-3">
          <RegisterForm />
        </div>
      </Wrapper>
    </Container>
  );
};

export default RegisterPage;
