import styled from "@emotion/styled";
import tw from "tailwind.macro";

// Global styles
export const Container = styled.div`
  ${tw`h-full w-full max-w-2xl m-8`};
`;

export const Wrapper = styled.div`
  ${tw`flex flex-wrap my-10 justify-center -mx-2`};
`;

export const HalfDiv = styled.div`
  ${tw`w-full h-full sm:w-1/2 px-2`};
`;

export const Button = styled.button`
  ${tw`border-none text-indigo-lighter hover:text-indigo rounded leading-tight focus:outline-none`};
`;

export const FormStyle = styled.form`
  ${tw`bg-indigo-lightest text-lg text-indigo-darker shadow-md rounded px-8 pt-6 pb-8 mb-6`};
`;

export const FormWrapper = styled.div`
  ${tw`flex-1 max-w-xs mx-auto`};
`;

export const FormLabel = styled.label`
  ${tw`block font-bold mb-2`};
`;

export const FormInput = styled.input`
  ${tw`border focus:border-indigo rounded w-full py-2 px-3 mb-3 shadow appearance-none`};
`;

export const FormBtn = styled.button`
  ${tw`bg-indigo-dark hover:bg-transparent text-indigo-lightest hover:text-indigo-dark hover:border hover:border-indigo font-semibold mt-3 py-2 px-4 rounded shadow`};
`;