import './App.css';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import Header from './components/Header'
import HomePageContent from './components/HomePageContent'
import ErrorPage from './components/ErrorPage'
import { useEffect, useState } from 'react';
import axios from 'axios';
import LoginForm from './components/LoginForm';
import SignupForm from './components/SignupForm';

function App() {
  const [signed, setSigned] = useState(0)

  useEffect(
    () => {
      const getSigned = () => axios.get("/api/user").then(() => setSigned(true)).catch(() => setSigned(false))
      getSigned()
    }, []
  )
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
          }
          /*{
            path: "/error",
            element: <div>
              <Header />
              {{
                '401': <div>
                  <Header />
                  <UnauthorizedContent />
                </div>,
                '404': <div>
                  <Header />
                  <NotFoundContent />
                </div>,
              }[useSearchParams()[0].get("error")]}
              <HomePageContent />
            </div>
            }*/
        ])
      } />
    </div>
  );
}

export default App;
