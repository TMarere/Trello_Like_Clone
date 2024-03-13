import React, {useEffect, useState}from "react";
import ViewBoards from "../components/ViewBoards";

export default function Boards(){

    const [boardsData, setBoardsData] = useState([]);

    //get boards from backend
    function getAllBoards(){
        //call API
       fetch('http://localhost:8070/board/getAll')
       .then(response => response.json())
       .then(boards => {
            setBoardsData(boards);
       });

    };


    //use useEffect hook to call getAllBoards()
    //[] makes use hte useEffect only be called once
    //call api here
    useEffect(function(){
        getAllBoards();
    }, []);

    return(
        <section>
            

            <ViewBoards boards = {boardsData}/>
        

        
        </section>


    );

}

