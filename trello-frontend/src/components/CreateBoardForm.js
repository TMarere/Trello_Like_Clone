import {Button, TextField,Typography} from '@mui/material';
import React, {useRef} from 'react';



function CreateBoardForm(props){

    const boardNameRef = useRef();
    const boardDescriptionRef = useRef();


    function createBoard(e){
        e.preventDefault();
        const boardName = boardNameRef.current.value;
        const boardDescription = boardDescriptionRef.current.value;

        const newBoard = {
            boardName: boardName,
            boardDescription : boardDescription
        }

        props.createBoard(newBoard);
    };

  

    return(

        <section style={{mt: '32px'}}>
            <Typography variant='h2' component= 'h2'>Create New Board</Typography>
            <form onSubmit={createBoard}> 
                <TextField
                    id = 'boardName'
                    placeholder='Board Name'
                    variant='outlined'
                    required
                    fullWidth
                    margin='dense'
                    inputRef={boardNameRef}
                />

                <TextField
                    id = 'boardDescription'
                    placeholder='Board Description'
                    variant='outlined'
                    required
                    multiline
                    fullWidth
                    rows={4}
                    margin='dense'
                    inputRef={boardDescriptionRef}
                />
                <Button type='submit' variant='contained' color='primary' sx={{marginTop: '16px'}}>
                    Create Board
                </Button>
            </form>
        </section>
    );
}



export default CreateBoardForm;