import { Typography,Container } from '@mui/material';
import React from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import UpdateDueDateForm from "../components/UpdateDueDateForm"



function UpdateDueDate(){

    const location = useLocation();
    const navigate = useNavigate();

    const taskData = location.state?.item;


    //call API to update due date here
    // Prepare url with status as request parameter
     
    function updateDueDateHandler(updateTask){
    
        const url = new URL(`http://localhost:8070/task/updateDeadline/${updateTask.taskID}`);
        url.searchParams.append("deadline", updateTask.deadline);
        fetch(url, {
            method: 'PUT',
        }).then(() => navigate(`/boards`));
    }
    





    return(

        <Container>

           
            <UpdateDueDateForm taskData={taskData} updateDueDate = {updateDueDateHandler}/>
            
        </Container>
        
    )

}



export default UpdateDueDate;