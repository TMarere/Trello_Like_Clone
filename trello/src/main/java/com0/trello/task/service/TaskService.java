package com0.trello.task.service;

import com0.trello.board.model.BoardModel;
import com0.trello.board.service.IBoardService;
import com0.trello.task.model.TaskModel;
import com0.trello.task.repository.TaskRepository;
import com0.trello.user.model.UserModel;
import com0.trello.user.service.IUserService;
import com0.trello.workspace.model.WorkspaceModel;
import com0.trello.workspace.service.IWorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    TaskRepository taskRepository;
    private IUserService userService;
    private IWorkspaceService workspaceService;
    private IBoardService boardService;
    @Autowired
    public TaskService(TaskRepository taskRepository,IUserService userService,IWorkspaceService workspaceService,IBoardService boardService) {
        this.taskRepository=taskRepository;
        this.userService =userService;
        this.workspaceService = workspaceService;
        this.boardService = boardService;
    }

    public String createTask(TaskModel taskModel){
        if(taskRepository.save(taskModel) != null){
            return "Task has been successfully created";
        }
        return "Empty task model passed";
    }

    @Override
    public TaskModel findTaskById(long taskID) {
        TaskModel taskModel = null;

        Optional<TaskModel> optionalTaskModel = taskRepository.findById(taskID);
        if(optionalTaskModel.isPresent())
        {
            taskModel=optionalTaskModel.get();
        }

        return taskModel;
    }


    public List<TaskModel> getAllTasks()
    {
        return taskRepository.findAll();
    }

    public TaskModel updateStatus(TaskModel taskModel, String status){
        taskModel.setStatus(status);
        taskRepository.save(taskModel);
        if(taskModel.getStatus().equals(status)){
            return taskModel;
        }
        return null;
    }
    @Override
    public TaskModel createDeadlineOfTask(TaskModel task) throws ResponseStatusException {

        Optional<TaskModel> fetchedTask = this.taskRepository.findById(task.getTaskID().longValue());
        TaskModel oldTask = fetchedTask.get();
        oldTask.setDeadline(task.getDeadline());

        if(oldTask.getDeadline().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid deadline.");
        }

        TaskModel savedTask = this.taskRepository.save(oldTask);
        return savedTask;
    }

    @Override
    public TaskModel assignMemberOfWorkspace(long taskID, long userID, long workspaceID) throws ResponseStatusException{
        TaskModel updatedTask = null;
        Optional<TaskModel> task = taskRepository.findById(taskID);
        if(task.isPresent()){
            TaskModel taskModel = task.get();
            UserModel userModel = userService.findById(userID);
            UserModel user = taskModel.getUser();

            WorkspaceModel workspaceModel = workspaceService.findById(workspaceID);

            if(user!=null){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This task has been assigned.");
            }
            if(!workspaceModel.getUsers().contains(userModel)){
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The user doesn't in the user list of workspace.");
            }


            if(user==null&workspaceModel.getUsers().contains(userModel)){
                taskModel.setUser(user);
            }
            updatedTask = taskRepository.save(taskModel);
        }
        return updatedTask;
    }

    @Override
    public TaskModel assignMember(long taskID, long userID)throws ResponseStatusException{
                TaskModel updatedTask = null;
        Optional<TaskModel> task = taskRepository.findById(taskID);
        if(task.isPresent()){
            TaskModel taskModel = task.get();
            UserModel userModel = userService.findById(userID);
            UserModel user = taskModel.getUser();
            if(user==null) {
                taskModel.setUser(userModel);
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "This task has been assigned.");
            }

            updatedTask = taskRepository.save(taskModel);
        }
        return updatedTask;
    }

    @Override
    public TaskModel createTaskOfBoard(long boardID, TaskModel taskModel)throws ResponseStatusException{
        TaskModel updatedTask = null;
        BoardModel boardModel = boardService.findById(boardID);
        TaskModel savedTaskModel = taskModel;
        if(boardModel==null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The board want to task does not exist.");
        }
        else{
            savedTaskModel.setBoard(boardModel);
        }
        updatedTask = taskRepository.save(savedTaskModel);
        return updatedTask;
    }

    @Override
    public List<TaskModel> findTaskByBoardID(long boardID){
        BoardModel boardModel = boardService.findById(boardID);
        List<TaskModel> tasks = boardModel.getTasks();
        return tasks;
    }

    @Override
    public TaskModel updateDeadline(long taskID, LocalDate deadline){
        TaskModel updatedTask = null;
        Optional<TaskModel> task = taskRepository.findById(taskID);
        if(task.isPresent()){
            TaskModel taskModel = task.get();
            taskModel.setDeadline(deadline);
            if(taskModel.getDeadline().isBefore(LocalDate.now())) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid deadline.");
            }
            updatedTask = taskRepository.save(taskModel);
        }
        return updatedTask;
    }

}

