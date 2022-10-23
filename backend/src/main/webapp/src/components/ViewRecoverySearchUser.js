import React from "react";

class ViewRecoverySearchUser extends React.Component {
    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="container d-flex flex-column col-md-8" style={{ height: " calc(100vh - 82px)" }}>
                        <div className="d-flex justify-content-center w-100">
                            <input style={{ width: "100%", marginTop: "8%" }} placeholder="Digite o jogo desejado"></input>
                        </div>
                        <div style={{ marginLeft: "89%", marginTop: "3%" }}>
                            <button className="btn btn-primary btn-sm" type="submit">Pesquisar</button>
                        </div>
                        <div className="row flex-fill align-items-center"></div>
                    </div>
                </div>
            </div >
        );
    }
}

export default ViewRecoverySearchUser;
