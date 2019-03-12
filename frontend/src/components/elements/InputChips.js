import React from "react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";

// Display user input chips
const Chip = styled.div`
  ${tw`text-indigo-darkest font-semibold bg-indigo-lighter rounded-full py-2 px-3 mr-2 text-center`};
`;

const InputChips = ({ query }) => {
  const chips = [];
  query.map(item =>
    chips.push(<Chip key={item}>{item}</Chip>)
  );

  // populate the table with podcasts
  return (
    <>
      <div className="inline-flex mb-8">{chips}</div>
    </>
  );
};

export default InputChips;
