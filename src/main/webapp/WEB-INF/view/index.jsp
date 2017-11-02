<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test App</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
</head>
<body>
<div class="container" ng-app="myTestApp" ng-controller="pageCtrl">
    <div class="row">
        <h1>Previous Calculations</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>№</th>
                <th>Calculation Date</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="x in calcHistory">
                <td>{{$index + 1}}</td>
                <td>{{x.calcDate | date:'medium'}}</td>
                <td><a href="" ng-click="showDetail(x.id, x.calcDate)">Show</a></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="row" ng-show = "showInput">
        <h2 class="col-md-3">New Calculation</h2>
        <form class="col-md-8 form-inline">
            <div class="form-group form-control">
                <input type="file" name="fileName" required file-model="uploadFile">
                <button class="btn btn-success"
                        ng-click="calculate()">Calculate</button>
            </div>
        </form>
    </div>

    <div class="row" ng-show = "showSelectData">
        <h2>№{{numberOfSelectData}}, {{dateOfSelectDate | date:'medium'}}</h2>
    </div>
    <div class="row" ng-show = "showDetailTable">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Expression</th>
                <th>Result</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat = "res in selectData">
                <td>{{res.expression}}</td>
                <td>{{res.result}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row" ng-show = "newData">
        <button class="btn btn-info btn-block" ng-click="saveCalculation()">Save Calculation</button>
    </div>
    <div class="row" ng-show = "showSelectData">
        <button class="btn-block btn btn-info" ng-click="cancelShowDetail()">Cancel</button>
        <button class="btn btn-danger btn-block" ng-click="deleteSelect()">
            Delete Calculation</button>
    </div>
</div>

<script>
    var myTestApp = angular.module('myTestApp',[]);

    myTestApp.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function(){
                    scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);


    myTestApp.controller('pageCtrl', function($scope, $http){
        $scope.showInput = true;
        var tempData = undefined;

        $http.get('/rest/calc').then(function (response) {$scope.calcHistory = response.data;
        });

        $scope.saveCalculation = function () {
            var fd = new FormData();
            fd.append('calcList',$scope.selectData.json());
            $http.post("/rest/calc/save", fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {
                //$scope.selectData = response.data;
            });
        }

        $scope.showDetail=function(historyId, dateCalc){
            $http.get('/rest/calc/'+historyId)
                .then(function (response) {
                    $scope.numberOfSelectData = historyId;
                    $scope.dateOfSelectDate = dateCalc;
                    $scope.selectData = response.data;
            });
            $scope.showInput = false;
            $scope.newData = false;
            $scope.showDetailTable=true;
            $scope.showSelectData = true;
        }
        $scope.cancelShowDetail=function(){
            $scope.showInput = true;
            $scope.newData = false;
            $scope.showDetailTable=false;
            $scope.showSelectData = false;
        }
        $scope.calculate = function(){
            if($scope.uploadFile != undefined){

                var file = $scope.uploadFile;

                var fd = new FormData();
                fd.append('file',file);
                $http.post("/rest/calc/upload", fd, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }).then(function (response) {
                    tempData = response;
                    $scope.selectData = response.data;
                });
            $scope.showInput = true;
            $scope.newData = true;
            $scope.showDetailTable=true;
            $scope.showSelectData = false;}
        };
        $scope.deleteSelect = function () {
            $http.get('/rest/calc/delete/'+$scope.numberOfSelectData).then(function (response) {$scope.calcHistory = response.data;
            });
            $scope.cancelShowDetail();
        };

    });
</script>

<style>
    tr {
        width: 100%;
        display: inline-table;
        table-layout: fixed;
    }
    table{
        height:220px;
        display: -moz-groupbox;
        width: 85%;
    }
    tbody{
        width: 85%;
        overflow-y: scroll;
        height: 175px;
        position: absolute;
    }
</style>
</body>
</html>

