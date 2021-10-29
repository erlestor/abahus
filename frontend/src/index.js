import React, { useState } from "react";
import ReactDOM from "react-dom";
import "./index.css";
import Landing from "./components/landing";
import Login from "./components/login";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

const Component = () => {
  const [user, setUser] = useState(null);

  return (
    <React.StrictMode>
      <Router>
        <Switch>
          <Route path="/" exact>
            <Landing />
          </Route>
          <Route path="/login" exact>
            <Login />
          </Route>
        </Switch>
      </Router>
    </React.StrictMode>
  );
};

ReactDOM.render(<Component />, document.getElementById("root"));
