import React from "react";
import GameCard from "./GameCard";
import AddIcon from '@mui/icons-material/Add';
import { IconButton } from "@mui/material";

class ViewRecoveryGame extends React.Component {
    render() {
        return (
            <div className="container">
                <div className="row">

                    <h1 className="text-center font-weight-bold" style={{ marginTop: "5%", fontSize: "20", width: "100%" }}>Buscar Jogo</h1>

                    <div className="input-group mt-5">
                        <input className="form-control" placeholder="Digite o jogo desejado"></input>
                        <div className="input-group-append"><button className="btn btn-primary">Pesquisar</button></div>
                    </div>

                </div>

                <div className="row justify-content-left">
                    <GameCard name="Teste Jogo" players={12} admin={true} />
                    <GameCard name="Teste Jogo" players={2} admin={true} />
                    <GameCard name="Teste Jogo" players={3} admin={true} />
                    <GameCard name="Teste Jogo" players={4} admin={true} />

                    <div className="col-4 d-flex justify-content-center align-items-center ">
                        {
                            this.props.admin ? <IconButton variant="contained" className="btn mt-5 rounded-circle" style={{ backgroundColor: "var(--taptake-dark-1)", width: "48px", height: "48px" }}> <AddIcon  sx={{color: "#FFFFFF"}} /></IconButton> : null

                        }
                    </div>

                </div>
            </div >
        );
    }
}

export default ViewRecoveryGame;
