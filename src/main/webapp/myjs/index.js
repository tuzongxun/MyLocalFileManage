var app=angular.module('tzxblog',[]);
app.controller('tzxctr',function($scope,$http){
	$scope.name='tzx';
	$scope.typelist=['java相关','数据库','框架','设计模式','linux系统','开发工具'];
	$scope.getIndex=function(){
		$http.get('./getIndex').then(function(response){
			console.log(response.data);
			$scope.typelist=response.data.typeList;
			$scope.blogs=response.data.fileModels;
			/*var converter = new showdown.Converter();
			console.log(content);
            var html = converter.makeHtml(content);
            document.getElementById("con").innerHTML = html;*/
//			document.getElementById("con").innerHTML=content;
		});
	};
	$scope.getTypeList=function(type){
		console.log(type);
		$http.post('./getTypeList',type).then(function(response){
			console.log(response.data);
			$scope.blogs=response.data;
			/*var converter = new showdown.Converter();
			console.log(content);
            var html = converter.makeHtml(content);
            document.getElementById("con").innerHTML = html;*/
//			document.getElementById("con").innerHTML=content;
		});
	}
});