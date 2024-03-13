import {
    Typography,
    Container,
    Stack,
    Box,
    AppBar,
    Toolbar,
    IconButton,
    Button,
    Badge,
    
  
  } from "@mui/material";
  import { Link, Link as RouterLink } from "react-router-dom";

function Navigation() {

  const navItems = [
    { label: "Home", url: "/home" },
    { label: "Boards", url: "/boards" }
  ];

    return (
        <Box>
        <AppBar component="nav">
          <Toolbar>
            <Typography
              variant="h6"
              component="div"
              sx={{ display: { xs: "none", sm: "block" } }}
            >
              TRELLO CLONE
            </Typography>
            <Box sx={{ ml: 2, display: { xs: "none", sm: "block" } }}>
              {navItems.map((item) => (
                <Link
                  key={item.label}
                  component={RouterLink}
                  to={item.url}
                  underline="none"
                  color="inherit"
                >
                <Button sx={{ color: "#fff" }}>{item.label}</Button>
              </Link>
              ))}
            </Box>
            <Box sx={{ ml: "auto" }}>
                
            </Box>
          </Toolbar>
        </AppBar>
        <Container>
           
          
  
         
        </Container>
      </Box>

    );
   

}

export default Navigation;