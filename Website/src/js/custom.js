var app = angular.module("myApp", []);
var textsize = 100;

	app.controller("myCtrl", function($scope) {
    
	$scope.style1 = {
        "font-size" : textsize + "px",
		"font-family" : 'Amatic SC'
    }

	
	$scope.style2 = {
        "font-size" : textsize + "px",
		"font-family" : 'Shadows Into Light'
    }

	
	$scope.style3 = {
        "font-size" : "80px",
		"font-family" : 'Nothing You Could Do'
    }
	
    });
	
