import React from "react";
import $ from 'jquery';
import axios from "axios";

const ViewOrgsForm = ({ orgname, orgLocal, createDate, orgId, update = false }) => {
    const createorg = (event) => {
        event.preventDefault()


        let orgDTO = {}
        for (let field of $("#neworg").serializeArray()) {
            orgDTO[field.name] = field.value
        }
        console.log(orgDTO)

        if (update) {
            axios
                .put("/api/org", orgDTO, { params: { id: orgId } })
                .then(() => {
                    window.location.href = "/org"
                })
        } else {
            axios
                .post("/api/org", orgDTO)
                .then(() => {
                    window.location.href = "/org"
                })
        }
    }
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-6 offset-md-3 justify-content-center">
                    <form id="newgame" onSubmit={createorg}>
                        <label htmlFor="orgname">Nome da organização</label>
                        <input id="orgname" name="nome" type="text" class="form-control" placeholder="nome" defaultValue={orgname} />
                        <label htmlFor="localOrg">Nacionalidade da organização</label>
                        <input id="localorg" name="localOrg" type="text" class="form-control" placeholder="tipo" defaultValue={orgLocal} />
                        <label htmlFor="createorg">Data de criação da organização</label>
                        <input id="createorg" name="dataCriacao" type="date" class="form-control" placeholder="Data de criação" defaultValue={createDate} />
                        <button className="btn btn-primary mt-3 mx-auto">{!update ? "Salvar" : "Atualizar"}</button>
                    </form>
                </div>
            </div>

        </div>
    )
}

export default ViewOrgsForm;