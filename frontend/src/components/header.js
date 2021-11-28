import React from "react"
import { Link } from "react-router-dom"
import { ImHome } from "react-icons/im"
import "./header.css"
import url from "../url"

const Header = ({ user, fetchUser }) => {
  const signOut = () => {
    const requestOptions = {
      method: "POST",
      content: "application/json",
    }
    fetch(`${url}/logOut`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText)
        }
        return response.json()
      })
      .then((msg) => {
        console.log(msg)
        fetchUser()
      })
      .catch((error) => {
        console.error("Error:", error)
        fetchUser()
      })
  }

  return (
    <div className="header">
      {/* links to landing */}
      <Link to="/" className="flex space-x-4">
        <ImHome color="red" size="60" />
        <h1>Abahus</h1>
      </Link>

    {/* if user is logged in show username and signout btn, if not show sign in btn */}
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
