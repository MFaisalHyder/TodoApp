package todoApp.manager;

import todoApp.dataModel.TaskDTO;

import java.util.List;

public interface TaskManager {

    List<TaskDTO> getAllTaskList();

    TaskDTO createTask(TaskDTO task);

    TaskDTO updateTask(TaskDTO task);

    TaskDTO updateTaskAsCompleted(Long taskId);

    TaskDTO deleteTask(Long taskId);

}