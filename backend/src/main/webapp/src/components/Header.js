import React from "react";
import AnonHeaderLinks from "./AnonHeaderLinks";
import AuthHeaderLinks from "./AuthHeaderLinks";

class Header extends React.Component {
  render() {
    return (
      <div>
        <nav
          className="navbar navbar-dark navbar-expand-md fixed-top py-3"
          style={{
            background: "rgba(33,37,41,0.81)",
            backdropFilter: "blur(100px)"
          }}
        >
          <div className="container-fluid">
            <a className="navbar-brand d-flex align-items-center" href="#">
              <span
                className="bs-icon-sm bs-icon-rounded d-flex justify-content-center align-items-center me-2 bs-icon"
                style={{
                  background: "var(--primary)"
                }}
              >
                <i className="fa fa-soccer-ball-o text-dark" />
              </span>
              <span
                className="text-primary"
                style={{
                  fontWeight: "bold",
                  fontFamily: "Kanit, sans-serif",
                  marginLeft: "0.5rem"
                }}
              >
                TapTakeGames
              </span>
            </a>
            <button
              data-bs-toggle="collapse"
              className="navbar-toggler"
              data-bs-target="#navcol-1"
            >
              <span className="visually-hidden" />
              <span className="navbar-toggler-icon" />
            </button>
            <div
              className="collapse navbar-collapse text-end d-md-flex justify-content-md-end"
              id="navcol-1"
            >
              {
                this.props.signed ?
                  <AnonHeaderLinks /> :
                  <AuthHeaderLinks />
              }
              <a
                className="btn btn-primary ms-md-2"
                role="button"
                id="login-button"
                href={this.props.signed ? "/logout" : "/login"}
              >
                {this.props.signed ? "Sair" : "Entrar"}
              </a>
            </div>
          </div>
        </nav>
        <div
          style={{
            marginTop: "82px",
            marginRight: "2rem",
            marginLeft: "2rem"
          }}
        />
      </div>
    );
  }
}

export default Header;
