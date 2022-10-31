import React from 'react';
import ModalDelete from './ModalDelete';
class TeamCard extends React.Component {
    render() {
        return (

            <div className="col-4 mt-5">
                <div className="card text-center" style={{ backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>

                    <div className="card-body">
                        <h5 className="card-title font-weight-bold">
                            {
                                this.props.nameTeam
                            }
                        </h5>
                        <p className="card-text">
                            {
                                `Organização: ${this.props.nameOrg}`
                            }
                        </p>
                        <p className="card-text">
                            {
                                `Jogo: ${this.props.nameGame}`
                            }
                        </p>
                        {this.props.admin ? <button className="btn btn-primary d-block w-50 mx-auto mt-2">Editar</button> : null}
                        {this.props.admin ? <button className="btn btn-danger d-block w-50 mx-auto mt-2"> <ModalDelete /> </button> : null}
                    </div>
                </div>
            </div>


        );
    }
}

export default TeamCard;