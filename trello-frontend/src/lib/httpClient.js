import axios from "axios";
import storage from "./localStorage";


//create client
const httpClient = axios.create({
  baseURL: process.env.REACT_APP_SERVER_BASE_URL,
});


httpClient.interceptors.request.use(config => {
  //if there is a token in loacal storage 
  const token = storage.get("token")
  if(token){
      config.headers["Authorization"] = `Bearer ${token}`
  }

  //every header will have the token available
  return config
});


export default httpClient;