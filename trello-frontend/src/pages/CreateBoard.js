import React from 'react';
import {useNavigate} from 'react-router-dom';
import CreateBoardForm from '../components/CreateBoardForm';

function CreateBoard(){

    //navigate to baords after create a new board
    const navigate = useNavigate();



    //call API here
    //board data are got from CreateBoardForm
    function createBoardHandler(board){
        fetch('http://localhost:8070/board/save', {
            method: 'POST',
            body: JSON.stringify(board),
            headers:{
                'Content-Type' : 'application/json'
            }
        }).then(navigate('/boards'));
    }

    return(


        <CreateBoardForm createBoard = {createBoardHandler}/>

    );

};

export default CreateBoard;