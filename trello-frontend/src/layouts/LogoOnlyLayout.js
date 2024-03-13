import { Outlet } from "react-router-dom";
import { Container, Box, Typography } from "@mui/material";

export default function LogoOnlyLayout() {
  return (

    <Container sx={{marginTop: 10}}>



      <Outlet />
    </Container>
    
      
      
      
   
  );
}
