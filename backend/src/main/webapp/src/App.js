import "./App.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import Header from "./components/Header";
import HomePageContent from "./components/HomePageContent";
import { useEffect, useState } from "react";
import axios from "axios";
import LoginForm from "./components/LoginForm";
import About from "./components/About";
import HowItWorks from "./components/HowItWorks";
import ViewRecoveryTeam from "./components/ViewRecoveryTeam";
import ViewRecoveryGame from "./components/Game/ViewRecoveryGame";
import Cast from "./components/Cast";
import Lineup from "./components/Lineup";
import UpdateForm from "./components/UpdateForm";
import CreateForm from "./components/CreateForm";
import SignupForm from './components/SignupForm';
import ModalDelete from "./components/ModalDelete";
import NotFoundContent from "./components/NotFoundContent";
import ViewGameForm from "./components/Game/ViewGameForm";
import UpdateGame from "./components/Game/UpdateGame";
import ViewOrgsForm from "./components/ViewOrgsForm";

function App() {
  const [signed, setSigned] = useState(0);
  const [admin, setAdmin] = useState(false)

  useEffect(() => {
    const getSigned = () =>
      axios
        .get("/api/user/")
        .then((res) => {
          if (res.data.role === "Admin") {
            setAdmin(true)
          }
          setSigned(true)
        })
        .catch(() => setSigned(false));
    getSigned();
  }, []);
  return (
    <div>
      <Header signed={signed} />
      <RouterProvider router={
        createBrowserRouter([
          {
            path: "/",
            element: <div>

              <HomePageContent />
            </div>
          },
          {
            path: "/login",
            element: <LoginForm />
          },
          {
            path: "/cadastrar",
            element: <SignupForm />
          },
          {
            path: "/UpdateForm",
            element: (
              <div>
                <Header signed={signed} />
                <UpdateForm />
              </div>
            ),
          },
          {
            path: "/CreateForm",
            element: (
              <div>
                <Header signed={signed} />
                <CreateForm />
              </div>
            ),
          },
          {
            path: "/Cast",
            element: (
              <div>
                <Header signed={signed} />
                <Cast />
              </div>
            ),
          },
          {
            path: "/Lineup",
            element: (
              <div>
                <Header signed={signed} />
                <Lineup />
              </div>
            ),
          },
          {
            path: "/about",
            element: (
              <div>
                <Header signed={signed} />
                <About />
              </div>
            ),
          },
          {
            path: "/howItWorks",
            element: (
              <div>
                <Header signed={signed} />
                <HowItWorks />
              </div>
            ),
          },
          {
            path: "/Delete",
            element: (
              <div>
                <Header signed={signed} />
                <ModalDelete />
              </div>
            ),
          },
          // GAMES
          {
            path: "/games",
            element: (
              <div>
                <Header signed={signed} />
                <ViewRecoveryGame admin={admin} />
              </div>
            ),
          },
          {
            path: "/games/new",
            element: (
              <div>
                <Header signed={signed} />
                <ViewGameForm />
              </div>
            ),
          },
          {
            path: "/games/update",
            element: (
              <div>
                <Header signed={signed} />
                <UpdateGame />
              </div>
            ),
          },
          {
            path: "/teams",
            element: (
              <div>
                <Header signed={signed} />
                <ViewRecoveryTeam />
              </div>
            ),
          },
          {
            path: "/games/update",
            element: (
              <div>
                <Header signed={signed} />
                <ViewGameForm />
              </div>
            ),
          },
          {
            path: "ViewOrgsForm",
            element: (
              <div>
                <Header signed={signed} />
                <ViewOrgsForm />
              </div>
            ),
          },
          {
            path: "*",
            element: (
              <div>
                <Header signed={signed} />
                <NotFoundContent />
              </div>
            )
          }
        ])
      } />
    </div>
  );
}

export default App;
