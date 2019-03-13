import React from "react";
import PropTypes from "prop-types";

// Bookmark button used in podcast card components
function SaveBtn({ fill, onClick }) {
  return (
    <button className="focus:outline-none" onClick={onClick}>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="24"
        height="24"
        viewBox="0 0 24 24"
        fill={fill}
        strokeWidth="2"
        strokeLinecap="round"
        strokeLinejoin="round"
        className="stroke-current text-indigo-light hover:text-indigo-dark"
      >
        <path d="M19 21l-7-5-7 5V5a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2z" />
      </svg>
    </button>
  );
}

SaveBtn.propTypes = {
  fill: PropTypes.string.isRequired,
  onClick: PropTypes.func.isRequired
};

export default SaveBtn;
