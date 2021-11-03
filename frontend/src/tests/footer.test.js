import { mount } from "@cypress/react"
import Footer from "../components/footer"

describe("Footer", () => {
  it("renders contact us text", () => {
    mount(<Footer />)
    cy.get("h3").contains("Contact us at")
  })
  it("renders email", () => {
    mount(<Footer />)
    cy.get("a").contains("nobody@abahus.com")
  })
})
