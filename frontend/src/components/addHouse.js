import React, { useState } from "react"
import "./addHouse.css"
import { useHistory } from "react-router-dom"

const AddHouse = ({ fetchHouses }) => {
  const [location, setLocation] = useState("")
  const history = useHistory()

  const handleAddHouse = () => {
    const requestOptions = {
      method: "POST", // or 'PUT'
      content: "application/json",
    }
    fetch(`http://localhost:8080/addHouse/${location}`, requestOptions)
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
      })
      .catch((error) => {
        console.error("Error:", error)
      })
  }

  return (
    <div className="page">
      <h1>Add a new house</h1>
      <input
        type="text"
        placeholder="location"
        value={location}
        onChange={(e) => setLocation(e.target.value)}
      />
      <button onClick={handleAddHouse}>Add house</button>
    </div>
  )
}

export default AddHouse
