TodoApp.factory('TodoService', ['$http', '$q', function ($http, $q) {

    return {

        getAllTaskListService: function () {
            return $http.get('http://192.168.0.100:8080/App/taskList')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        },

        addTaskService: function (task) {
            return $http.post('http://192.168.0.100:8080/App/addTask/', task)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        },

        updateTaskService: function (task) {
            return $http.put('http://192.168.0.100:8080/App/updateTask/', task)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        },

        markTaskAsCompletedService: function (id) {
            return $http.put('http://192.168.0.100:8080/App/updateTaskStatus/id/' + id)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        },

        removeTaskService: function (id) {
            return $http.delete('http://192.168.0.100:8080/App/deleteTask/id/' + id)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        }

    };
}]);