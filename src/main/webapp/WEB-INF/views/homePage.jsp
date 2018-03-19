<%--
  Created by IntelliJ IDEA.
  User: Muhammad Faisal Haider
  Date: 19-Mar-18
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Todo App</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
        <link href="<c:url value='/static/css/application.css' />" rel="stylesheet"/>

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="<c:url value='/static/js/application.js' />"></script>
        <script src="<c:url value='/static/js/repository/todoApp_service.js' />"></script>
        <script src="<c:url value='/static/js/controller/todoApp_controller.js' />"></script>
    </head>

    <body ng-app="todoApp" class="ng-cloak" ng-controller="TodoAppController as ctrl">
        <div class="shadow-below-header"></div>
        <div class="generic-container" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="lead">Document Insertion Form</span>
                </div>
                <div class="formcontainer">
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading"><span class="lead">List of Documents</span>
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
                                <th>File Name</th>
                                <th>Upload Date</th>
                                <th ng-click="ctrl.sort('length')">Size
                                    <span class = "sortingIcon" ng-show="predicate === 'length'" ng-class="{reverse:reverse}"></span>
                                </th>
                                <th width="20%"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr dir-paginate ="u in ctrl.filesObj| orderBy:predicate:reverse|filter:search|itemsPerPage:5">
                                <td style="vertical-align: middle"><span ng-bind="u.filename"></span></td>
                                <td style="vertical-align: middle"><span ng-bind="u.uploadDate"></span></td>
                                <td style="vertical-align: middle"><span ng-bind="u.length"></span> Bytes</td>
                                <td style="vertical-align: middle"> Remove </td>
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