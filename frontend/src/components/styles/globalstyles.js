import styled from "@emotion/styled";
import { css } from "@emotion/core";
import tw from "tailwind.macro";

// Global styles
export const Container = styled.div`
  ${tw`w-full justify-center mt-8`};
`;

export const MaxWidth = styled.div`
  ${tw`max-w-3xl mx-auto`};
`;

export const Wrapper = styled.div`
  ${tw`flex flex-wrap justify-center items-center`}; 
`;

export const IconBtn = styled.button`
  ${tw`border-none text-indigo-lighter hover:text-indigo rounded leading-tight focus:outline-none`};
`;

export const FormStyle = styled.form`
  ${tw`bg-indigo-lightest text-xl text-indigo-darker shadow-md rounded px-8 pt-6 pb-8`};
`;

export const FormWrapper = styled.div`
  ${tw`flex-1 max-w-xs`};
`;

export const FormLabel = styled.label`
  ${tw`block font-bold mb-2`};
`;

export const FormInput = styled.input`
  ${tw`focus:outline-none focus:shadow-outline focus:shadow-indigo rounded w-full py-2 px-3 mb-3 shadow-md appearance-none`};
`;

const gradientBtnStyle = css`
  ${tw`font-semibold py-2 px-4 rounded shadow text-white inline-block mt-4 md:mt-0`};
  transition: 0.5s;
  background-size: 200% auto;
  &:hover {
    background-position: right center;
  }
  a {
    ${tw`text-white no-underline`};
  }
`;

export const GradientBtn = styled.button`
  ${gradientBtnStyle}
  background-image: linear-gradient(to right, #B24592 0%, #F15F79 51%, #B24592 100%);
`;

export const GradientBtnTwo = styled.button`
  ${gradientBtnStyle}
  background-image: linear-gradient(to right, #F15F79 0%, #B24592 51%, #F15F79 100%);
`;

export const CardDeck = styled.div`
  ${tw`flex flex-wrap -mx-3`};
`;

export const SvgBtn = styled.button`
  svg {
    stroke: #6574cd;
  }
  &:hover {
    svg {
      fill: #6574cd;
    }
  }
`;
