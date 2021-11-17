import React from "react"
import "./landing.css"
import House from "./house"
import { Link } from "react-router-dom"

function LandingPage({ houses, user }) {
  console.log({ houses })
  return (
    <div className="page">
      <div className="houses-group">
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
      {user && (
        <div className="houses-group">
          <h2>Your houses</h2>
          <Link to="add-house" className="btn">
            Add new house
          </Link>
          <div className="houses">
            {houses.map((house) => (
              <>
                {house.user.email === user.email ? (
                  <>
                    <House house={house} />
                    <div className="divider" />
                  </>
                ) : (
                  <></>
                )}
              </>
            ))}
          </div>
        </div>
      )}
    </div>
  )
}

export default LandingPage
