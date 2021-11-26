import React from "react"
import { Link } from "react-router-dom"
import { ImHome } from "react-icons/im"
import "./header.css"

const Header = ({ user, setUser }) => {
  const signOut = () => {
    setUser("")
    localStorage.setItem("user", "")
  }
  return (
    <div className="header">
      <Link to="/" className="flex space-x-4">
        <ImHome color="red" size="60" />
        <h1>Abahus</h1>
      </Link>

      {user ? (
        <div className="logged-in">
          <p>{user}</p>
          <Link onClick={signOut} to="/login" className="signInButton">
            Sign out
          </Link>
        </div>
      ) : (
        <Link to="/login" className="signInButton">
          Sign in
        </Link>
      )}
    </div>
  )
}

export default Header
