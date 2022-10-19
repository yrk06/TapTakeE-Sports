import React from "react";

class HomePageContent extends React.Component {
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
          <h2 className="text-center">Essa birosca ta em construção</h2>
        </div>
        <div
          style={{
            background:
              'url("https://freesvg.org/img/Sabathius_Warning_Stripe_Black_Yellow.png") repeat-x',
            height: "72px",
            backgroundSize: "contain"
          }}
        />
        <div className="d-flex justify-content-center">
          <img src="/assets/img/5Io0AoD.gif" />
        </div>
        <div
          style={{
            marginTop: "82px",
            marginRight: "2rem",
            marginLeft: "2rem"
          }}
        >
          <h2 className="text-center">
            Tente novamente depois que a gente acabar
          </h2>
        </div>
        <div
          style={{
            background:
              'url("https://freesvg.org/img/Sabathius_Warning_Stripe_Black_Yellow.png") repeat-x',
            height: "72px",
            backgroundSize: "contain"
          }}
        />
      </div>
    );
  }
}

export default HomePageContent;
