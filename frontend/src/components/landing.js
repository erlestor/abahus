import React from "react"
import "./landing.css"
import House from "./house"
import { Link } from "react-router-dom"

function LandingPage({ houses, user }) {
  return (
    <div className="page">
      <div className="houses-group">
        <h2>Available houses</h2>
        <div className="houses">
          {/* show houses which are available and not owned by the user */}
          {houses.map(
            (house) =>
              house.user !== user &&
              house.available && (
                <>
                  <House house={house} />
                  <div className="divider" />
                </>
              )
          )}
        </div>
      </div>
      {user && (
        <div className="houses-group">
          <h2>Your houses</h2>
          <Link to="add-house" className="btn">
            Add new house
          </Link>
          <div className="houses">
            {/* show houses which the user owns */}
            {houses.map((house) => (
              <>
                {house.user === user ? (
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
