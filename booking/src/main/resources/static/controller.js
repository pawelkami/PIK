var myApp = angular.module('myApp', ['google.places']);


myApp.controller("ctrlAutoComplete", ['$scope', function($scope){
	$scope.search = function() {
		var cityJson = angular.fromJson($scope.city);
		console.log(cityJson.name);
	};
}]);
