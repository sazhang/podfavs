import React from "react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

//Render a list of podcasts.
const FooterDiv = styled.footer`
  ${tw`flex w-full h-16 justify-center items-center bg-indigo-darker text-indigo-lighter shadow`};
  a {
    ${tw`text-indigo-lighter hover:text-indigo`};
  }
`;

const Footer = () => (
  // populate the table with podcast components
  <FooterDiv>
    <span>Check out the project on&nbsp;</span>
    <a
      href="https://github.com/sazhang/podfavs"
      title="github"
      target="_blank"
      rel="noopener noreferrer"
    >
      github
    </a>
  </FooterDiv>
);

export default Footer;
