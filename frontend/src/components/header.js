import React from "react"
import { Link } from "react-router-dom"
import { ImHome } from "react-icons/im"
import "./header.css"

const Header = ({ user, setUser }) => {
  return (
    <div className="header">
      <Link to="/" className="flex space-x-4">
        <ImHome color="red" size="60" />
        <h1>Abahus</h1>
      </Link>

      {user ? (
        <Link
          onClick={() => setUser(null)}
          to="/login"
          className="signInButton"
        >
          Sign out
        </Link>
      ) : (
        <Link to="/login" className="signInButton">
          Sign in
        </Link>
      )}
    </div>
  )
}

export default Header
