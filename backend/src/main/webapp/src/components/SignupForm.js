import React from "react";
import $ from 'jquery'
import axios from "axios";

class SignupForm extends React.Component {
  send(e) {
    e.preventDefault()
    $("#confirmar_senha_validation").hide()
    const formData = $(e.target).serializeArray()
    const userDTO = {}
    if ($('input[name="senha"]').val() !== $('input[name="confirmar_senha"]').val()) {
      $("#confirmar_senha_validation").show()
      return
    } else {
    }
    for (let field of formData.filter((e) => e.name !== "confirmar_senha")) {
      userDTO[field.name] = field.value
    }

    axios.post("/api/user/", userDTO).then(() => window.location.href = "/")
  }
  render() {
    return (
      <section className="position-relative py-4 py-xl-5">
        <div className="container">
          <div className="row mb-5">
            <div className="col-md-8 col-xl-6 text-center mx-auto">
              <h2>Cadastre-se</h2>
              <p className="w-lg-50">
                Cadastre-se para jogar no TapTake E-Sports
              </p>
            </div>
          </div>
          <div className="row d-flex justify-content-center">
            <div className="col-md-6 col-xl-4">
              <div
                className="card mb-5"
                style={{
                  background: "var(--taptake-dark-1)"
                }}
              >
                <div className="card-body d-flex flex-column align-items-center">
                  <div className="bs-icon-xl bs-icon-circle bs-icon-primary text-primary bs-icon my-4"
                    style={{
                      background: "var(--gray-dark)"
                    }}
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="1em"
                      height="1em"
                      fill="currentColor"
                      viewBox="0 0 16 16"
                      className="bi bi-person"
                    >
                      <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
                    </svg>
                  </div>
                  <form className="text-center" method="post" onSubmit={this.send}>
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="email"
                        name="email"
                        placeholder="Email"
                        required
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="text"
                        name="nome"
                        placeholder="Nome"
                        required
                        minLength={1}
                        maxLength={255}
                        pattern="[a-zA-Z]*"
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="tel"
                        name="telefone"
                        placeholder="Telefone"
                        required
                        minLength={8}
                        pattern="^(\([0-9]{2}\)|[0-9]{2})\s?[0-9]{4}[0-9]?(-|\s)?[0-9]{4}$"
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="password"
                        name="senha"
                        placeholder="Senha"
                        minLength={6}
                        maxLength={32}
                        required
                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{6,}$"
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="password"
                        name="confirmar_senha"
                        placeholder="Repita a senha"
                        minLength={6}
                        maxLength={32}
                        required
                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{6,}$"
                      />
                      <p id="confirmar_senha_validation" className="text-danger" style={{ "display": "none" }}>As senhas devem ser iguais</p>
                    </div>
                    <div className="mb-3">
                      <button
                        className="btn btn-primary d-block w-100"
                        type="submit"
                      >
                        Cadastrar
                      </button>
                    </div>
                    <a className="text-muted mt-2" href="/login">Já possui cadastro?</a>
                    {/*<p className="text-muted">Forgot your password?</p>*/}
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    );
  }
}

export default SignupForm;
