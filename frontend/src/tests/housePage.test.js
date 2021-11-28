import { mount } from "@cypress/react"
import { BrowserRouter as Router } from "react-router-dom"
import HousePage from "../components/housePage"

const houses = [
  {
    location: "Location",
    user: "test@email.com",
    available: true,
  },
]

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <HousePage houses={houses} user="test@email.com" />
    </Router>
  )
}

describe("Add House Page", () => {
  it("if no house is found 404 is shown", () => {
    setup()

    cy.get("h1").contains("404")
  })
})
