package com0.trello.workspace.controller;

import com0.trello.board.model.BoardModel;
import com0.trello.workspace.model.WorkspaceModel;
import com0.trello.workspace.service.IWorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {
    @Autowired
    IWorkspaceService workspaceService;

    @PostMapping("/save")
    public String createWorkspace(@RequestBody WorkspaceModel workspaceModel){
         return workspaceService.createWorkspace(workspaceModel);
    }

    @PostMapping("/update")
    public String updateWholeWorkspace(@RequestBody WorkspaceModel savedWorkspace){
        return workspaceService.updateWholeWorkspace(savedWorkspace);
    }

    @PostMapping("/updateDetail/{workspaceID}")
    public String updateWorkspaceDetail(@PathVariable long workspaceID, @RequestParam String workspaceDescription){
        return workspaceService.updateWorkspaceDetail(workspaceID,workspaceDescription);
    }

    @GetMapping("/get/{workspaceID}")
    public WorkspaceModel findById(@PathVariable long workspaceID){
        return workspaceService.findById(workspaceID);
    }



//    @PostMapping("/createBoard")
//    public WorkspaceModel createBoard(@RequestBody WorkspaceModel savedWorkspace, BoardModel boardModel){
//        return workspaceService.createBoard(savedWorkspace,boardModel);
//    }

    @PostMapping("/createBoard/{workspaceID}")
    public WorkspaceModel createBoard(@PathVariable long workspaceID, @RequestBody BoardModel boardModel) {
        return workspaceService.createBoard(workspaceID,boardModel);
    }

    @PutMapping("/assignBoard/{workspaceID}")
    public WorkspaceModel updateBoard(@PathVariable long workspaceID, @RequestParam long boardID){
        return workspaceService.assignBoard(workspaceID,boardID);
    }

    @PutMapping("/addMember/{workspaceID}")
    public WorkspaceModel addMembers(@PathVariable long workspaceID,@RequestParam long userID){
        return workspaceService.addMembers(workspaceID,userID);
    }


}
