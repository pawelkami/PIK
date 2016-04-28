/**
 * Created by Jan on 28.04.2016.
 */

var app=angular.module('mainapp',['ngRoute', 'google.places']);

app.config(function($routeProvider){


    $routeProvider
        .when('/search',{
            templateUrl: 'search.html'
        })
        .when('/promote',{
            templateUrl: 'promote.html'
        })
        .when('/result',{
        templateUrl: 'result.html'
        });
    
});

app.controller('cfgController',function($scope){

    $scope.search = function() {
        var cityJson = angular.fromJson($scope.city);
        console.log(cityJson.name);
    };

});
