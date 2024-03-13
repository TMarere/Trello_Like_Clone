package com0.trello.board.service;

import com0.trello.board.model.BoardModel;
import com0.trello.board.repository.BoardRepository;
import com0.trello.workspace.model.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService implements IBoardService{
    @Autowired
    BoardRepository boardRepository;

    public String createBoard(BoardModel boardModel){
        boardRepository.save(boardModel);
        return "The board is created successfully";
    }

    public BoardModel findById(long boardID){
        Optional<BoardModel> boardModel = boardRepository.findById(boardID);
        return boardModel.orElse(null);
    }

    @Transactional
    public String deleteBoard(long boardID){
        Optional<BoardModel> boardModel = boardRepository.findById(boardID);
        if(boardModel.isPresent()){
            boardRepository.deleteById(boardID);
            return "The board is deleted successfully";
        }
        return "The board want to deleted does not exist";
    }

    @Transactional
    public String deleteBoard(BoardModel savedBoard){
        if(savedBoard.getBoardID()!= null){
            long savedBoardID = savedBoard.getBoardID();
            Optional<BoardModel> boardModel = boardRepository.findById(savedBoardID);
            if(boardModel.isPresent()){
                boardRepository.delete(savedBoard);
                return "The board is deleted successfully";
            }
        }
        return "The board want to deleted does not exist";

    }

    public List<BoardModel> getAllBoard(){
        return boardRepository.findAll();
    }

}
