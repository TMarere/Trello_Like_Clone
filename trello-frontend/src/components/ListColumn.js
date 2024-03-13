import React from "react";
import { Droppable } from "react-beautiful-dnd";
import {List, Typography, Container, Box} from "@mui/material";
import ListCard from "./ListCard";


const ListColumn = ({ column }) => {
  return (
    <Container
      style={{
        backgroundColor: "#2074d4",
        marginTop: 20,
        padding: 20,
        color: "white"
      }}
    >
      
      
      <Typography variant={"h4"} align="center">{column.id}</Typography>
 
      
      <Droppable droppableId={column.id}>
        {(provided) => (
            <List ref={provided.innerRef} {...provided.droppableProps}>
              {column.list.map((itemObject, index) => {
                
                return (
                
                    <ListCard key={itemObject.id} index={index} itemObject={itemObject} />
                
                )
              })}
              {provided.placeholder}
            </List>
        )}
      </Droppable>
    </Container>
  );
};

  
  export default ListColumn;