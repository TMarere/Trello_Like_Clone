
import React, {useState} from 'react';
import {Button, TextField,Typography,Container,Box} from '@mui/material';
import DueDatePicker from './DueDatePicker';
import dayjs from 'dayjs';


function UpdateDueDateForm({taskData,updateDueDate}){
  
    const taskName = taskData.taskName;
    const taskDescription = taskData.taskDescription;
    const preDueDate= taskData.deadline;
    const taskID = taskData.id;

    //get selected due date
    const [selectedDate, setSelectedDate] = useState(preDueDate);

    const handleDateChange = (date) => {
        setSelectedDate(date);
    };


    function updateDueDateAction(e){
        e.preventDefault();

       
       
        const deadline = selectedDate.format('YYYY-MM-DD');

        const updateTask = {
            taskID: taskID,
            deadline: deadline
        }

        updateDueDate(updateTask);
    }


    return(
        <Container style={{marginTop:32}}>


            <Typography variant='h2' component= 'h2'>Update Due Date</Typography>

            <form onSubmit={updateDueDateAction}> 

                <TextField
                    id = 'taskName'
                    placeholder={taskName}
                    variant='outlined'
                    disabled
                    fullWidth
                    margin='dense'
                />

                <Box style={{marginTop:6, marginBottom: 3}}>

                    <DueDatePicker handleDateChange={handleDateChange}/>

                </Box>

                <TextField
                    id = 'taskDescription'
                    placeholder={taskDescription}
                    variant='outlined'
                    disabled
                    multiline
                    fullWidth
                    rows={4}
                    margin='dense'
                />
                <Button type='submit' variant='contained' color='primary' sx={{marginTop: '16px'}}>
                    Update Due Date
                </Button>
            </form>



        </Container>




    );

}

export default UpdateDueDateForm;