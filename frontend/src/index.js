import React, { useState, useEffect } from "react";
import ReactDOM from "react-dom";
import "./index.css";
import Landing from "./components/landing";
import Login from "./components/login";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import data from "./dummyData.json";

const RouterConfig = () => {
  const [user, setUser] = useState(null);
  const [houses, setHouses] = useState([]);

  useEffect(() => {
    setHouses(data.houses);
  }, []);

  return (
    <React.StrictMode>
      <Router>
        <Switch>
          <Route path="/" exact>
            <Landing houses={houses} />
          </Route>
          <Route path="/login" exact>
            <Login />
          </Route>
        </Switch>
      </Router>
    </React.StrictMode>
  );
};

ReactDOM.render(<RouterConfig />, document.getElementById("root"));
