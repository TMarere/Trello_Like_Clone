package com0.trello.task.controller;


import com0.trello.task.model.TaskModel;
import com0.trello.task.service.ITaskService;
import com0.trello.workspace.model.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin//(origins = "http://localhost:3001")
@RequestMapping("/task")
public class TaskController {
    private final ITaskService taskService;

    @Autowired
    public TaskController(ITaskService taskServiceImpl){
        this.taskService = taskServiceImpl;
    }

    @PostMapping("/create")
    public String createTask(@RequestBody TaskModel taskModel) {
        return taskService.createTask(taskModel);
    }

    @GetMapping("/getAll")
    public List<TaskModel> getAllTasks()
    {
        return taskService.getAllTasks();
    }

    @PutMapping("/update/{taskID}")
    public ResponseEntity<TaskModel> updateStatus(@PathVariable("taskID") long taskID, @RequestParam String status){
        TaskModel taskModel = taskService.findTaskById(taskID);
        TaskModel taskModel1 = taskService.updateStatus(taskModel, status);
        if(taskModel1 != null){
            return ResponseEntity.ok(taskModel1);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/createDeadline")
    public TaskModel updateTask(@RequestBody TaskModel task)
    {
        return taskService.createDeadlineOfTask(task);
    }

    @PutMapping("/updateDeadline/{taskID}")
    public TaskModel updateDeadline(@PathVariable long taskID, @RequestParam LocalDate deadline){
        return taskService.updateDeadline(taskID,deadline);
    }

    @PutMapping("/assignUser/{taskID}")
    public TaskModel assignMember(@PathVariable long taskID, @RequestParam long userID){
        return taskService.assignMember(taskID,userID);
    }

    @PutMapping("/assignUserOfWorkspace/{taskID}")
    public TaskModel assignMemberOfWorkspace(@PathVariable long taskID, @RequestParam long userID, @RequestParam long workspaceID){
        return taskService.assignMemberOfWorkspace(taskID,userID,workspaceID);
    }

    @PutMapping("/createTaskOfBoard/{boardID}")
    public TaskModel createTaskOfBoard(@PathVariable long boardID, @RequestBody TaskModel taskModel){
        return taskService.createTaskOfBoard(boardID,taskModel);
    }

    @GetMapping("/getByBoardID/{boardID}")
    public List<TaskModel> findTaskByBoardID(@PathVariable long boardID){
        return taskService.findTaskByBoardID(boardID);
    }
}

