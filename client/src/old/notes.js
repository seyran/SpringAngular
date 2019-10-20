var module = angular.module('myapp', ['ngResource']);

module.factory('Note', function ($resource) {
  return $resource('mynotes/:username', { username: '@username' });
})
.controller('NotesController', function($scope, Note) {
	var url = function() {
		return {username:$scope.username||'vladson'};
	};
	
	var update = function() {
		console.log(url());
		$scope.notes = Note.query(url());
	};
	
	$scope.update = update;
	
	$scope.add = function() {
		var note = new Note();
		note.body = $scope.body;
		note.$save(url(), function() {
			$scope.body = "";
			update();
		});
	};

	update();
});
