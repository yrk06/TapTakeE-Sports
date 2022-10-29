import React from "react";
import CastCard from "./CastCard";
import AddIcon from '@mui/icons-material/Add';
import { IconButton } from "@mui/material";

class Cast extends React.Component {
    render() {
        return (
            <div className="container">
                <div className="row">

                    <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%" }}>Elenco</h1>

                    <div className="input-group mt-5">
                        <input className="form-control" placeholder="Digite o jogador desejado"></input>
                        <div className="input-group-append"><button className="btn btn-primary">Pesquisar</button></div>
                    </div>
                    <div className="col-4 d-flex float-right ">
                        {
                            this.props.admin ? <IconButton variant="contained" className="btn mt-5 rounded-circle" style={{ backgroundColor: "var(--taptake-dark-1)", width: "48px", height: "48px" }}> <AddIcon sx={{ color: "#FFFFFF" }} /></IconButton> : null
                        }
                    </div>
                </div>

                <div className="row justify-content-left">
                    <CastCard name="League of Legends" players={0} points={0} admin={true} />
                    <CastCard name="Counter Strike" players={3} points={100} admin={true} />
                    <CastCard name="Valorant" players={4} points={80} admin={true} />
                </div>
            </div >
        );
    }
}

export default Cast;
