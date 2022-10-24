import React from "react";

class HowItWorks extends React.Component {
  render() {
    return (
      <div className="container text-center" style={{ height: " calc(100vh - 82px)" }}>
        <div>
          <h1>Como se divertir com o TapTake?</h1>
          <li>TapTake é um Fantasy Game onde é possível escalar jogadores de diferentes times de Esports</li>
          <h2 style={{ marginTop: "10%" }}>Como usar o programa?</h2>
        </div>
        <div className="row text-justify" style={{ marginLeft: "32%", marginTop: "3%", marginRight: "auto" }}>
          <ol>
            <li>Você deve criar uma conta no TapTake</li>
            <li>Após criar a conta, já será possível visualizar os jogos disponíveis</li>
            <li>Com o jogo selecionado, basta escalar os jogadores disponíveis</li>
            <li>E de acordo com o desempenho dos jogadores escalados, você vai pontuando</li>
            <li>Divirta-se</li>
          </ol>
        </div>
      </div>
    );
  }
}

export default HowItWorks;
