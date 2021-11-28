import React from "react"
import Image from "../resources/default-house.jpg"
import { Link } from "react-router-dom"

const house = ({ house }) => {
  return (
    // links to housePage
    <Link to={"/house/" + house.location}>
      <div className="house">
        {/* image is a default one to keep things simple */}
        <img src={Image} className="w-24 h-16" alt="house" />
        <h1 className="house-location">{house.location}</h1>
      </div>
    </Link>
  )
}

export default house
