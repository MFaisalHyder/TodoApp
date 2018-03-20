package todoApp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoApp.constant.TaskStatus;
import todoApp.dataModel.TaskDTO;
import todoApp.entity.Task;
import todoApp.manager.TaskManager;
import todoApp.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements TaskManager {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getAllTaskList() {
        List<Task> tasksListFromDB = taskRepository.findAll();
        List<TaskDTO> tasksList = new ArrayList<>();
        TaskDTO taskObject;

        for (int i = 0; i < tasksListFromDB.size(); i++) {
            taskObject = new TaskDTO();
            BeanUtils.copyProperties(tasksListFromDB.get(i), taskObject);

            tasksList.add(taskObject);
        }

        return tasksList;
    }

    @Override
    public TaskDTO createTask(TaskDTO task) {
        TaskDTO response = new TaskDTO();

        Task taskToBeCreated = new Task();
        taskToBeCreated.setTaskName(task.getTaskName());
        taskToBeCreated.setTaskStatus(task.getTaskStatus());

        taskToBeCreated = taskRepository.save(taskToBeCreated);

        response.setResponse(taskToBeCreated.getId() != null);

        return response;
    }

    @Override
    public TaskDTO updateTask(TaskDTO task) {
        TaskDTO response = new TaskDTO();
        Task taskToBeUpdated;

        if (task.getId() == null || task.getId().toString().isEmpty()) {
            response.setResponse(false);

            return response;
        }

        taskToBeUpdated = taskRepository.findOne(task.getId());
        if (taskToBeUpdated == null) {
            response.setResponse(false);

            return response;
        }

        taskToBeUpdated.setTaskName(task.getTaskName());
        taskToBeUpdated.setTaskStatus(task.getTaskStatus());

        taskRepository.save(taskToBeUpdated);

        response.setResponse(true);

        return response;
    }

    @Override
    public TaskDTO updateTaskAsCompleted(Long taskId) {
        Task taskToBeMarkedAsCompleted = taskRepository.findOne(taskId);
        TaskDTO response = new TaskDTO();

        if (taskToBeMarkedAsCompleted == null) {
            response.setResponse(false);

            return response;
        }

        taskToBeMarkedAsCompleted.setTaskStatus(TaskStatus.COMPLETED.toString());
        taskRepository.save(taskToBeMarkedAsCompleted);

        response.setResponse(true);

        return response;
    }

    @Override
    public TaskDTO deleteTask(Long taskId) {
        Task taskToBeDeleted = taskRepository.findOne(taskId);
        TaskDTO response = new TaskDTO();

        if (taskToBeDeleted == null) {
            response.setResponse(false);

            return response;
        }

        taskRepository.delete(taskToBeDeleted);

        response.setResponse(true);

        return response;
    }
}