var myApp = angular.module("myApp", ['ngMessages']);




myApp.controller("listEtudiantController", function ($scope, $http) {
	//pour la page listEtudiants
	$scope.pageEtudiants = null;
	$scope.pageCourante = 0;
	$scope.size = 5;

	$scope.listEtudiants = function () {
		$http.get("etudiants?page=" + $scope.pageCourante + "&size=" + $scope.size).then(function (data) {
			$scope.pageEtudiants = data;
			console.log(JSON.stringify(data));
			console.log("etudiants?page=" + $scope.pageCourante + "&size=" + $scope.size);
		})

	};

	//on appelle la fonction des le chargement du scope donc des le chargement de la page
	$scope.listEtudiants();

});










myApp.controller("InscriptionController", function ($scope, $http) {


	$scope.etudiant = {};
	$scope.errors = null;
	$scope.mode = { value: "form" };
	$scope.exception = {message:null};


	$scope.saveEtudiant = function () {
		$http.post("etudiants", $scope.etudiant)//ici on a utilisé success mais ca ne convenait pas a la version de angularJS utilisée
			.then(function (data) {
				if (!data["data"]["errors"]) {
					$scope.etudiant = data;
					$scope.errors = null;
					$scope.mode.value = "confirm";
					console.log("test");
				} else if ((data["data"]["errors"])) {
					$scope.errors = data["data"]["errors"]; //theoriquement les messages d'errurs sont recupéré via errors et ce qui est mis dans le formulaire d'inscription (et qui n'est pas valide ,nom ou prenom trop court )disparait de l'ecran apres appui sur le bouton save, justement a cause de la ligne //$scope.etudiant.nom = null;(les champs etant mis a null, moi ca ne disparait pas car je traite les erreurs directement depuis le formulaire, par contre meme en laissant cette ligne il ne les fait pas disparaitre je ne sais pas pourquoi)
					//$scope.etudiant.nom = null;

				}
			},function(data){
				$scope.exception.message = "Refusé vous n'avez pas les droits pour inscrire !";
               //console.log("-------->"+data["data"]["error"]);  ca c'est le message récupéré dans le json de retour on aurait pu mettre ce message a la place de celui que je viens d'ecrire
			});
	};

});




myApp.controller("IndexController", function ($http, $scope) {
	$scope.menu = ["Inscription", "Liste"];
	$scope.selectedMenu = null;

    $scope.select = function(m){
		$scope.selectedMenu = m;

	};


});


