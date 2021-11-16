import React from "react"
import Footer from "./footer"
import Header from "./header"
import "./layout.css"

const Layout = ({ children, user }) => {
  return (
    <>
      <Header user={user} />
      <div className="layout">{children}</div>
      <Footer />
    </>
  )
}

export default Layout
