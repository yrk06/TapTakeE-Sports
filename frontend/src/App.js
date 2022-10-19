import logo from './logo.svg';
import './App.css';
import {
  createBrowserRouter,
  RouterProvider,
  Route,
  useSearchParams,
} from "react-router-dom";

import Header from './components/Header'
import HomePageContent from './components/HomePageContent'
import ErrorPage from './components/ErrorPage'

function App() {

  return (
    <RouterProvider router={
      createBrowserRouter([
        {
          path: "/",
          element: <div>
            <Header />
            <HomePageContent />
          </div>
        },
        {
          path: "/error",
          element: <ErrorPage />
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
  );
}

export default App;
