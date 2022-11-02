import axios from 'axios';
import React from 'react';
import ModalDelete from '../ModalDelete';
import $ from 'jquery'

 const TeamCard = ({time, admin}) => {

    const deleteTime = () => {
        axios.delete("/api/time", {
            params:
            {
                id: time.idEquipe
            }
        }).finally(() => window.location.reload(false))
    }
    
    return (

        <div className="col-4 mt-5">
            <div className="card text-center" style={{ backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>

                <div className="card-body">
                    <h5 className="card-title font-weight-bold">
                        {
                            time.nomeTime
                        }
                    </h5>
                    <p className="card-text">
                        {
                            `Organização: ${time.org.nomeOrg}`
                        }
                    </p>
                    <p className="card-text">
                        {
                            `Jogo: ${time.game.nome}`
                        }
                    </p>
                    {admin ? <a href={`/teams/update?${$.param(time)}`} className="btn btn-outline-primary d-block w-50 mx-auto mt-2">Editar</a> : null}
                    {admin ? <ModalDelete onDelete={deleteTime} /> : null}
                </div>
            </div>
        </div>
    );
}


export default TeamCard;