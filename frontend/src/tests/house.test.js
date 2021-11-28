import { mount } from "@cypress/react"
import { BrowserRouter as Router } from "react-router-dom"
import House from "../components/house"

const house = {
  location: "Location",
  user: "test@email.com",
  available: true,
}

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <House house={house} />
    </Router>
  )
}

describe("House", () => {
  it("location is correct", () => {
    setup()

    cy.get(".house-location").contains("Location")
  })

  it("clicking house redirects to housepage", () => {
    setup()

    cy.get(".house-link").click()
    cy.location("pathname").should("eq", "/house/Location")
  })
})
