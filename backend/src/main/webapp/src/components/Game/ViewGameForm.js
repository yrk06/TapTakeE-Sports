import React from "react";
import $ from 'jquery';
import axios from "axios";

const ViewGameForm = ({ gamename, typegame, gamequantity, gameId, update = false }) => {
    const createGame = (event) => {
        event.preventDefault()


        let gameDTO = {}
        for (let field of $("#newgame").serializeArray()) {
            gameDTO[field.name] = field.value
        }
        console.log(gameDTO)

        if (update) {
            axios
                .put("/api/game", gameDTO, { params: { id: gameId } })
                .then(() => {
                    window.location.href = "/games"
                })
        } else {
            axios
                .post("/api/game", gameDTO)
                .then(() => {
                    window.location.href = "/games"
                })
        }
    }
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-6 offset-md-3 justify-content-center">
                    <form id="newgame" onSubmit={createGame}>
                        <label htmlFor="gamename">Nome do jogo</label>
                        <input id="gamename" name="nome" type="text" class="form-control" placeholder="nome" defaultValue={gamename} />
                        <label htmlFor="tipoJogo">Tipo do Jogo</label>
                        <input id="gametype" name="tipoJogo" type="text" class="form-control" placeholder="tipo" defaultValue={typegame} />
                        <label htmlFor="gamequantity">Quantidade de Jogadores</label>
                        <input id="gamequantity" name="quantidadeJogadores" type="input" class="form-control" placeholder="Quantidade de Jogadores" defaultValue={gamequantity} />
                        <button className="btn btn-primary mt-3 mx-auto">{!update ? "Salvar" : "Atualizar"}</button>
                    </form>
                </div>
            </div>

        </div>
    )
}

export default ViewGameForm;