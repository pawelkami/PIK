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
        })
        .when('/hoteldetails', {
            templateUrl: 'hoteldetails.html'
        })
        .when('/form', {
            templateUrl: 'form.html'
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
    this.setHotelName = function(n) {
        this.name = n;
    }
    this.getHotelName = function() {
        return this.name;
    }
});


app.controller('hotelDetailsController',function($scope, $http, cfgService){

    $scope.method = 'GET';
    $scope.response = null;

    $scope.hotelName = cfgService.getHotelName();
    console.log($scope.hotelName);
    $scope.completeUrl = 'hotelDetails/search/findByHotelName?hotelName=' + $scope.hotelName;
    console.log($scope.completeUrl);

    $http({method: $scope.method, url: $scope.completeUrl}).
    then(function(response) {
        $scope.status = response.status;
        $scope.data = response.data;
    }, function(response){
        $scope.data = response.data || "Request failed";
        //$scope.status = response.status;
    });

    $scope.getPreview = function(index) {
        $scope.mypreview.src = "img"+index+".src";
    }
});


app.controller('searchController',function($scope, $http, cfgService){

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
            //console.log("User = " + JSON.stringify(response.data._embedded.hotel));
            //cfgService.setHotelList(response.data._embedded.hotel);
        }, function(response){
            $scope.data = response.data || "Request failed";
            $scope.status = response.status;
        });
    $scope.setHotelNameAndDisplayDetails = function(id) {
        cfgService.setHotelName(id);
    }
});

app.controller('addHotelController', function($scope, $http,cfgService){
    $scope.method = 'POST';
    $scope.response = null;
    $scope.base64encoded = null;
    $scope.phoneregex = /^(0|[1-9][0-9]*)$/;

    $scope.completeUrl = 'hotel/';

    $scope.addHotel = function() {
        if($scope.telephone === undefined){
            alert("Podano niewłaściwy numer telefon\nPrzykład poprawnego numeru: 111111111");
            return;
        }
        $http({method: $scope.method,
            url: $scope.completeUrl, 
            headers: {'content-type': 'application/json'},
            transformRequest :  [function (data, headers) {
                return JSON.stringify(data);
            }],
            data: { "image": $scope.base64encoded, 
            "name": $scope.hotelname, 
            "city": angular.fromJson($scope.city).name }}).
        then(function(response) {
            $scope.status = response.status;
            console.log($scope.status);

            // jeśli udało się wstawić to wstawiamy jeszcze hoteldetails
            $http({method: $scope.method,
                url: "hotelDetails/", 
                headers: {'content-type': 'application/json'},
                transformRequest :  [function (data, headers) {
                    return JSON.stringify(data);
                }],
                data: { "gallery": [$scope.base64encoded], 
                "hotelName": $scope.hotelname, 
                "city": angular.fromJson($scope.city).name,
                "description": $scope.hoteldescription, 
                "email": $scope.email,
                "address": $scope.address,
                "number": $scope.telephone  }}).
            then(function(response) {
                $scope.status = response.status;
                console.log($scope.status);
            }, function(response){
                $scope.data = response.data || "Request failed";
                $scope.status = response.status;
                console.log($scope.status);
            });
        }, function(response){
            $scope.data = response.data || "Request failed";
            $scope.status = response.status;
            console.log($scope.status);
        });
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

app.controller('formController',function($scope, $http, cfgService){
    $scope.method = 'POST';
    $scope.response = null;
    $scope.completeUrl = 'customer/';

    $scope.regexphone = /^(0|[1-9][0-9]*)$/;

    $scope.reserve = function() {
        if($scope.telephoneNumber !== undefined) {

            $http({method: $scope.method,
                url: $scope.completeUrl,
                headers: {'content-type': 'application/json'},
                transformRequest :  [function (data, headers) {
                    return JSON.stringify(data);
                }],
                data: { "firstName": $scope.firstName,
                    "lastName": $scope.lastName,
                    "telephoneNumber": $scope.telephoneNumber,
                    "email": $scope.email
                }}).
            then(function(response) {
                $scope.status = response.status;
                console.log($scope.status);

                // jeśli udało się wstawić to wstawiamy docelowe Reservation
                $http({method: $scope.method,
                    url: "reservation/",
                    headers: {'content-type': 'application/json'},
                    transformRequest :  [function (data, headers) {
                        return JSON.stringify(data);
                    }],
                    data: { "roomAmount": [$scope.roomAmount],
                        "children": $scope.children,
                        "adults": $scope.adults,
                        "beginDate": $scope.beginDate,
                        "endDate": $scope.endDate,
                        "address": $scope.address
                    }}).
                then(function(response) {
                    $scope.status = response.status;
                    console.log($scope.status);
                }, function(response){
                    $scope.data = response.data || "Request failed";
                    $scope.status = response.status;
                    console.log($scope.status);
                });
            }, function(response){
                $scope.data = response.data || "Request failed";
                $scope.status = response.status;
                console.log($scope.status);
            });
            alert('Złożono rezerwację!');
            history.go(-1);
        } else {
            alert('Niepoprawne dane');
        }
    };

});
