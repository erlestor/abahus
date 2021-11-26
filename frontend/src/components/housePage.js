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
    fetch(`http://localhost:8080/removeHouse/${location}`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((msg) => {
        console.log(msg)
        fetchHouses()
        // history.push("/")
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
          <img src={Image} width="400px" alt="house" />
          {house.user === user ? (
            <button className="btn" onClick={handleDeleteHouse}>
              Delete
            </button>
          ) : (
            <h1 className="owner">Owner: {house.user}</h1>
          )}
        </>
      ) : (
        <h1>404 house not found</h1>
      )}
    </div>
  )
}

export default HousePage
