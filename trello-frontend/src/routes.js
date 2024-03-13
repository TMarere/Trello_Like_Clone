import { useRoutes } from "react-router-dom"
import LogoOnlyLayout  from "./layouts/LogoOnlyLayout"
import Home from "./pages/Home"
import { Link } from "@mui/material";
import Boards from "./pages/Boards";
import CreateBoard from "./pages/CreateBoard";
import Board from "./pages/Board"
import CreateTask from "./pages/CreateTask"
import UpdateDueDate from "./pages/UpdateDueDate";
import AssignUser from  "./pages/AssignUser";


export default function Router(){

    //pass an array has an object in it
    const elements = useRoutes([
        {
            path: "/",
            element: <LogoOnlyLayout />,
            children: [
               
                {
                    path:"/home",
                    element: <Home />
                    
                },
                {
                    path:"/boards",
                    element: <Boards />
                },
                {
                    path:"/create-board",
                    element: <CreateBoard />
                },
                {
                    path:"/board/:boardID",
                    element: <Board />
                },
                {
                    path:"/create-task/:boardID",
                    element: <CreateTask />
                },
                {
                    path:"/update-due-date",
                    element: <UpdateDueDate />
                },
                {
                    path:"/assign-user",
                    element: <AssignUser />
                }
            ]
        }

    ])

    return elements
}