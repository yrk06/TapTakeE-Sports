import React, { useState, useEffect } from "react";
import OrgCard from "./Orgcard";
import AddIcon from '@mui/icons-material/Add';
import { IconButton } from "@mui/material";
import axios from "axios";

const ViewRecoveryOrg = ({ admin }) => {
    const isAdmin = admin;

    const [orgs, setOrgs] = useState([]);

    useEffect(() => {
        axios
            .get('/api/org')
            .then(res => res.data)
            .then(setOrgs)
    }, [])


    return (
        <div className="container">
            <div className="row">

                <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%" }}>Organizações</h1>

                {/*<div className="input-group mt-5">
                        <input className="form-control" placeholder="Digite o jogo desejado"></input>
                        <div className="input-group-append"><button className="btn btn-primary">Pesquisar</button></div>
                    </div>
                </div>
                    </div>*/}

            </div>

            <div className="row justify-content-left">

                {
                    orgs.map((element, index) => <OrgCard key={index} org={element} admin={isAdmin} />)
                }

                <div className="col-4 d-flex justify-content-center align-items-center ">
                    {
                        isAdmin ? <IconButton href="/org/new" variant="contained" className="btn mt-5 rounded-circle" style={{ backgroundColor: "var(--taptake-dark-1)", width: "48px", height: "48px" }}> <AddIcon sx={{ color: "#FFFFFF" }} /></IconButton> : null
                    }
                </div>

            </div>
        </div >
    );
}

export default ViewRecoveryOrg;
