import React from "react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

//Render a list of podcasts.
const FooterDiv = styled.footer`
  ${tw`flex-no-shrink w-full justify-center items-center bg-indigo-darker text-indigo-lighter shadow p-4`};
  a {
    ${tw`text-indigo-lighter hover:text-indigo`};
  }
`;

const Footer = () => (
  <FooterDiv>
    <div className="flex justify-center items-center">
      <span>Check out the project on&nbsp;</span>
      <a
        href="https://github.com/sazhang/podfavs"
        title="github"
        target="_blank"
        rel="noopener noreferrer"
      >
        github
      </a>
    </div>
  </FooterDiv>
);

export default Footer;
