import React from "react";

class AuthHeaderLinks extends React.Component {
  render() {
    return (
      <ul
        className="navbar-nav ms-auto"
        style={{
          marginRight: "1rem"
        }}
      >
        <li className="nav-item">
          <a className="nav-link active" href="#">
            Elenco
          </a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="#">
            Próximas Partidas
          </a>
        </li>
        <li className="nav-item" />
      </ul>
    );
  }
}

export default AuthHeaderLinks;
