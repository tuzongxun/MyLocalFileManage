var app=angular.module('tzxblog',[]);
app.controller('tzxctr',function($scope,$rootScope,$http){
	$scope.name='tzx';
	$scope.typelist=['java相关','数据库','框架','设计模式','linux系统','开发工具'];
	$scope.getIndex=function(){
		$http.get('./getIndex').then(function(response){
//			console.log(response.data);
			$scope.typelist=response.data.typeList;
			$scope.blogs=response.data.fileModels;
			$rootScope.isShow=false;
//			document.getElementById("content").show();
		});
	};
	$scope.getTypeList=function(type){
//		console.log(type);
		$http.post('./getTypeList',type).then(function(response){
//			console.log(response.data);
			$scope.blogs=response.data;
			$("#content").css("display","block");
            document.getElementById("content1").innerHTML = '';
		});
	};
	
	$scope.getBlogInfo=function(filePath){
//		console.log(filePath);
		$http.post('./getBlogInfo',filePath).then(function(response){
//			console.log(response.data);
			content=response.data.fileContent;
			var converter = new showdown.Converter();
//			console.log(content);
            var html = converter.makeHtml(content);
            document.getElementById("content1").innerHTML = html;
            $("#content").css("display","none");
//			document.getElementById("con").innerHTML=content;
		});
	}
	
	$scope.search=function(){
		var searchValue=document.getElementById("search").value;
		console.log(searchValue);
		$http.post('./search',searchValue).then(function(response){
			console.log(response.data);
//			content=response.data.fileContent;
			$scope.blogs=response.data;
			$("#content").css("display","block");
            document.getElementById("content1").innerHTML = '';
		});
	}
});