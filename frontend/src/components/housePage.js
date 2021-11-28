import React from "react"
import { useParams, useHistory } from "react-router-dom"
import Image from "../resources/default-house.jpg"
import "./housePage.css"
import url from "../url"

const HousePage = ({ houses, user, fetchHouses }) => {
  const history = useHistory()
  // this location is the browser location, not of the house :)
  const { location } = useParams()
  const house = houses.filter((house) => location === house.location)[0]

  const handleDeleteHouse = () => {
    const requestOptions = {
      method: "DELETE", // or 'PUT'
      content: "application/json",
    }
    fetch(`${url}/removeHouse/${location}`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((msg) => {
        console.log(msg)
        fetchHouses()
        // redirect to landing page
        history.push("/")
      })
      .catch((error) => {
        console.error("Error:", error)
        fetchHouses()
      })
  }

  const handleToggleAvailability = () => {
    const requestOptions = {
      method: "POST", // or 'PUT'
      content: "application/json",
    }
    fetch(`${url}/setAvailable/${location}/${!house.available}`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((msg) => {
        console.log(msg)
        fetchHouses()
      })
      .catch((error) => {
        console.error("Error:", error)
      })
  }

  return (
    <div className="page">
      {house ? (
        <>
          <h1 className="location">{house.location}</h1>
          <h1 className="owner">
            {house.available ? "Available" : "Not available"}
          </h1>
          {/* we used a default to improve looks without overcomplicating the app */}
          <img src={Image} width="400px" alt="house" />
          {/* if the user is the owner show buttons */}
          {house.user === user ? (
            <>
              <button className="btn" onClick={handleDeleteHouse}>
                Delete
              </button>
              <button className="btn" onClick={handleToggleAvailability}>
                Toggle availability
              </button>
            </>
          ) : (
            <h1 className="owner">Contact owner to rent: {house.user}</h1>
          )}
        </>
      ) : (
        // house is undefined it means the house doesn't exist
        <h1>404 house not found</h1>
      )}
    </div>
  )
}

export default HousePage
