import React from "react";

class InternalServerError extends React.Component {
  panic() {
    alert("SOS, REPITO, SOS!! ISSO NÃO É UM TESTE!!!");
  }

  render() {
    return (
      <div>
        <div
          className="d-flex justify-content-center flex-column align-items-center"
          style={{
            marginTop: "82px",
            marginRight: "2rem",
            marginLeft: "2rem",
          }}
        >
          <h2>Aperte o botão de pânico</h2>
          <button className="btn btn-primary" onClick={this.panic}>
            Pânico
          </button>
          <p></p>
          <h3>Algo deu muito errado</h3>
          <p
            className="text-center"
            style={{
              color: "#3e454d",
            }}
          ></p>
        </div>
        <div className="d-flex justify-content-center">
          <img
            src="https://media.tenor.com/L4rjYt7wOQoAAAAC/april-fools-joke.gif"
            alt="Jake the dog"
          />
        </div>
      </div>
    );
  }
}
export default InternalServerError;
