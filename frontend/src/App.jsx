import './App.css';
import { useRoutes } from "react-router-dom";
import { Home } from "./Home"
import { GroupList } from "./GroupList"

function App() {
    const routes = useRoutes([
        {
            path: '/',
            element: <Home/>
        },
        {
            path: '/groups',
            element: <GroupList/>
        }
    ]);

    return <>{routes}</>;
}

export { App };
