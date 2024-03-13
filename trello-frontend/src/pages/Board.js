import ViewTasks from "../components/ViewTasks";
import React, {useEffect, useState}from "react";
import { useParams } from "react-router-dom";
import { Button, Link, Grid, Container } from "@mui/material";
import ListColumn from "../components/ListColumn"
import moment from "moment";

//for drag&drop
import { DragDropContext } from "react-beautiful-dnd";



export default function Board(){

    const {boardID} = useParams();

    //task state
    const [tasksData, setTasksData] = useState([]);

    // save info that user input from the search_box (wenxu)
    const [searchQuery, setSearchQuery] = useState("");
    const [taskFilter, setTaskFilter] = useState("all");

    //list state
    const [columns, setColumns] = useState({
        Todo: {
          id: "Todo",
          list: []
        },
        Doing: {
          id: "Doing",
          list: []
        },
        Done: {
          id: "Done",
          list: []
        }
    });

    //to prevent multiple loading
    const [loading, setLoading] = useState(true);

    //fetch tasks from API and sort them into columns
    const getAllTasks = () => {
        fetch('http://localhost:8070/task/getAll')
          .then(response => response.json())
          .then(tasks => {
            //Debug
            console.log(tasks);


            //deep copy of the colums state to avoid modifying the original state
            const newColumns = JSON.parse(JSON.stringify(columns));


            const newTasks = tasks.map(task => ({
               //dragableID should be a string 
              id: String(task.taskID),
              taskName: task.taskName,
              taskDescription: task.taskDescription,
              status: task.status || "Todo",
              deadline: task.deadline,
              user: task.user

            }));


            //Initialize each list in the columns with an empty array
            Object.values(newColumns).forEach(column => {
                column.list = [];
            });
            
            newTasks.forEach(newTask => {
              if (newColumns[newTask.status]) {
                newColumns[newTask.status].list.push(newTask);
              }
            });
            
            setTasksData(newTasks);
            setColumns(newColumns); 
            // Data is fetched, set loading to false
            setLoading(false); 
          });
      };
      
      // check if it's due today
    const isDueToday = task => {
        const now = moment();
        const taskDeadline = moment(task.deadline);
        return taskDeadline.isSame(now, 'day');
    };

    // check if it's due this week
    const isDueThisWeek = task => {
        const now = moment();
        const taskDeadline = moment(task.deadline);
        return taskDeadline.isSame(now, 'week');
    };

    // check if it's overdue
    const isOverdue = task => {
        const now = moment().startOf('day');
        const taskDeadline = moment(task.deadline);
        return taskDeadline.isBefore(now);
    };


    // lookup object for the filter
    const filterFunctions = {
        all: () => true,
        dueToday: isDueToday,
        dueThisWeek: isDueThisWeek,
        overdue: isOverdue
    };

    // search and filter function
     const getFilteredTasks = (columns, query, filter) => {
        // initialize filtered tasks to all
        let filteredTasks = JSON.parse(JSON.stringify(columns));

        // store filtered tasks to filteredTasks
        if (query !== "") {
            for (let column in filteredTasks) {
                filteredTasks[column].list = filteredTasks[column].list.filter(task =>
                    task.taskName && task.taskName.toLowerCase().includes(query.toLowerCase())
                );
            }
        }

        if (filter !== "all") {
            for (let column in filteredTasks) {
                filteredTasks[column].list = filteredTasks[column].list.filter(filterFunctions[filter]);
            }
        }

        return filteredTasks;
    };

      
    


    //Simple Version:
    //TODO: should get tasks by id
    //get tasks from backend
    // function getAllTasks(){
    //     //call API
    //    fetch('http://localhost:8070/task/getAll')
    //    .then(response => response.json())
    //    .then(tasks => {
    //         setTasksData(tasks);
    //    });

    // };


    //use useEffect hook to call getAllBoards()
    //[] makes use hte useEffect only be called once
    //call api here
    useEffect(function(){
        getAllTasks();
    }, []);



    /*
    
    Learned how to implement drag and drop from:
    1. 
        Title: How to Add Drag and Drop in React with React Beautiful DnD
        Author: Colby Fayock
        URL: https://www.freecodecamp.org/news/how-to-add-drag-and-drop-in-react-with-react-beautiful-dnd/
        Accessed Date: July 20, 2023

    2. 
        Title: React Material UI drag and drop 
        Author: Uros Randelovic
        URL：https://codesandbox.io/u/uros.randelovic
        Accessed Date: July 20, 2023
    
    
    */
    const handleOnDragEnd = async ({ source, destination }) => {
        // Make sure a destination is vaild
        if (destination === undefined || destination === null) return null;
    
        // Make sure the item is actually moving
        if (
          source.droppableId === destination.droppableId &&
          destination.index === source.index
        ) return null;
    
        // Set start and end variables
        const start = columns[source.droppableId];
        const end = columns[destination.droppableId];
    
        // If start is the same as end, the item is in the same column
        if (start === end) {
          // Move the item within the list
          // Start by making a new list without the dragged item
          //console.log(start);
          const newList = start.list.filter((_, idx) => idx !== source.index);
    
          // Then insert the item at the right location
          newList.splice(destination.index, 0, start.list[source.index]);
    
          // Then create a new copy of the column object
          const newCol = {
            id: start.id,
            list: newList
          };
    
          // Update the state
          // NOTICE: will not persist the changes on the server-side if the item is in the same column
          setColumns((state) => ({ ...state, [newCol.id]: newCol }));

          return null;
        } 
        else {
          // If start is different from end, we need to update multiple columns
          const newStartList = start.list.filter((_, idx) => idx !== source.index);
    
          // Create a new start column
          const newStartCol = {
            id: start.id,
            list: newStartList
          };
    
          // Make a new end list array
          const newEndList = end.list;
    
          // Insert the item into the end list
          newEndList.splice(destination.index, 0, start.list[source.index]);
    
          // Create a new end column
          const newEndCol = {
            id: end.id,
            list: newEndList
          };
    
          // Update the state
          setColumns((state) => ({
            ...state,
            [newStartCol.id]: newStartCol,
            [newEndCol.id]: newEndCol
          }));

          //create the task object to be updated
          const movedTask = start.list[source.index];
          movedTask.status = newEndCol.id;


         //call API
         // Prepare url with status as request parameter
         const url = new URL(`http://localhost:8070/task/update/${movedTask.id}`);
         url.searchParams.append("status", movedTask.status);

        // Call API to persist the changes on the server-side
        try {
            const response = await fetch(url, {
                method: 'PUT'
            });
            if (!response.ok) {
                throw new Error("HTTP error " + response.status);
            }
        } catch (error) {
            console.log(error);
        }
          return null;
        }
      };









    return(


        <Container>

            <div>
                {/*<implement filter based on due date>*/}
                <input placeholder="Enter Post Title" onChange={event => setSearchQuery(event.target.value)} />
                <select value={taskFilter} onChange={event => setTaskFilter(event.target.value)}>
                    <option value="all">All tasks</option>
                    <option value="dueToday">Due today</option>
                    <option value="dueThisWeek">Due this week</option>
                    <option value="overdue">Overdue</option>
                </select>
            </div>
           

           
            <Link key='Create New Task' href={`/create-task/${boardID}`} underline="none" color="inherit">
                <Button variant='contained' sx={{marginTop: '16px'}}>Create New Task</Button>
            </Link>
 
        


            {/* Render drag&drop status list */}
            {/* Use loading condition to make sure the page rendered after API were called */}
            {loading ? (
                <div>Loading...</div>
                ) : (
                <DragDropContext onDragEnd={handleOnDragEnd}>
                    <Grid container direction="row" justify="center" spacing={2}>
                        {Object.values(getFilteredTasks(columns, searchQuery, taskFilter)).map((column) => (
                            <Grid item key={column.id} xs={4}>
                              <ListColumn column={column} />
                            </Grid>
                        ))}
                    </Grid>
                </DragDropContext>
            )}

        
        </Container>
    )
}
