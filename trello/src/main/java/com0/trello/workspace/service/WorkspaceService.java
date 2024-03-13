package com0.trello.workspace.service;

import com0.trello.board.model.BoardModel;
import com0.trello.board.service.BoardService;
import com0.trello.board.service.IBoardService;
import com0.trello.user.model.UserModel;
import com0.trello.user.service.IUserService;
import com0.trello.workspace.model.WorkspaceModel;
import com0.trello.workspace.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService implements IWorkspaceService{
    private WorkspaceRepository workspaceRepository;
    private IBoardService boardService;
    private IUserService userService;
    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository,IBoardService boardService,IUserService userService){
        this.workspaceRepository = workspaceRepository;
        this.boardService = boardService;
        this.userService =userService;
    }


    public String createWorkspace(WorkspaceModel workspaceModel){
        workspaceRepository.save(workspaceModel);
        return "The workspace is created successfully";
    }

    public WorkspaceModel findById(long workspaceID){
        Optional<WorkspaceModel> workspaceModel = workspaceRepository.findById(workspaceID);
        return workspaceModel.orElse(null);
    }

    @Transactional
    public String updateWholeWorkspace(WorkspaceModel savedWorkspace){
        long savedWorkspaceID = savedWorkspace.getWorkspaceID();
        Optional<WorkspaceModel> workspaceModel = workspaceRepository.findById(savedWorkspaceID);
        if(workspaceModel.orElse(null)!=null){
            WorkspaceModel updateWorkspace = workspaceRepository.save(savedWorkspace);
            return "The workspace is updated successfully";
        }
        return "The workspace want to updated does not exist";
    }

    @Transactional
    public String updateWorkspaceDetail(long workspaceID, String workspaceDescription){
        Optional<WorkspaceModel> workspaceModel = workspaceRepository.findById(workspaceID);
        if(workspaceModel.isPresent()){
            WorkspaceModel savedWorkspace = workspaceModel.orElse(null);
            if(savedWorkspace!=null){
                savedWorkspace.setWorkspaceDescription(workspaceDescription);
                WorkspaceModel updateWorkspace = workspaceRepository.save(savedWorkspace);
                return "The workspace description is updated successfully";
            }
        }
        return "The workspace want to updated does not exist";
    }


//    public WorkspaceModel createBoard(WorkspaceModel savedWorkspace, BoardModel boardModel){
//        WorkspaceModel updatedWorkspace = null;
//        WorkspaceModel workspaceModel = null;
//        if(savedWorkspace.getWorkspaceID()!=null){
//            long savedWorkspaceID = savedWorkspace.getWorkspaceID();
//            Optional<WorkspaceModel> workspace = workspaceRepository.findById(savedWorkspaceID);
//            workspaceModel = workspace.get();
//        }
//        else{
//            workspaceModel = workspaceRepository.save(savedWorkspace);
//        }
//        List<BoardModel> boards = workspaceModel.getBoards();
//        if(boards==null) {
//            boards = new ArrayList<>();
//        }
//        boards.add(boardModel);
//        workspaceModel.setBoards(boards);
//
//        updatedWorkspace = workspaceRepository.save(workspaceModel);
//
//        return updatedWorkspace;
//    }

    public WorkspaceModel createBoard(long workspaceID, BoardModel boardModel){
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = workspaceRepository.findById(workspaceID);
        if(workspace.isPresent()){
            WorkspaceModel workspaceModel = workspace.get();
            List<BoardModel> boards = workspaceModel.getBoards();
            if(boards==null) {
                boards = new ArrayList<>();
            }

            boards.add(boardModel);
            workspaceModel.setBoards(boards);

            updatedWorkspace = workspaceRepository.save(workspaceModel);
        }
        return updatedWorkspace;
    }

    public WorkspaceModel assignBoard(long workspaceID, long boardID){
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = workspaceRepository.findById(workspaceID);
        if(workspace.isPresent()){
            WorkspaceModel workspaceModel = workspace.get();
            BoardModel boardModel = boardService.findById(boardID);
            List<BoardModel> boards = workspaceModel.getBoards();
            if(boards==null) {
                boards = new ArrayList<>();
            }

            boards.add(boardModel);
            workspaceModel.setBoards(boards);

            updatedWorkspace = workspaceRepository.save(workspaceModel);
        }
        return updatedWorkspace;
    }


    public WorkspaceModel addMembers(long workspaceID,long userID){
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = workspaceRepository.findById(workspaceID);
        if(workspace.isPresent()){
            WorkspaceModel workspaceModel = workspace.get();
            UserModel userModel = userService.findById(userID);
            List<UserModel> users = workspaceModel.getUsers();
            if(users==null) {
                users = new ArrayList<>();
            }

            users.add(userModel);
            workspaceModel.setUsers(users);

            updatedWorkspace = workspaceRepository.save(workspaceModel);
        }
        return updatedWorkspace;

    }
}
