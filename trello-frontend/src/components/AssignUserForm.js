import React from 'react';
import {Button, TextField, Typography, Container, Box} from '@mui/material';
import UserPicker from "./UserPicker"

export default function AssignUserForm({taskData, assignUser, onUserSelect, selectedUser}){
    const taskName = taskData.taskName;
    const taskDescription = taskData.taskDescription;

    const handleSubmit = (event) => {
        event.preventDefault();
        if (selectedUser) {
            assignUser(selectedUser);
        }
    }

    return(
        <Container>
            <Typography variant='h2' component= 'h2'>Assign User</Typography>
            <form onSubmit={handleSubmit}> 
                <TextField
                    id='taskName'
                    placeholder={taskName}
                    variant='outlined'
                    disabled
                    fullWidth
                    margin='dense'
                />
                <Box style={{marginTop:6, marginBottom: 3}}>
                    <UserPicker onUserSelect={onUserSelect} />
                </Box>
                <TextField
                    id='taskDescription'
                    placeholder={taskDescription}
                    variant='outlined'
                    disabled
                    multiline
                    fullWidth
                    rows={4}
                    margin='dense'
                />
                <Button type='submit' variant='contained' color='primary' sx={{marginTop: '16px'}}>
                    Assign User
                </Button>
            </form>
        </Container>
    )
}
