package com0.trello.board.controller;

import com0.trello.board.model.BoardModel;
import com0.trello.board.service.IBoardService;
import com0.trello.workspace.model.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/board")
public class BoardController {
    @Autowired
    IBoardService boardService;

    @PostMapping("/save")
    public String createBoard(@RequestBody BoardModel boardModel){
        return boardService.createBoard(boardModel);
    }

    @DeleteMapping("/delete/{boardID}")
    public String deleteBoard(@PathVariable long boardID){
        return boardService.deleteBoard(boardID);
    }


    @DeleteMapping("/delete")
    public String deleteBoard(@RequestBody BoardModel boardModel){
        return boardService.deleteBoard(boardModel);
    }

    @GetMapping("/get/{boardID}")
    public BoardModel findById(@PathVariable long boardID){
        return boardService.findById(boardID);
    }

    @GetMapping("/getAll")
    public List<BoardModel> getAllBoard() {return boardService.getAllBoard();}


}
