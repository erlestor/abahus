import React, { useState, useEffect } from "react"
import ReactDOM from "react-dom"
import "./index.css"
import Landing from "./components/landing"
import Login from "./components/login"
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import data from "./dummyData.json"
import HousePage from "./components/housePage"
import Layout from "./components/layout"
import AddHouse from "./components/addHouse"

const RouterConfig = () => {
  // const [user, setUser] = useState(null)
  const [user, setUser] = useState({
    email: "email@email.com",
    password: "passord",
  })

  const [houses, setHouses] = useState([])

  useEffect(() => {
    setHouses(data.houses)
  }, [])

  return (
    <React.StrictMode>
      <Router>
        <Layout user={user} setUser={setUser}>
          <Switch>
            <Route path="/" exact>
              <Landing houses={houses} user={user} />
            </Route>
            <Route path="/login" exact>
              <Login />
            </Route>
            <Route path="/add-house" exact>
              <AddHouse />
            </Route>
            <Route path="/house/:location">
              <HousePage houses={houses} user={user} />
            </Route>
          </Switch>
        </Layout>
      </Router>
    </React.StrictMode>
  )
}

ReactDOM.render(<RouterConfig />, document.getElementById("root"))
