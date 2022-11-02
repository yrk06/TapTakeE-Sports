import React from "react";
import $ from 'jquery';
import axios from "axios";

const ViewTeamForm = ({ teamId, teamName, update = false}) => {
    const createTeam = (event) => {
        event.preventDefault();

        let teamDTO = {}

        for(let field of $("newteam").serializeArray()){
            teamDTO[field.nomeTime] = field.value
        }
        
        console.log(teamDTO)

        if (update) {
            axios
                .put("/api/team", teamDTO, { params: { id: idEquipe } })
                .then(() => {
                    window.location.href = "/teams"
                })
        } else {
            axios
                .post("/api/team", teamDTO)
                .then(() => {
                    window.location.href = "/teams"
                })
        }
    }

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-6 offset-md-3 justify-content-center">
                    <form id="newgame" onSubmit={createTeam}>
                        <label htmlFor="nomeTime">Nome do time</label>
                        <input id="nomeTime" name="nome" type="text" class="form-control" placeholder="nome" defaultValue={nomeTime} />
                        <button className="btn btn-primary mt-3 mx-auto">{!update ? "Salvar" : "Atualizar"}</button>
                    </form>
                </div>
            </div>

        </div>
    )
}