import React, { useState } from "react"
import "./login.css"
import { useHistory } from "react-router-dom"

const login = ({ setUser }) => {
  return (
    <div className="forms-container">
      <RegisterForm setUser={setUser} />
      <LoginForm setUser={setUser} />
    </div>
  )
}

const RegisterForm = ({ setUser }) => {
  const history = useHistory()

  const [email, setEmail] = useState("")
  const [pass, setPass] = useState("")
  const [confirmPass, setConfirmPass] = useState("")
  const [error, setError] = useState("")

  const register = () => {
    const requestOptions = {
      method: "POST",
      content: "application/json",
      body: JSON.stringify({
        email: email,
        password: pass,
        confirmPassword: confirmPass,
      }),
    }
    fetch(`http://localhost:8080/registerUser`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((user) => {
        console.log(user)
        setError("")
        setUser(user)
        setEmail("")
        setPass("")
        setConfirmPass("")
        // store the user in localStorage
        localStorage.setItem("user", user)
        // redirect to landing
        history.push("/")
      })
      .catch((error) => {
        console.error("Error:", error)
        setError("invalid email/password")
      })
  }

  return (
    <div className="registerForm">
      <h1>Register</h1>
      <input
        type="text"
        placeholder="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="password"
        value={pass}
        onChange={(e) => setPass(e.target.value)}
      />
      <input
        type="password"
        placeholder="confirm password"
        value={confirmPass}
        onChange={(e) => setConfirmPass(e.target.value)}
      />
      <button onClick={register}>Register</button>
      <p>{error}</p>
    </div>
  )
}

const LoginForm = ({ setUser }) => {
  const history = useHistory()
  const [email, setEmail] = useState("")
  const [pass, setPass] = useState("")
  const [error, setError] = useState("")

  const logIn = () => {
    const requestOptions = {
      method: "POST",
      content: "application/json",
      body: JSON.stringify({
        email: email,
        password: pass,
      }),
    }
    fetch(`http://localhost:8080/logIn`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((user) => {
        console.log(user)
        setError("")
        setUser(user)
        setEmail("")
        setPass("")
        // store the user in localStorage
        localStorage.setItem("user", user)
        // redirect to landing
        history.push("/")
      })
      .catch((error) => {
        console.error("Error:", error)
        setError("invalid email/password")
      })
  }

  return (
    <div className="loginForm">
      <h1>Login</h1>
      <input
        type="text"
        placeholder="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="password"
        value={pass}
        onChange={(e) => setPass(e.target.value)}
      />
      <button onClick={logIn}>Log in</button>
      <p>{error}</p>
    </div>
  )
}

export default login
