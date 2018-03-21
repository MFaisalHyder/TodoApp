TodoApp.controller('TodoController', ['$scope', 'TodoService', '$window', function ($scope, TodoService, $window) {
    var todoObj = this;
    todoObj.task = {id: '', taskName: '', taskStatus: ''};
    todoObj.tasks;
    todoObj.statusList = ['COMPLETED', 'PENDING'];
    todoObj.task.taskStatus = todoObj.statusList[0];

    todoObj.getAllTaskList = function () {
        TodoService.getAllTaskListService()
            .then(
                function (response) {
                    todoObj.tasks = response.taskList;
                },
                function (errResponse) {
                    $window.alert('Error while fetching task list');
                }
            );
    };

    todoObj.getAllTaskList();

    todoObj.addTask = function (taskObj) {
        TodoService.addTaskService(taskObj)
            .then(
                function (response) {
                    todoObj.getAllTaskList();
                },
                function (errResponse) {
                    console.error('Error while creating Task');
                }
            );
    };

    todoObj.updateTask = function (taskObj) {
        TodoService.updateTaskService(taskObj)
            .then(
                function (response) {
                    todoObj.getAllTaskList();
                },
                function (errResponse) {
                    console.error('Error while updating Task');
                }
            );
    };

    todoObj.markTaskAsCompleted = function (task) {
        TodoService.markTaskAsCompletedService(task.id)
            .then(
                function (response) {
                    todoObj.getAllTaskList();
                },
                function (errResponse) {
                    $window.alert('Error while deleting Task');
                }
            );
    };

    todoObj.removeTask = function (id) {
        TodoService.removeTaskService(id)
            .then(
                function (response) {
                    todoObj.getAllTaskList();
                },
                function (errResponse) {
                    console.error('Error while deleting task');
                }
            );
    };

    todoObj.submit = function () {
        if (todoObj.task.id == '') {
            todoObj.addTask(todoObj.task);
        } else {
            todoObj.updateTask(todoObj.task);
        }
        todoObj.reset();
    };


    todoObj.editTask = function (id) {
        for (var i = 0; i < todoObj.tasks.length; i++) {
            if (todoObj.tasks[i].id == id) {
                todoObj.task = angular.copy(todoObj.tasks[i]);
                break;
            }
        }
    };

    todoObj.reset = function () {
        todoObj.task = {id: '', taskName: '', taskStatus: todoObj.statusList[0]};
        $scope.appForm.$setPristine();
    };

}]);