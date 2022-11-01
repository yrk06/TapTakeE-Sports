import React, { useState, useEffect } from "react";
import GameCard from "./GameCard";
import AddIcon from '@mui/icons-material/Add';
import { IconButton } from "@mui/material";
import axios from "axios";

const ViewRecoveryGame = ({ admin }) => {
    const isAdmin = admin;

    const [games, setGames] = useState([]);

    useEffect(() => {
        axios
            .get('/api/game')
            .then(res => res.data)
            .then(setGames)
    }, [])


    return (
        <div className="container">
            <div className="row">

                <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%" }}>Jogos</h1>

                {/*<div className="input-group mt-5">
                        <input className="form-control" placeholder="Digite o jogo desejado"></input>
                        <div className="input-group-append"><button className="btn btn-primary">Pesquisar</button></div>
                    </div>
                </div>
                    </div>*/}

            </div>

            <div className="row justify-content-left">

                {
                    games.map((element, index) => <GameCard key={index} game={element} admin={isAdmin} />)
                }

                <div className="col-4 d-flex justify-content-center align-items-center ">
                    {
                        isAdmin ? <IconButton href="/games/new" variant="contained" className="btn mt-5 rounded-circle" style={{ backgroundColor: "var(--taptake-dark-1)", width: "48px", height: "48px" }}> <AddIcon sx={{ color: "#FFFFFF" }} /></IconButton> : null

                    }
                </div>

            </div>
        </div >
    );
}

export default ViewRecoveryGame;
