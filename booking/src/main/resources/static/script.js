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


app.service('cfgService',function(){
    this.dbginfo = '1;';
    this.getinfo = function () {
        return this.dbginfo;
    }

    this.city = 'Warsaw';
    this.getCity = function() {
        return this.city;
    }
    this.setCity = function(c) {
        this.city = c;
    }
});

app.controller('searchController',function($scope, $http, cfgService){

    $scope.method = 'GET';
    $scope.response = null;

    $scope.search = function() {
        //console.log(angular.fromJson($scope.city).name);
        cfgService.setCity(angular.fromJson($scope.city).name);
    };

});


app.controller('resultController',function($scope, $http, cfgService){

    $scope.method = 'GET';
    $scope.response = null;
    
    $scope.cityname = cfgService.getCity();
    console.log($scope.cityname);
    $scope.completeUrl = 'hotel/search/findByCity?city=' + $scope.cityname;
    console.log($scope.completeUrl);

    $http({method: $scope.method, url: $scope.completeUrl}).
        then(function(response) {
            $scope.status = response.status;
            $scope.data = response.data._embedded.hotel;
            console.log("User = " + JSON.stringify(response.data._embedded.hotel));
            cfgService.setHotelList(response.data._embedded.hotel);
        }, function(response){
            $scope.data = response.data || "Request failed";
            $scope.status = response.status;
        });
});
