import React from "react";

class UnauthorizedContent extends React.Component {
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
          <h2 className="text-center">BANIDO</h2>
          <p className="text-center">
            Uma parede invisível não deixa você passar
          </p>
        </div>
        <div className="container">
          <div className="row">
            <div className="col d-flex justify-content-center">
              <img
                className="mx-auto"
                src="https://www.pngmart.com/files/22/Mr.-Mime-Pokemon-PNG.gif"
                width="400px"
              />
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UnauthorizedContent;
