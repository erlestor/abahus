import React, { useState, useEffect } from "react"
import ReactDOM from "react-dom"
import "./index.css"
import Landing from "./components/landing"
import Login from "./components/login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import HousePage from "./components/housePage"
import Layout from "./components/layout"
import AddHouse from "./components/addHouse"
import url from "./url"

// this is the root component
const RouterConfig = () => {
  const [user, setUser] = useState("")
  const [houses, setHouses] = useState([])

  // when component loads, load houses and logged in user from rest api into state
  useEffect(() => {
    console.log(url)
    fetchHouses()
    fetchUser()
  }, [])

  const fetchHouses = () => {
    const requestOptions = {
      method: "GET", // or 'PUT'
      content: "application/json",
    }

    fetch(`${url}/houses`, requestOptions)
      .then((response) => {
        console.log(`${url}/houses`)
        if (!response.ok) {
          throw new Error(response.statusText)
        }
        return response.json()
      })
      .then((houseMap) => {
        // rest returns a map with location as key and [location, availability] as value
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

    fetch(`${url}/getUser`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw new Error(response.statusText)
        }
        return response.json()
      })
      .then((email) => {
        console.log(email)
        if (email === "null") {
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
        {/* layers renders a header and footer around the page selected by the user */}
        <Layout user={user} fetchUser={fetchUser}>
          {/* switch and router selects the first page that matches with the url */}
          <Switch>
            <Route path="/" exact>
              {/* this is the main page where houses are shown */}
              <Landing houses={houses} user={user} />
            </Route>
            <Route path="/login" exact>
              <Login setUser={setUser} />
            </Route>
            <Route path="/add-house" exact>
              {/* where you get sent to when clicking add house in landing page */}
              <AddHouse fetchHouses={fetchHouses} />
            </Route>
            <Route path="/house/:location">
              {/* when you click a house on landing a page with the house loads */}
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
