import React from "react";
import {useNavigate} from 'react-router-dom';
import { ListItem,Card, CardContent, Typography, Button} from "@mui/material";
import { Draggable } from "react-beautiful-dnd";
import UpdateDueDate from "../pages/UpdateDueDate";

const ListCard = ({itemObject, index}) => {


    //send task info to updateDate Due date and user
    const navigate = useNavigate();

    const goToUpdateDueDate = () => {
        navigate('/update-due-date', { state: { item: itemObject } });
    }

    const goToAssignUser = () => {
        navigate('/assign-user', { state: { item: itemObject } });
    }
    
    return (
      <Draggable draggableId={itemObject.id} key={itemObject.id} index={index}>
         
       

        {(provided) => (


            <ListItem
                key={itemObject.id}
                role={undefined}
                alignItems="flex-start"
                ref = {provided.innerRef}
                sx={{ width: '100%'}}
                {...provided.draggableProps}
                {...provided.dragHandleProps}
            >
                <Card sx={{ width: '100%'}}>
                    <CardContent>
                        <Typography variant="h5" component="h2">
                            {itemObject.taskName}
                        </Typography>
                        <Typography variant="body1" component="p">
                            {itemObject.taskDescription}
                        </Typography>
                        <Button 
                            variant="text"
                            
                            onClick={goToUpdateDueDate}
                        >
                            Due Date: {itemObject.deadline ? itemObject.deadline : "Set Due Date"}
                        </Button>
                        <Button 
                            variant="text"
                            
                            onClick={goToAssignUser}
                        >
                            User: {itemObject.user ? itemObject.user : "Assign User"}
                        </Button>
                    </CardContent>
                </Card>

               
            </ListItem>
        )}
      </Draggable>
    );
  };
  
  export default ListCard;