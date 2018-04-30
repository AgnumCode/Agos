var app = angular.module('myApp', []);

//Controller for 'posts' POST requests
app.controller('formCtrl', ['$scope', '$http', function ($scope, $http) {

        $scope.entry = function () {
            
            $http({
                
                url: 'php/db_get.php',
                method: "POST",
                data: {
                    'postTitle':        $scope.title,
                    'postEnd':          $scope.postEnd,
                    'content':          $scope.content,
                    'subPostUrl':       $scope.url,
                    'subPost':          $scope.subPost,
                    'subPostTitleBody': $scope.subPostTitleBody
                }})

                    .then(function (response) {
                        //Success
                        console.log(response);
                        //Error
                    }, function (response) {
                        console.log('POST failed');
                        console.log(response);
                    });
        };
    }]);

//Controller for 'posts' GET requests
app.controller('postCtrl', ['$scope', '$http', function ($scope, $http) {

        $http({
            method: 'GET',
            url: 'php/db_post.php'

        }).then(function (response) {
            // Console log for test purposes
            console.log("Successful 'GET'");
            console.log(response.data);
            // Display response data
            $scope.posts = response.data;
            
        }, function (response) {
            console.log('GET failed');
            console.log(response);
        });
    }]);

//Controller for home page
app.controller('homeCtrl', function ($scope, styleService, pictureService) {
    
    //getTextStyle(font-family, optional webkit css args, font-weight, font-size)
    $scope.style1 = styleService.getTextStyle(undefined, true);
    $scope.style2 = styleService.getTextStyle();
    $scope.style3 = styleService.getTextStyle('Amatic SC');   
          
    //Initialize array of unique numbers for use in formulating picture urls
    let nums = [];
    pictureService.getNums(nums);
    
    //get the three home page images
    $scope.background0 = pictureService.getImageUrl(nums[0]);
    $scope.background1 = pictureService.getImageUrl(nums[1]);
    $scope.background2 = pictureService.getImageUrl(nums[2]);
        
});

//pictureService provides various picture related logic
app.service('pictureService', function () {

    //@function getImageUrl returns a CSS rule for a background-image through the css url() data-type
    //@param name       -> the name of the image
    //@param path       -> the path of the image
    //@param extension  -> the image extension to use
    this.getImageUrl = function (name, path = 'home/', extension = '.png') {
        
        return { "background-image": 'url(' + path + name + extension + ')'};

    };

    //@function getNums assigns returns an array of numbers in which to reference picture files in the public_html/home folder
    //@param nums      -> an empty array
    //@param maxImage  -> the maximum amount of images located in the 'home' folder
    //@param maxScroll -> the maximum amount of images that will scroll into view
    this.getNums = function (nums, maxImage = 13, maxScroll = 3) {

        for (i = 0; i < maxScroll; i += 1) {
            nums.push(Math.ceil(Math.random() * maxImage));
        }
        return (this.removeDuplicate(nums).length < maxScroll) ? getNum() : true;

    };

    //@function removeDuplicate removes duplicate numbers in the array
    //@param arr  -> the array for which to remove duplicate numbers
    this.removeDuplicate = function (arr) {
        let unique_array = arr.filter(function (elem, index, self) {
            return index === self.indexOf(elem);
        });
        
        return unique_array;
    };


});

//styleService is responsible for creating CSS styles
app.service('styleService', function () {

    let style = {};

    //@function getTextStyle takes arguments for CSS rules pertaining to text and returns the style
    //@param font       -> font-family CSS rule
    //@param useWebKit  -> used to check ternary operator for optional additional CSS styling
    //@param fontWeight -> font-weight CSS rule
    //@param fontSize   -> font-size CSS rule
    style.getTextStyle = function (font = 'Amatic SC', useWebkit = false, fontWeight = '', fontSize = '100px') {
        
       return style = {
           
            "font-family" : font,
            "font-size"   : fontSize,
            "font-weight" : fontWeight
            
        };
        
       return (useWebkit) ? {
           
            style,
            "-webkit-text-fill-color"   : 'white', /* Will override color (regardless of order) */
            "-webkit-text-stroke-width" : '0.5px',
            "-webkit-text-stroke-color" : 'black'
            
      } : { style };
    };

    return style;
});

//Post item custom directive
app.directive('ptag', function () {
    return {
        template: `<h1>{{x.postTitle}}
                  <small style="float:right">{{x.postDate}}</small>
                  </h1>
                  <hr id="hrStyle">
                  <a href="{{x.subPostUrl}}">{{x.subPost}}</a> {{x.subPostTitleBody}}
                  <article>
                  <br>
                  <div>{{x.content}}</div>
                  <hr id="hrStyle">
                  {{x.postEnd}}&nbsp
                  <div class="btn-toolbar" style="float:right" role="group"><button type="button" ng-click="delete($index)" class="btn btn-danger btn-lg">Delete</button>
                  <button type="button" ng-click="edit($index)" class="btn btn-info btn-lg">Edit</button></div>
                  </article>`
    };
});

//Temporary Function
/* function makeid() {
 var text = "";
 var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 for (var i = 0; i < 8; i++) 
 text += possible.charAt(Math.floor(Math.random() * possible.length));
 return text;
 } */

/* //Retrieve JSON data and display in view model
 $http.get("data.json").then(function(response) {
 
 //response.data.posts[x]
 response.data.posts.reverse();
 
 $scope.$sce = $sce;
 //assign posts to view model
 $scope.posts = response.data.posts;
 })
*/