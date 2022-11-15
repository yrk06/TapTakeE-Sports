import axios from 'axios';
import React from 'react';
import ModalDelete from '../ModalDelete'
import $ from 'jquery'

const UserTeamCard = ({ team }) => {

    const deleteTeam = () => {
        axios.delete("/api/userteam", {
            params:
            {
                id: team.idEquipeUsuario
            }
        }).finally(() => window.location.reload(false))
    }

    return (

        <div className="col-12 col-md-6 col-lg-4 mt-5">
            <div className="card text-center" style={{ backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>
                <div class="card-header">
                    {team.game.nome}
                </div>
                <div className="card-body">
                    <h5 className="card-title font-weight-bold">
                        {
                            `${team.points} ${team.points === 1 ? "ponto" : "pontos"} `
                        }
                    </h5>
                    <ul>
                        {team.players.map(el => <li>{el.nome}</li>)}
                    </ul>
                    <a href={`/roster/update?${$.param(team)}`} className="btn btn-outline-primary d-block w-50 mx-auto mt-2">Editar</a>
                    <ModalDelete onDelete={deleteTeam} />

                </div>
            </div>
        </div>


    );
}

export default UserTeamCard;
