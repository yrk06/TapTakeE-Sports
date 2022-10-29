import React from 'react';

class GameCard extends React.Component {
    render() {
        return (

            <div className="col-4 mt-5">
                <div className="card text-center" style={{backgroundColor: "var(--taptake-dark-1)", borderRadius: "8px" }}>

                    <div className="card-body">
                        <h5 className="card-title font-weight-bold">
                            {
                                this.props.name
                            }
                        </h5>
                        <p className="card-text">
                            {
                                `${this.props.players} Jogadores`
                            }
                        </p>
                        {this.props.admin ? <a href="/error?error=404" className="btn btn-outline-primary d-block w-50 mx-auto mt-2">Editar</a> : null}
                        {this.props.admin ? <a href="/error?error=404" className="btn btn-danger d-block w-50 mx-auto mt-2">Apagar</a> : null}
                    </div>
                </div>
            </div>


        );
    }
}

export default GameCard;
