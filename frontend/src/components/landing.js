import React from "react"
import "./landing.css"
import House from "./house"
import Layout from "./layout"

function LandingPage({ houses }) {
  console.log({ houses })
  return (
    <Layout>
      <div className="page">
        <h2>Available houses</h2>
        <div className="houses">
          {houses.map((house) => (
            <>
              {house.available ? <House house={house} /> : <></>}
              <div className="divider" />
            </>
          ))}
        </div>
      </div>
    </Layout>
  )
}

export default LandingPage
