import React from 'react';
import {useNavigate} from 'react-router-dom';
import CreateBoardForm from '../components/CreateBoardForm';
import CreateTaskForm from '../components/CreateTaskForm';
import { useParams } from "react-router-dom";


function CreateTask(){

     //navigate to the baord after create a new board
     const navigate = useNavigate();

     const { boardID } = useParams();

    //call API here
     function createTaskHandler(newTask){
        fetch('http://localhost:8070/task/create', {
            method: 'POST',
            body: JSON.stringify(newTask),
            headers:{
                'Content-Type' : 'application/json'
            }
        })
        .then(() => navigate(`/board/${boardID}`));

     }

    // Create a new task in a board. Failed.
    // function createTaskHandler(newTask){
    //    const url = new URL(`http://localhost:8070/task/createTaskOfBoard/${boardID}`);
    //    console.log(boardID);
    //     fetch(url, {
    //         method: 'PUT',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify({
    //             taskName:newTask.taskName,
    //             taskDescription: newTask.taskDescription,
    //             deadline: newTask.deadline
    //         })
    //     })
    //     .then(() => navigate(`/boards`))
    // }
    



    // TEST ONLY:
    // function createTaskHandler(task) {
    //     fetch('http://localhost:8070/task/create', {
    //         method: 'POST',
    //         body: JSON.stringify(task),
    //         headers: {
    //             'Content-Type': 'application/json'
    //         }
    //     })
    //     .then(response => {
    //         if (!response.ok) {
    //             throw new Error('Network response was not ok');
    //         }
    //         return response.json();
    //     })
    //     .then(data => {
    //         // Do something with the response data, if necessary
    //         navigate(`/board/${boardID}`);
    //     })
    //     .catch(error => {
    //         console.error('Fetch error:', error);
    //     });
    // }
    


    return(

        // boardID={boardID}
        <CreateTaskForm createTask = {createTaskHandler} />
    );
}


export default CreateTask;