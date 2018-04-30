var textsize = 100;
var nums = [];
var app = angular.module('myApp', ['ngSanitize']);

//Controller
app.controller('myCtrl', function($scope, $http, $sce) {
	
	//Generate 3 numbers for background image selection
	getNum();
	
	//Retrieve JSON data and display in view model
	$http.get("data.json").then(function(response) {

		//response.data.posts[x]
		response.data.posts.reverse();
		
		//assign scope to view model
		$scope.$sce = $sce;
		//assign posts to view model
		$scope.posts = response.data.posts;
		
	})
	
	//3 background images
	$scope.background1 = {
		"background-image" : 'url(home/' + nums[0] + '.png)'
	}
	
	$scope.background2 = {
		"background-image" : 'url(home/' + nums[1] + '.png)'
	}

	$scope.background3 = {
		"background-image" : 'url(home/' + nums[2] + '.png)'
	}
	
	//Three styles
	
	$scope.style1 = {
        "font-size" : textsize + "px",
		"font-family" : 'Amatic SC',
    }
	
	
	$scope.style2 = {
        "font-size" : textsize + "px",
		"font-family" : 'Amatic SC'
    }

	
	$scope.style3 = {
        "font-size" : textsize + "px",
		"font-family" : 'Amatic SC'
    }
});

/* //Constructor for posts
function postCreate(postTitle, postDate, postEnd, content, subPostUrl, subPost, subPostTitleBody) {
	
				  this.postTitle = postTitle,
				  this.postDate = postDate,
				  this.postEnd = postEnd,
				  this.content = content,
				  this.subPostUrl = subPostUrl,
			      this.subPost = subPost,
				  this.subPostTitleBody = subPostTitleBody			  
} */

//Directive for posts
app.directive('ptag', function() {   
		return {
		    template:       '<div>' +
							'<h1>{{x.postTitle}}</h1><a href="{{x.subPostUrl}}">{{x.subPost}}</a> {{x.subPostTitleBody}}' +
                            '<br><br><em>{{x.postDate}}</em>' +
                            '<article><br><div ng-bind-html="$sce.trustAsHtml(x.content)">{{x.content}}</div><p><br><em>{{x.postEnd}}</em></p><div class="spacing"></div></article>' +
							'</div>' +
                            '<!--Debug later <a class="btn btn-blog pull-right marginBottom10">READ MORE</a>  -->'
        }
 })

//Temporary Function
/* function makeid() {
	
	var text = "";
	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	for (var i = 0; i < 8; i++) 
	  
    text += possible.charAt(Math.floor(Math.random() * possible.length));

	return text;
 
} */

//Function
function removeDuplicateUsingFilter(arr){
    let unique_array = arr.filter(function(elem, index, self) {
        return index == self.indexOf(elem);
    });
    return unique_array;
}

//Function
function getNum() {
	
    nums = [];
	var max_image = 13;
	var max_scroll = 3;
	
	for(i = 0; i < max_scroll; i++) {
	nums.push(Math.ceil(Math.random() * max_image))
	
	}
	
	nums = removeDuplicateUsingFilter(nums);
	
	if (nums.length < 3) {
		
		getNum();
		
	}
	else return;		
}	
