import React from "react"
import { useParams, useHistory } from "react-router-dom"
import Image from "../resources/default-house.jpg"
import "./housePage.css"

const HousePage = ({ houses, user, fetchHouses }) => {
  const history = useHistory()
  const { location } = useParams()
  const house = houses.filter((house) => location === house.location)[0]

  const handleDeleteHouse = () => {
    const requestOptions = {
      method: "DELETE", // or 'PUT'
      content: "application/json",
    }
    fetch(`https://8080-white-coyote-7xo3ngjz.ws.gitpod.stud.ntnu.no/removeHouse/${location}`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((msg) => {
        console.log(msg)
        fetchHouses()
        history.push("/")
        // history.push("/")
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
    fetch(
      `https://8080-white-coyote-7xo3ngjz.ws.gitpod.stud.ntnu.no/setAvailable/${location}/${!house.available}`,
      requestOptions
    )
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
          <h1 className="owner">{house.available ? "Available" : "Not available"}</h1>
          <img src={Image} width="400px" alt="house" />
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
        <h1>404 house not found</h1>
      )}
    </div>
  )
}

export default HousePage
