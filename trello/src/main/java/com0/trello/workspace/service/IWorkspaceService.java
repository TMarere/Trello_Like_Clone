package com0.trello.workspace.service;

import com0.trello.board.model.BoardModel;
import com0.trello.workspace.model.WorkspaceModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface IWorkspaceService {
    String createWorkspace(WorkspaceModel workspaceModel);

    String updateWholeWorkspace(WorkspaceModel savedWorkspace);

    WorkspaceModel findById(long workspaceID);

    String updateWorkspaceDetail(long workspaceID, String workspaceDescription);


    WorkspaceModel createBoard(long workspaceID, BoardModel boardModel);
    //WorkspaceModel createBoard(WorkspaceModel savedWorkspace, BoardModel boardModel);

    WorkspaceModel assignBoard(long workspaceID,long boardID);
    WorkspaceModel addMembers(long workspaceID,long userID);
}
