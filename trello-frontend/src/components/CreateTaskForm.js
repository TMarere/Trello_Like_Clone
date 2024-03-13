
import React, {useRef,useState} from 'react';
import {Box, Button, TextField,Typography} from '@mui/material';
import DueDatePicker from './DueDatePicker';
import dayjs from 'dayjs';
import UserPicker from './UserPicker';



function CreateTaskForm(props){




    //get user input of task name & description
    const taskNameRef = useRef();
    const taskDescriptionRef = useRef();
    const taskOwnerRef =useRef();
    
    //get selected due date
    const [selectedDate, setSelectedDate] = useState(dayjs());
    const handleDateChange = (date) => {
        setSelectedDate(date);
    };

    function createTask(e){
        e.preventDefault();
        const taskName = taskNameRef.current.value;
        const taskDescription = taskDescriptionRef.current.value;
        //date format should meet the format of LocalDate
        const deadline = selectedDate.format('YYYY-MM-DD');

        //const taskOwner = taskOwnerRef.current.value;
        //const taskBoardID = props.boardID;
        

        const newTask = {
            taskName: taskName,
            taskDescription : taskDescription,
            deadline: deadline,
            //taskOwner: taskOwner,
            //taskBoardID: taskBoardID
        }

        props.createTask(newTask);
    };

    return(

        <section style={{mt: '32px'}}>
            <Typography variant='h2' component= 'h2'>Create New Task</Typography>
            <form onSubmit={createTask}> 
                <TextField
                    id = 'taskName'
                    placeholder='Task Name'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={taskNameRef}
                />

                {/* TODO: check and understand controlled/uncontrolled datePicker */}
            
                <Box style={{marginTop:6, marginBottom: 3}}>

                      <DueDatePicker handleDateChange={handleDateChange}/>

                </Box>
              


                {/* TODO: check and understand how to implement select */}

                {/* <UserPicker /> */}

                <TextField
                    id = 'taskDescription'
                    placeholder='Task Description'
                    variant='outlined'
                    required
                    multiline
                    fullWidth
                    rows={4}
                    margin='dense'
                    inputRef={taskDescriptionRef}
                />
                <Button type='submit' variant='contained' color='primary' sx={{marginTop: '16px'}}>
                    Create Task
                </Button>
            </form>
    </section>




    );

}

export default CreateTaskForm;