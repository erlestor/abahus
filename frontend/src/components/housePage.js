import React from "react"
import { useParams } from "react-router-dom"
import Image from "../resources/default-house.jpg"
import "./housePage.css"

const HousePage = ({ houses, user }) => {
  const { location } = useParams()
  const house = houses.filter((house) => location === house.location)[0]

  const handleDeleteHouse = () => {}

  return (
    <div className="page">
      {house ? (
        <>
          <h1 className="location">Location</h1>
          <img src={Image} width="400px" alt="house" />
          {house.user.email === user.email && (
            <button className="btn" onClick={handleDeleteHouse}>
              Delete
            </button>
          )}
        </>
      ) : (
        <h1>404 house not found</h1>
      )}
    </div>
  )
}

export default HousePage
