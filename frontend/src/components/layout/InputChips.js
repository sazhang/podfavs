import React from "react";
import styled from "@emotion/styled";
import tw from "tailwind.macro";
import { btnStyle } from "../styles/globalstyles";

// Display user input chips
const Chip = styled.div`
  ${btnStyle}
  ${tw`rounded-full`};
`;

const InputChips = ({ query }) => {
  const chips = [];
  query.map(item =>
    chips.push(<Chip key={item}>{item}</Chip>)
  );

  // populate the table with podcasts
  return (
    <>
      <div className="inline-flex">{chips}</div>
    </>
  );
};

export default InputChips;
