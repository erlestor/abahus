import { mount } from "@cypress/react"
import Header from "../components/header"
import { BrowserRouter as Router } from "react-router-dom"

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <Header />
    </Router>
  )
}

describe("Header", () => {
  it("renders main logo", () => {
    setup()

    cy.get("h1").contains("Abahus")
  })
  it("renders sign in button", () => {
    setup()

    cy.get("a").contains("Sign in")
  })
})
