import axios from 'axios';
import React from 'react';
import ModalDelete from '../ModalDelete'
import $ from 'jquery'

const GameCard = ({ game, admin }) => {

    const deleteGame = () => {
        axios.delete("/api/game", {
            params:
            {
                id: game.idJogo
            }
        }).finally(() => window.location.reload(false))
    }

    return (

        <div className="col-4 mt-5">
            <div className="card text-center" style={{ backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>

                <div className="card-body">
                    <h5 className="card-title font-weight-bold">
                        {
                            game.nome
                        }
                    </h5>
                    <p className="card-text">
                        {
                            `${game.quantidadeJogadores} Jogadores`
                        }
                    </p>
                    {admin ? <a href={`/games/update?${$.param(game)}`} className="btn btn-outline-primary d-block w-50 mx-auto mt-2">Editar</a> : null}
                    {admin ? <ModalDelete onDelete={deleteGame} /> : null}

                </div>
            </div>
        </div>


    );
}

export default GameCard;
