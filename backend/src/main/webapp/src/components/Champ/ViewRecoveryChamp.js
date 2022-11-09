import React, { useState, useEffect } from "react";
import ChampCard from "./ChampCard";
import AddIcon from '@mui/icons-material/Add';
import { IconButton } from "@mui/material";
import axios from "axios";

const ViewRecoveryChamp = ({ admin }) => {
    const isAdmin = admin;

    const [champs, setChamps] = useState([]);

    useEffect(() => {
        axios
            .get('/api/champ')
            .then(res => res.data)
            .then(async champDROs => {
                let games = []
                champDROs.forEach(element => {
                    games.push(axios.get(`/api/game/id?id=${element.idGame}`))
                });
                games = await Promise.all(games)
                for (let i = 0; i < champDROs.length; i++) {
                    champDROs[i].game = games[i].data
                }
                return champDROs
            }).then(setChamps)
    }, [])

    console.log(champs)

    return (
        <div className="container">
            <div className="row">

                <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%" }}>Campeonatos</h1>

            </div>

            <div className="row justify-content-left">

                {
                    champs.map((element, index) => <ChampCard key={index} champ={element} admin={isAdmin} />)
                }

                <div className="col-12 col-md-6 col-lg-4 mt-5 mb-5 d-flex justify-content-center align-items-center ">
                    {
                        isAdmin ? <IconButton href="/champ/new" variant="contained" className="btn mt-5 rounded-circle" style={{ backgroundColor: "var(--taptake-dark-1)", width: "48px", height: "48px" }}> <AddIcon sx={{ color: "#FFFFFF" }} /></IconButton> : null

                    }
                </div>

            </div>
        </div>
    );
}

export default ViewRecoveryChamp;
