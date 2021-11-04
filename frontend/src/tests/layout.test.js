import { mount } from "@cypress/react"
import { BrowserRouter as Router } from "react-router-dom"
import Layout from "../components/layout"

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <Layout children={<h>Heisann</h>} />
    </Router>
  )
}

describe("Layout", () => {
  it("Content is below header", () => {
    setup()

    cy.get(".header").next().should("have.class", "layout")
  })

  it("Footer is below content", () => {
    setup()

    cy.get(".layout").next().should("have.class", "footerContainer")
  })

  it("Children are rendered", () => {
    setup()

    cy.get(".layout").find("h").contains("Heisann")
  })
})
