import "./App.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import './components/deparam'
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
import ViewRecoveryChamp from "./components/Champ/ViewRecoveryChamp";
import ViewChampForm from "./components/Champ/ViewChampForm";
import UpdateChamp from "./components/Champ/UpdateChamp";
import ViewRecoveryUserTeam from "./components/UserTeam/ViewRecoveryUserTeam";
import ViewUserTeamForm from "./components/UserTeam/ViewUserTeamForm";
import UpdateUserTeam from "./components/UserTeam/UpdateUserTeam";


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
          // STATIC
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
          // Campeonatos
          {
            path: "/champ",
            element: (
              <div>
                <Header signed={signed} />
                <ViewRecoveryChamp admin={admin} />
              </div>
            )
          },
          {
            path: "/champ/new",
            element: (
              <div>
                <Header signed={signed} />
                <ViewChampForm />
              </div>
            )
          },
          {
            path: "/champ/update",
            element: (
              <div>
                <Header signed={signed} />
                <UpdateChamp />
              </div>
            )
          },
          // UserTeam
          {
            path: "/roster",
            element: (
              <div>
                <Header signed={signed} />
                <ViewRecoveryUserTeam />
              </div>
            )
          },
          {
            path: "/roster/new",
            element: (
              <div>
                <Header signed={signed} />
                <ViewUserTeamForm />
              </div>
            )
          },
          {
            path: "/roster/update",
            element: (
              <div>
                <Header signed={signed} />
                <UpdateUserTeam />
              </div>
            )
          },
          ///
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
