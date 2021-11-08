import { mount } from "@cypress/react"
import { BrowserRouter as Router } from "react-router-dom"
import LandingPage from "../components/landing"
import data from "../dummyData.json"

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <LandingPage houses={data.houses} />
    </Router>
  )
}

describe("Landing", () => {
  it("header is rendered", () => {
    setup()

    cy.get("h2").contains("Available houses")
  })

  it("all houses are rendered", () => {
    setup()

    cy.get(".house").should("have.length", data.houses.length)
  })

  it("houses have correct locations", () => {
    setup()

    for (let i = 0; i < data.houses.length; i++) {
      cy.get(".house").find(".house-location").contains(data.houses[i].location)
    }
  })
})
