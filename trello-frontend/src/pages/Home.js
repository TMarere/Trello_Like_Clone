import {Typography} from  "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { useDispatch, useSelector } from "react-redux";
import AccountCircle from "@mui/icons-material/AccountCircle";
import MailIcon from "@mui/icons-material/Mail";
import NotificationsIcon from "@mui/icons-material/Notifications";
import { useEffect } from "react";
import { fetchAuthenticatedUser } from "../store/slices/user/UserThunk";


export default function Home() {

  //fetching user details
  const { data: user, isFetching } = useSelector(
    (state) => state.user.details
  );




  

  return (
    <>
   
    
   
    <Typography>Home page</Typography>          
    </>  
  );
}

//make sure use the same with api response: {user["first_name"]}