import { mount } from "@cypress/react"
import { BrowserRouter as Router } from "react-router-dom"
import AddHouse from "../components/addHouse"

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <AddHouse />
    </Router>
  )
}

describe("Add House Page", () => {
  it("input works", () => {
    setup()

    cy.get("input").type("test").invoke("val").should("equal", "test")
  })
})
