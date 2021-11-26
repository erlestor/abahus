import React, { useState, useEffect } from "react"
import ReactDOM from "react-dom"
import "./index.css"
import Landing from "./components/landing"
import Login from "./components/login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import HousePage from "./components/housePage"
import Layout from "./components/layout"
import AddHouse from "./components/addHouse"

const RouterConfig = () => {
  const [user, setUser] = useState("")
  const [houses, setHouses] = useState([])

  useEffect(() => {
    fetchHouses()
    setUser(localStorage.getItem("user"))
  }, [])

  const fetchHouses = () => {
    const requestOptions = {
      method: "GET", // or 'PUT'
      content: "application/json",
    }

    fetch("http://localhost:8080/houses", requestOptions)
      .then((response) => response.json())
      .then((houseMap) => {
        const houses = []
        const locations = Object.keys(houseMap)
        for (const i in locations) {
          const location = locations[i]
          houses.push({ location: location, user: houseMap[location] })
        }
        setHouses(houses)
      })
      .catch((error) => {
        console.error("Error:", error)
      })
  }

  return (
    <React.StrictMode>
      <Router>
        <Layout user={user} setUser={setUser}>
          <Switch>
            <Route path="/" exact>
              <Landing houses={houses} user={user} />
            </Route>
            <Route path="/login" exact>
              <Login setUser={setUser} />
            </Route>
            <Route path="/add-house" exact>
              <AddHouse fetchHouses={fetchHouses} />
            </Route>
            <Route path="/house/:location">
              <HousePage
                houses={houses}
                user={user}
                fetchHouses={fetchHouses}
              />
            </Route>
          </Switch>
        </Layout>
      </Router>
    </React.StrictMode>
  )
}

ReactDOM.render(<RouterConfig />, document.getElementById("root"))
