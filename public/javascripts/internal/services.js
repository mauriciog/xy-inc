'use strict';
/* Services */

angular.module('xy-inc-service', ['ngResource'])
	.factory('POI', ['$resource',
		function($resource) {
			return $resource('poi/:category', {}, {
				queryAll: {
					method: 'GET',
					isArray: true
				},
				queryNeighborhood: {
					method: 'GET',
					isArray: true,
					params: {
						category: 'neighbors'
					},
				}
			});
		}
	]);