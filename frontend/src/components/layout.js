import React from "react"
import Footer from "./footer"
import Header from "./header"
import "./layout.css"

const Layout = ({ children, user, fetchUser }) => {
  return (
    <>
      <Header user={user} fetchUser={fetchUser} />
      <div className="layout">{children}</div>
      <Footer />
    </>
  )
}

export default Layout
