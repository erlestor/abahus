import React, { useState } from "react"
import "./addHouse.css"

const AddHouse = () => {
  const [location, setLocation] = useState("")

  const handleAddHouse = () => {
    // add house with api
    // go back to home page
  }

  return (
    <form className="page">
      <h1>Add a new house</h1>
      <input
        type="text"
        placeholder="location"
        value={location}
        onChange={(e) => setLocation(e.target.value)}
      />
      <button onClick={handleAddHouse}>Add house</button>
    </form>
  )
}

export default AddHouse
