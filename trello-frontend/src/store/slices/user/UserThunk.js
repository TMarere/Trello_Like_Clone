//UserThunk do all communication with server regarding to user entity
//thunk: delayed job


import { createAsyncThunk } from "@reduxjs/toolkit";
import httpClient from "../../../lib/httpClient";

//createAsyncThunk
export const authenticateUser = createAsyncThunk(
  //this str should be unique
  "user/authenticate",
  
  //payload creator
  async ({email, password}) => {
    let user = null
    
    //call server
    user = await httpClient.post("user/authenticate", {email,password})
    console.log(user);

    //axio data
    return user.data

  }
);

//registeration
//need test
export const registerUser = createAsyncThunk(
  //this str should be unique
  "user/register",


  //payload creator
  async ({firstName, lastName, email, password, securityAns}) => {
    let user = null
    
    //call server
    user = await httpClient.post("/user/authenticate", {firstName, lastName, email, password, securityAns})
    console.log(user);

    //axio data
    return user.data

  }
);





export const fetchAuthenticatedUser = createAsyncThunk(
  "user/authenticated",
  
  //call api here
  async () => {
    let user = null
    user = await httpClient.get("/user/authenticated")
    console.log(user.data);

    
    return user.data

  }
);