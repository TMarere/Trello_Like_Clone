package com0.trello.board.service;

import com0.trello.board.model.BoardModel;
import com0.trello.workspace.model.WorkspaceModel;

import java.util.List;

public interface IBoardService {
    String createBoard(BoardModel boardModel);
    String deleteBoard(long boardID);
    String deleteBoard(BoardModel boardModel);
    BoardModel findById(long boardID);
    List<BoardModel> getAllBoard();
}
