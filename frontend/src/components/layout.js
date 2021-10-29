import React from "react";
import Footer from "./footer";
import Header from "./header";
import "./layout.css";

const Layout = ({ children }) => {
  return (
    <>
      <Header />
      <div className="layout">{children}</div>
      <Footer />
    </>
  );
};

export default Layout;
