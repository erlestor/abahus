import React from "react"
import Image from "../resources/default-house.jpg"
import { Link } from "react-router-dom"

const house = ({ house }) => {
  return (
    <Link to={"/house/" + house.location}>
      <div className="house">
        <img src={Image} className="w-24 h-16" alt="house" />
        <h1 className="house-location">{house.location}</h1>
      </div>
    </Link>
  )
}

export default house
