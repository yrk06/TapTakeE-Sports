import React from "react";
import GameCard from "./GameCard";

class ViewRecoveryGame extends React.Component {
    render() {
        return (
            <div className="container">
                <div className="row">

                    <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%"}}>Buscar Jogo</h1>

                    <div className="input-group mt-5">
                        <input className="form-control" placeholder="Digite o jogo desejado"></input>
                        <div className="input-group-append"><button className="btn btn-primary">Pesquisar</button></div>
                    </div>

                </div>

                <div className="row justify-content-center">
                    <GameCard name="Teste Jogo" players={12} admin = {true}/>
                    <GameCard name="Teste Jogo" players={2} admin = {false}/>
                    <GameCard name="Teste Jogo" players={3} admin = {false}/>
                    <GameCard name="Teste Jogo" players={4} admin = {false}/>
                </div>
            </div >
        );
    }
}

export default ViewRecoveryGame;
