import React, { useState } from "react"
import "./login.css"

const login = () => {
  return (
    <div className="forms-container">
      <RegisterForm />
      <LoginForm />
    </div>
  )
}

const RegisterForm = () => {
  const [email, setEmail] = useState("")
  const [pass, setPass] = useState("")
  const [confirmPass, setConfirmPass] = useState("")

  return (
    <form className="registerForm">
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
      <button>Register</button>
    </form>
  )
}

const LoginForm = () => {
  const [email, setEmail] = useState("")
  const [pass, setPass] = useState("")

  return (
    <form className="loginForm">
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
      <button>Log in</button>
    </form>
  )
}

export default login
