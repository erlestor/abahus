import React from "react"
import { Link } from "react-router-dom"
import { ImHome } from "react-icons/im"
import { FaUserCircle } from "react-icons/fa"
import "./header.css"

const Header = ({ user }) => {
  return (
    <div className="header">
      <Link to="/" className="flex space-x-4">
        <ImHome color="red" size="60" />
        <h1>Abahus</h1>
      </Link>

      {user ? (
        <div className="user">
          <FaUserCircle size="50" />
          <h2>{user.email}</h2>
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
