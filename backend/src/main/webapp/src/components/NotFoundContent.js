import React from "react";

class NotFoundContent extends React.Component {
  render() {
    return (
      <div>
        <div
          style={{
            marginTop: "82px",
            marginRight: "2rem",
            marginLeft: "2rem"
          }}
        >
          <h2 className="text-center">Essa página ainda não existe</h2>
          <p
            className="text-center"
            style={{
              color: "#3e454d"
            }}
          >
            Ou existe e a gente não contou pra você
          </p>
        </div>
        <div className="d-flex justify-content-center">
          <img src="https://i.kym-cdn.com/photos/images/original/001/043/243/419.gif" />
        </div>
      </div>
    );
  }
}

export default NotFoundContent;
