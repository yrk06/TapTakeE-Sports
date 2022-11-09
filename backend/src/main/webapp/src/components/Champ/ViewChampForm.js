import React, { useEffect, useState } from "react";
import $ from 'jquery';
import axios from "axios";

const ViewChampForm = ({ champ, update = false }) => {

    const create = (event) => {
        event.preventDefault()


        let DTO = {}
        DTO.idEquipes = []
        for (let field of $("#new").serializeArray()) {
            if (field.name.startsWith("team-id")) {
                DTO.idEquipes.push(field.value)
            } else {
                DTO[field.name] = field.value
            }

        }

        console.log(DTO)

        if (update) {
            axios
                .put("/api/champ", DTO, { params: { id: champ.idCampeonato } })
                .then(() => {
                    window.location.href = "/champ"
                })
        } else {
            axios
                .post("/api/champ", DTO)
                .then(() => {
                    window.location.href = "/champ"
                })
        }
    }

    const [games, setGames] = useState([]);

    useEffect(() => {
        axios
            .get('/api/game')
            .then(res => res.data)
            .then(setGames)
    }, [])

    const [selectedGame, setSelectedGame] = useState("nullid")

    useEffect(() => {
        setSelectedGame(champ ? champ.idGame : "nullid")
    }, [champ, games])

    const [equipes, setEquipes] = useState([]);

    useEffect(() => {

        axios
            .get('/api/team')
            .then(res => res.data)
            .then(eq => eq.filter(el => el.idJogo === selectedGame))
            .then(setEquipes)

    }, [selectedGame])

    const [selectedTeams, setSelectedTeams] = useState([])

    useEffect(() => {
        const idEquipes = equipes.map(el => el.id)
        if (champ !== undefined && champ.idEquipes !== undefined) {
            console.log(equipes)
            console.log(idEquipes)
            console.log(champ.idEquipes)
            setSelectedTeams(idEquipes.map((el) => champ.idEquipes.includes(el)))
        } else {
            setSelectedTeams(Array(equipes.length).map(() => false))
        }

    }, [equipes, champ])

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-6 offset-md-3 justify-content-center">
                    <form id="new" onSubmit={create}>
                        <label htmlFor="gamename">Nome do Campeonato</label>
                        <input id="gamename" name="nome" type="text" class="form-control" placeholder="Nome" defaultValue={champ ? champ.nome : ""} />
                        <label htmlFor="tipoJogo">Local do Campeonato</label>
                        <input id="gametype" name="localCampeonato" type="text" class="form-control" placeholder="Local" defaultValue={champ ? champ.localCampeonato : ""} />
                        <label htmlFor="idJogo">Jogo</label>
                        <select className="custom-select form-control" name="idJogo" value={selectedGame} onChange={(e) => setSelectedGame(e.target.value)}>
                            <option value="nullid">Selecionar o jogo</option>
                            {games.map((game, index) => <option key={index} value={game.idJogo}>{game.nome}</option>)}
                        </select>
                        <label htmlFor="gamequantity">Premiação</label>
                        <input id="gamequantity" name="premiacao" type="number" class="form-control" placeholder="Premiação" defaultValue={champ ? champ.premiacao : ""} />
                        <label htmlFor="">Equipes</label>
                        <div className="overflow-auto" style={{ height: "200px", backgroundColor: "var(--taptake-dark-1)", borderRadius: "10px", padding: "10px" }}>
                            <div className="row">
                                {
                                    equipes.map((element, equipeIdx) => {
                                        return (
                                            <div className="col-md-6 col-lg-4">
                                                <div className="form-check">
                                                    <input class="form-check-input" type="checkbox" checked={selectedTeams[equipeIdx]} onChange={(e) => {
                                                        console.log(`Alterando ${e.target.checked}`)
                                                        setSelectedTeams(selectedTeams.map((el, idx) => idx === equipeIdx ? e.target.checked : el))
                                                    }} name={`team-id-${element.id}`} value={element.id} id="flexCheckDefault" />
                                                    <label class="form-check-label">
                                                        {element.nomeTime}
                                                    </label>
                                                </div>
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        </div>
                        <button className="btn btn-primary mt-3 mx-auto">{!update ? "Salvar" : "Atualizar"}</button>
                    </form>
                </div>
            </div>

        </div>
    )
}

export default ViewChampForm;