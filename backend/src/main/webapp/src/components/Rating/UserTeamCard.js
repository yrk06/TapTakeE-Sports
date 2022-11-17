import React from 'react';

const UserTeamCard = ({ team }) => {

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
                    <div>{team.user}</div>
                </div>
            </div>
        </div>


    );
}

export default UserTeamCard;
