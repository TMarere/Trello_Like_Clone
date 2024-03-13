import { Container } from "@mui/material";
import React, { useState } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import AssignUserForm from "../components/AssignUserForm";

export default function AssignUser(){
    const [selectedUser, setSelectedUser] = useState(null);
    const location = useLocation();
    const navigate = useNavigate();
    const taskData = location.state?.item;

    //call API
    async function assignUserHandler(assignedUser){
        try {
            const response = await fetch(`http://localhost:8070/task/assignUser/${taskData.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userID: assignedUser.id })
            });
            const data = await response.json();
            navigate('/tasks');
        } catch (error) {
            console.error('Error:', error);
        }
    }

    return(
        <Container>
            <AssignUserForm 
                taskData={taskData} 
                assignUser={assignUserHandler}
                onUserSelect={setSelectedUser}
                selectedUser={selectedUser}
            />
         </Container>
    )
}
