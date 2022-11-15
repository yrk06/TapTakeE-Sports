import React, { useEffect, useState } from "react";
import $ from 'jquery';
import axios from "axios";

const ViewUserTeamForm = ({ userTeam, update = false }) => {

    const create = (event) => {
        event.preventDefault()


        let DTO = {}
        DTO.players = []
        for (let field of $("#new").serializeArray()) {
            if (field.name.startsWith("player")) {
                if (field.value !== "null") {
                    DTO.players.push(field.value)
                }
            } else {
                DTO[field.name] = field.value
            }

        }

        console.log(DTO)

        if (update) {
            axios
                .put("/api/userteam", DTO, { params: { id: userTeam.idEquipeUsuario } })
                .then(() => {
                    window.location.href = "/roster"
                })
        } else {
            axios
                .post("/api/userteam", DTO)
                .then(() => {
                    window.location.href = "/roster"
                })
        }
    }

    const [games, setGames] = useState([]);
    const [notAllowedGames, setNotAllowedGames] = useState([])

    const [selectedGame, setSelectedGame] = useState({ idjogo: "nullid", quantidadeJogadores: 0 })

    useEffect(() => {

        axios
            .get('/api/game')
            .then(res => res.data)
            .then(setGames)
        if (update) {
            setSelectedGame(
                { ...userTeam.game, quantidadeJogadores: parseInt(userTeam.game.quantidadeJogadores) }
            )
            return
        }
        axios
            .get('/api/userteam/owned')
            .then(res => res.data)
            .then(teams => teams.map(el => el.game))
            .then(setNotAllowedGames)
    }, [update, userTeam])

    const [players, setPlayers] = useState([])
    const [selectedPlayers, setSelectedPlayers] = useState([])

    useEffect(
        () => {
            axios
                .get(`/api/player/game?gameId=${selectedGame.idJogo}`)
                .then(res => res.data)
                .then(setPlayers)
            if (!update) {
                setSelectedPlayers([...Array(selectedGame.quantidadeJogadores)].map(() => null))
            } else {
                const oldPlayers = [...userTeam.players.map(el => el.idJogador)]
                while (oldPlayers.length < selectedGame.quantidadeJogadores) {
                    oldPlayers.push(null)
                }
                setSelectedPlayers(oldPlayers)
            }
        },
        [selectedGame, update, userTeam])

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-6 offset-md-3 justify-content-center">
                    <form id="new" onSubmit={create}>
                        <label htmlFor="idJogo">Jogo</label>
                        <select className="custom-select form-control" name="idJogo" value={selectedGame.idJogo} onChange={(e) => setSelectedGame(games.filter(el => el.idJogo === e.target.value)[0])}>
                            <option value="nullid">Selecionar o jogo</option>
                            {games.filter(el => !notAllowedGames.includes(el.idJogo)).map((game, index) => <option key={index} value={game.idJogo}>{game.nome}</option>)}
                        </select>
                        <label htmlFor="">{selectedGame.quantidadeJogadores} Jogadores</label>
                        {

                            [...Array(selectedGame.quantidadeJogadores)].map((el, idx) => (
                                <select className="custom-select form-control mb-2" name={`player-${idx}`} key={idx}
                                    value={selectedPlayers[idx]}
                                    onChange={(e) => setSelectedPlayers(selectedPlayers.map((el, spidx) => idx === spidx ? e.target.value : el))}
                                >
                                    <option value="null">Escolha um Jogador</option>
                                    {players.filter((el) => !selectedPlayers.includes(el.idJogador) || selectedPlayers[idx] === el.idJogador).map((el, idx) => <option key={idx} value={el.idJogador}>{el.nome}</option>)}
                                </select>))
                        }
                        <button className="btn btn-primary mt-3 mx-auto">{!update ? "Salvar" : "Atualizar"}</button>
                    </form>
                </div>
            </div>

        </div>
    )
}

export default ViewUserTeamForm;