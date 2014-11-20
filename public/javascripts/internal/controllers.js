'use strict';

/* Controllers */
angular.module('xy-inc', ['xy-inc-service'])
	.controller('POICrtl', ['$scope', 'POI',
		function($scope, POI) {
			$scope.formPOI = new POI();
			$scope.listPOI = POI.queryAll();;

			$scope.save = function() {
				$scope.message = '';

				if ($scope.validate()) {
					$scope.formPOI.$save({},function(success){
						$scope.message = "Register saved!";
						$scope.reload();
						$scope.clear();
					}, function(error){
						$scope.message = "Error occurred!";
					});
				}
			};

			$scope.validate = function() {
				if(!$scope.formPOI.x || !$scope.formPOI.y || !$scope.formPOI.name){
					$scope.message = "Fill all fields!";
					return false;
				}

				return true;
			};

			$scope.clear = function() {
				$scope.formPOI = {};
				$scope.message = '';
			}

			$scope.reload = function(){
				$scope.listPOI = POI.queryAll();
			}
		}
	])
	.controller('POISearchCrtl', ['$scope', 'POI',
		function($scope, POI) {
			$scope.searchPOI = {};
			$scope.listPOI = [];

			$scope.search = function() {
				$scope.message = '';
				if ($scope.validate()) {
					$scope.listPOI = POI.queryNeighborhood($scope.searchPOI,
					 function(result){
					 	if(!result.length){
					 		$scope.message = "No neighbors found!";
					 	}
					 });
				}
			};

			$scope.validate = function() {
				if(!$scope.searchPOI.x || !$scope.searchPOI.y || !$scope.searchPOI.distance){
					$scope.message = "Fill all fields!";
					return false;
				}
				return true;
			};

			$scope.clear = function() {
				$scope.searchPOI = {};
			}
		}
	]);