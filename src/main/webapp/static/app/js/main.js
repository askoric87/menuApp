var app = angular.module('menuApp', ['ngRoute', 'ui.bootstrap']);


app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/static/app/html/partial/home.html'
        })
        .when('/meals', {
        	templateUrl : '/static/app/html/partial/meals.html',
        	controller : 'mealCtrl'
        })
        
        .otherwise({
            redirectTo: '/',
        });
}]);


app.controller('mealCtrl', function ($scope, $http, $location){

	var getMeals = function(){
		var parameters = {};
		parameters.page = $scope.currentPage;
		if($scope.search && $scope.search.name){
			parameters.name = $scope.search.name;
		}
		$http.get('api/meals', {params:parameters})
			.success(function(data){
				$scope.meals = data;
				$scope.mealForSave = {};
			})
			.error(function(){
				console.log('Error');
			});
	}
	

	var getSorts = function(){
		$http.get('api/mealsorts')
			.success(function(data){
				$scope.sorts = data;
			})
			.error(function(data){
				console.log('Error');
			});
	}
	getMeals();
	getSorts();

	$scope.deleteMeal = function(id){
		$http.delete('api/meals/' + id)
			.success(function(){
				getMeals();
			})
	}

	$scope.saveMeal = function(){
		if(!$scope.mealForSave.id){
			$http.post('api/meals', $scope.mealForSave)
				.success(function(){
					getMeals();
				})
		}else {
			$http.put('api/meals/' + $scope.mealForSave.id, $scope.mealForSave)
				.success(function(){
					getMeals();
				})
		}
	}

	$scope.editMeal = function(meal){
		$scope.mealForSave = meal;
	}

	$scope.findMeal = function(){
		getMeals();
	}

	$scope.currentPage=0;
	$scope.changePage = function (i) {
		$scope.currentPage += i;
		
		getMeals();
	}
	
	

});
