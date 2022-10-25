import React from "react";
import logoTapTake from "../assets/logoTapTake.png";

class About extends React.Component {
  render() {
    return (
      <div className="container d-flex flex-column" style={{ height: " calc(100vh - 82px)" }}>
        <div className="d-flex justify-content-center">
          <img src={logoTapTake} alt="history of TapTake"></img>
        </div>
        <div className="row flex-fill align-items-center">
          <h2 className="text-justify">
            &nbsp; &nbsp; O 'TapTake' teve a sua origem na matéria de experiência criativa,
            onde foi desenvolvido um aplicativo com o objetivo de poupar o tempo
            de espera em filas de restaurantes. Após terminar a matéria, tivemos
            o o JIP (Jogos Internos da PUC) e por um equívoco do nosso capitão,
            o nome do nosso time também foi 'TapTake' e para dar continuidade na
            história, decidimos fazer uma plataforma interativa focada na área
            dos games. Inspirado no 'Cartola' onde é possível montar uma
            escalação com todos os jogadores dos times da serie A do
            Brasileirão, no 'TapTake' você pode montar uma escalação com com
            todos os jogadores de um determinado jogo, como Valorant e League of
            Legends.
          </h2>
        </div>
      </div>
    );
  }
}

export default About;
