import React from "react"
import Footer from "./footer"
import Header from "./header"
import "./layout.css"

// places its children between a header and a footer. children consists of a react component represtning a page
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
