import React from "react";

class LoginForm extends React.Component {
  render() {
    return (
      <section className="position-relative py-4 py-xl-5">
        <div className="container">
          <div className="row mb-5">
            <div className="col-md-8 col-xl-6 text-center mx-auto">
              <h2>Seja bem vindo</h2>
              <p className="w-lg-50">Faça login para começar a jogar</p>
            </div>
          </div>
          <div className="row d-flex justify-content-center">
            <div className="col-md-6 col-xl-4">
              <div
                className="card mb-5"
                style={{
                  borderStyle: "none",
                  borderRadius: "16px",
                  background: "rgba(33,37,41,0.81)",
                }}
              >
                <div className="card-body d-flex flex-column align-items-center">
                  <div
                    className="bs-icon-xl bs-icon-circle bs-icon-primary text-primary bs-icon my-4"
                    style={{
                      background: "var(--gray-dark)",
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
                  <form className="text-center" method="post" action="/login">
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="email"
                        name="username"
                        placeholder="Email"
                      />
                    </div>
                    <div className="mb-3">
                      <input
                        className="form-control"
                        type="password"
                        name="password"
                        placeholder="Password"
                      />
                    </div>
                    <div className="mb-3">
                      <button
                        className="btn btn-primary d-block w-100"
                        type="submit"
                        style={{
                          borderColor: "var(--bs-black)",
                        }}
                      >
                        Login
                      </button>
                    </div>
                    <a className="text-muted mt-2" href="/cadastrar">Não possui cadastro?</a>
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

export default LoginForm;
