import React from "react";

class CreateForm extends React.Component {
    render() {
        return (
            <div className="container d-flex flex-column" style={{ height: " calc(100vh - 82px)" }}>
                <div className="row mt-5 col-xs-4">
                    <input type="text" placeholder="Enter something" />
                </div>

                <div className="row mt-5">
                    <input type="text" placeholder="Enter something" />
                </div>

                <div className="row mt-5">
                    <input type="text" placeholder="Enter something" />
                </div>


                <div className="row mt-5 w-100">
                    <textarea placeholder="Enter something" />
                </div>

            </div>
        );
    }
}

export default CreateForm;
