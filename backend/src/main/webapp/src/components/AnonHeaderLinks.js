import React from "react";

class AnonHeaderLinks extends React.Component {
  render() {
    return (
      <ul
        className="navbar-nav ms-auto"
        style={{
          marginRight: "1rem"
        }}
      >
        <li className="nav-item">
          <a className="nav-link active" href="/">
            Jogos
          </a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="/">
            Campeonatos
          </a>
        </li>
        <li className="nav-item">
          <a className="nav-link" href="/">
            Organizações
          </a>
        </li>
      </ul>
    );
  }
}

export default AnonHeaderLinks;
