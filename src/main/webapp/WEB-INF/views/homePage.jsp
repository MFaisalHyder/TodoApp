<%--
  Created by IntelliJ IDEA.
  User: Muhammad Faisal Haider
  Date: 19-Mar-18
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Todo App</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
        <link href="<c:url value='/static/css/application.css' />" rel="stylesheet"/>

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="<c:url value='/static/js/application.js' />"></script>
        <script src="<c:url value='/static/js/dirPagination.js'/>"></script>
        <script src="<c:url value='/static/js/repository/todoApp_service.js' />"></script>
        <script src="<c:url value='/static/js/controller/todoApp_controller.js' />"></script>
    </head>

    <body ng-app="todoApp" class="ng-cloak" ng-controller="TodoController as ctrl">
        <div class="shadow-below-header"></div>

        <div class="generic-container">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="lead">Task Management Todo App</span></div>
                <div class="formcontainer">
                <form ng-submit="ctrl.submit()" name="appForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.task.id" />

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="taskName">Task Name</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.task.taskName" name="taskName" class="taskName form-control input-sm" placeholder="Enter Task Name" required ng-minlength="5"/>
                                <div class="has-error" ng-show="appForm.$dirty">
                                    <span ng-show="appForm.taskName.$error.required">*</span>
                                    <span ng-show="appForm.taskName.$error.minlength">Minimum length required is 5</span>
                                    <span ng-show="appForm.taskName.$invalid">This field is invalid </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="taskStatus">Task Status</label>
                            <div class="col-md-7">
                                    <select ng-model="ctrl.task.taskStatus" ng-options="status for status in ctrl.statusList" name="taskStatus" required>Select</select>
                                <div class="has-error" ng-show="appForm.$dirty">
                                    <span ng-show="appForm.taskStatus.$error.required">*</span>
                                    <span ng-show="appForm.taskStatus.$invalid">This field is invalid </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.task.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="appForm.$invalid"/>
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="appForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Task</span>
                <div class = "searchBar">
                    <form class="form-inline">
                        <input type="text" ng-model="search" class="form-control" placeholder="Search">
                    </form>
                </div>
            </div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th ng-click="ctrl.sort('id')">Task ID
                            <span class = "sortingIcon" ng-show="predicate === 'id'" ng-class="{reverse:reverse}"></span>
                        </th>
                        <th>Task Name</th>
                        <th>Task Status</th>
                        <th width="35%">Action</th>
                    </tr>
                        </thead>
                        <tbody>
                            <tr dir-paginate ="task in ctrl.tasks| orderBy:predicate:reverse|filter:search|itemsPerPage:5">
                                <td style="vertical-align: middle"><span ng-bind="task.id"></span></td>
                                <td style="vertical-align: middle"><span ng-bind="task.taskName"></span></td>
                                <td style="vertical-align: middle"><span ng-bind="task.taskStatus"></span></td>
                                <td style="vertical-align: middle">
                                    <button type="button" ng-disabled="task.taskStatus == 'COMPLETED' ? true : false" ng-click="ctrl.markTaskAsCompleted(task)" class="btn btn-success">Mark As Completed</button>
                                    <button type="button" ng-click="ctrl.editTask(task.id)" class="btn btn-warning custom-width">Edit</button>
                                    <button type="button" ng-click="ctrl.removeTask(task.id)" class="btn btn-danger custom-width">Remove</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="paginaterDiv">
                <dir-pagination-controls
                        max-size="5"
                        direction-links="true"
                        boundary-links="true" >
                </dir-pagination-controls>
            </div>
        </div>
    </body>

</html>