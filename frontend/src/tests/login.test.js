import { mount } from "@cypress/react"
import { BrowserRouter as Router } from "react-router-dom"
import LogIn from "../components/login"

const setup = () => {
  mount(
    // Man må mounte <Router> for å unngå "You should not use <Link> outside a <Router>" error fra cypress
    <Router>
      <LogIn />
    </Router>
  )
}

const checkValidTextInput = (htmlElement, textInput) => {
  cy.get(htmlElement).type(textInput).invoke("val").should("equal", textInput)
}

describe("Login", () => {
  it("register form input is valid", () => {
    setup()

    cy.get(".registerForm").within(() => {
      checkValidTextInput("input[placeholder='email']", "1@2.com")
      checkValidTextInput("input[placeholder='password']", "123")
      checkValidTextInput("input[placeholder='confirm password']", "123")
    })
  })
  it("login form input is valid", () => {
    setup()

    cy.get(".loginForm").within(() => {
      checkValidTextInput("input[placeholder='email']", "1@2.com")
      checkValidTextInput("input[placeholder='password']", "123")
    })
  })
})
