import React from "react";
import Image from "../resources/default-house.jpg";

const house = ({ house }) => {
  return (
    <div className="house">
      <img src={Image} className="w-24 h-16" alt="house" />
      <h1 className="house-location">{house.location}</h1>
    </div>
  );
};

export default house;
