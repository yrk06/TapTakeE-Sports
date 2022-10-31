import React from "react";

class CreateForm extends React.Component {
    render() {
        return (
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroup-sizing-default">Nome</span>
                </div>
                <textarea class="form-control" aria-label="With textarea"></textarea>
            </div>
        );
    }
}

export default CreateForm;
