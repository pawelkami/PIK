var app=angular.module('mainapp',['ngRoute', 'google.places']);

app.config(function($routeProvider){


    $routeProvider
        .when('/', {
            templateUrl: 'mainpage.html'
        })
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

app.controller('addHotelController', function($scope, $http,cfgService){
    $scope.method = 'POST';
    $scope.response = null;
    $scope.base64encoded = null;

    $scope.completeUrl = 'hotel/';

    $scope.addHotel = function() {
        $http({method: $scope.method,
            url: $scope.completeUrl, 
            headers: {'content-type': 'application/json'},
            data: { "image": $scope.b64encoded, "name": $scope.hotelname, "city": angular.fromJson($scope.city).name }}).
        then(function(response) {
            $scope.status = response.status;
            console.log($scope.status);
        }, function(response){
            $scope.data = response.data || "Request failed";
            $scope.status = response.status;
            console.log($scope.status);
        });

        // TODO poprawić obrazek wysyłanie i dodać wysyłanie do HotelDetails
    }

    var handleFileSelect = function(evt){
        var files = evt.target.files;
        var file = files[0];

        if (files && file) {
            var reader = new FileReader();

            reader.onload = function(readerEvt) {
                var binaryString = readerEvt.target.result;
                $scope.base64encoded = btoa(binaryString);
            };

            reader.readAsBinaryString(file);
        }
    }
    document.getElementById('photoToSend').addEventListener('change', handleFileSelect, false);

});