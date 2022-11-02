import axios from 'axios';
import React from 'react';
import ModalDelete from '../ModalDelete'
import $ from 'jquery'

const OrgCard = ({ orgs, admin }) => {

    const deleteOrg = () => {
        axios.delete("/api/orgs", {
            params:
            {
                id: orgs.idOrganizacao
            }
        }).finally(() => window.location.reload(false))
    }

    return (

        <div className="col-4 mt-5">
            <div className="card text-center" style={{ backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>

                <div className="card-body">
                    <h5 className="card-title font-weight-bold">
                        {
                            orgs.nome
                        }
                    </h5>
                    <p className="card-text">
                        {
                            `Região: ${orgs.localOrg}`
                        }
                    </p>
                    {admin ? <a href={`/org/update?${$.param(orgs)}`} className="btn btn-outline-primary d-block w-50 mx-auto mt-2">Editar</a> : null}
                    {admin ? <ModalDelete onDelete={deleteOrg} /> : null}

                </div>
            </div>
        </div>


    );
}

export default OrgCard;
