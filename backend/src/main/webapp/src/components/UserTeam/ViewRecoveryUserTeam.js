import React, { useState, useEffect } from "react";
import UserTeamCard from "./UserTeamCard";
import AddIcon from '@mui/icons-material/Add';
import { IconButton } from "@mui/material";
import axios from "axios";

const ViewRecoveryUserTeam = () => {

    const [teams, setTeams] = useState([]);

    useEffect(() => {
        axios
            .get('/api/userteam/owned')
            .then(res => res.data)
            .then(async userTeamDROs => {
                let games = []
                userTeamDROs.forEach(element => {
                    games.push(axios.get(`/api/game/id?id=${element.game}`))
                });
                games = await Promise.all(games)
                for (let i = 0; i < userTeamDROs.length; i++) {
                    userTeamDROs[i].game = games[i].data
                }
                return userTeamDROs
            }).then(setTeams)
    }, [])

    console.log(teams)

    return (
        <div className="container">
            <div className="row">

                <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%" }}>
                    Suas Equipes
                </h1>

            </div>

            <div className="row justify-content-left">

                {
                    teams.map((el, idx) => <UserTeamCard team={el} key={idx} />)
                }

                <div className="col-12 col-md-6 col-lg-4 mt-5 mb-5 d-flex justify-content-center align-items-center ">
                    <IconButton href="/roster/new" variant="contained" className="btn mt-5 rounded-circle" style={{ backgroundColor: "var(--taptake-dark-1)", width: "48px", height: "48px" }}> <AddIcon sx={{ color: "#FFFFFF" }} /></IconButton>
                </div>

            </div>
        </div>
    );
}

export default ViewRecoveryUserTeam;
