package todoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoApp.dataModel.TaskDTO;
import todoApp.manager.TaskManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoAppController {

    @Autowired
    private TaskManager taskManager;

    @RequestMapping(value = "/taskList", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getTasksList() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        List<TaskDTO> tasksList = taskManager.getAllTaskList();

        result.put("result", tasksList != null && tasksList.size() > 0 ? tasksList.size() + " - number of task" : "No task found");
        result.put("taskList", tasksList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody TaskDTO taskToBeCreated) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        TaskDTO createdTask = taskManager.createTask(taskToBeCreated);

        result.put("result", createdTask);

        return createdTask.isResponse() ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateTask", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateTask(@RequestBody TaskDTO taskToBeUpdated) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        TaskDTO updatedTask = taskManager.updateTask(taskToBeUpdated);

        result.put("result", updatedTask);

        return updatedTask.isResponse() ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateTaskStatus/id/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> markTaskAsCompleted(@PathVariable("id") Long taskID) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        TaskDTO updatedTask = taskManager.updateTaskAsCompleted(taskID);

        result.put("result", updatedTask);

        return updatedTask.isResponse() ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteTask/id/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> getTasksList(@PathVariable("id") Long taskID) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        TaskDTO deletedTask = taskManager.deleteTask(taskID);

        result.put("result", deletedTask);

        return deletedTask.isResponse() ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

}