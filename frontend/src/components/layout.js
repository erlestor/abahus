import React from "react"
import Footer from "./footer"
import Header from "./header"
import "./layout.css"

const Layout = ({ children, user, setUser }) => {
  return (
    <>
      <Header user={user} setUser={setUser} />
      <div className="layout">{children}</div>
      <Footer />
    </>
  )
}

export default Layout
