import "./App.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import Header from "./components/Header";
import HomePageContent from "./components/HomePageContent";
import ErrorPage from "./components/ErrorPage";
import { useEffect, useState } from "react";
import axios from "axios";
import LoginForm from "./components/LoginForm";
import About from "./components/About";
import HowItWorks from "./components/HowItWorks";
import ViewRecoveryGame from "./components/ViewRecoveryGame";
import Cast from "./components/Cast";
import Lineup from "./components/Lineup";
import UpdateForm from "./components/UpdateForm";
import CreateForm from "./components/CreateForm";
import SignupForm from './components/SignupForm';
import Delete from "./components/Delete";


function App() {
  const [signed, setSigned] = useState(0);

  useEffect(() => {
    const getSigned = () =>
      axios
        .get("/api/user")
        .then(() => setSigned(true))
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
            path: "/error",
            element: <ErrorPage />
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
                <Delete />
              </div>
            ),
          },
          {
            path: "/games",
            element: (
              <div>
                <Header signed={signed} />
                <ViewRecoveryGame />
              </div>
            ),
          },
        ])
      } />
    </div>
  );
}

export default App;
