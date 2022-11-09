import axios from 'axios';
import React from 'react';
import ModalDelete from '../ModalDelete'
import $ from 'jquery'

const ChampCard = ({ champ, admin }) => {

    const deleteChamp = () => {
        axios.delete("/api/champ", {
            params:
            {
                id: champ.idCampeonato
            }
        }).finally(() => window.location.reload(false))
    }

    return (

        <div className="col-12 col-md-6 col-lg-4 mt-5">
            <div className="card text-center" style={{ backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>
                <div class="card-header">
                    {champ.game.nome}
                </div>
                <div className="card-body">
                    <h5 className="card-title font-weight-bold">
                        {
                            champ.nome
                        }
                    </h5>
                    <p className="card-text">
                        {
                            champ.localCampeonato
                        }
                    </p>
                    <p className="card-text">
                        {
                            `R$${champ.premiacao}`
                        }
                    </p>
                    {admin ? <a href={`/champ/update?${$.param(champ)}`} className="btn btn-outline-primary d-block w-50 mx-auto mt-2">Editar</a> : null}
                    {admin ? <ModalDelete onDelete={deleteChamp} /> : null}

                </div>
            </div>
        </div>


    );
}

export default ChampCard;
