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
    fetchUser()
  }, [])

  const fetchHouses = () => {
    const requestOptions = {
      method: "GET", // or 'PUT'
      content: "application/json",
    }

    fetch("https://8080-white-coyote-7xo3ngjz.ws.gitpod.stud.ntnu.no/houses", requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw new Error(response.statusText)
        }
        return response.json()
      })
      .then((houseMap) => {
        const houses = []
        const locations = Object.keys(houseMap)
        for (const i in locations) {
          const location = locations[i]
          console.log(location)
          console.log(houseMap[location])
          const user = houseMap[location][0]
          const available = houseMap[location][1]
          houses.push({ location: location, user: user, available: available })
        }
        setHouses(houses)
      })
      .catch((error) => {
        console.error("Error:", error)
      })
  }

  const fetchUser = () => {
    const requestOptions = {
      method: "GET", // or 'PUT'
      content: "application/json",
    }

    fetch("https://8080-white-coyote-7xo3ngjz.ws.gitpod.stud.ntnu.no/getUser", requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw new Error(response.statusText)
        }
        return response.json()
      })
      .then((email) => {
        console.log(email)
        if (email == "null") {
          setUser("") 
          return
        }
        setUser(email)
      })
      .catch((error) => {
        console.error("Error:", error)
        setUser("")
      })
  }

  return (
    <React.StrictMode>
      <Router>
        <Layout user={user} fetchUser={fetchUser}>
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
