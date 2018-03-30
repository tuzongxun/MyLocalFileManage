var app=angular.module('tzxblog',[]);
app.controller('tzxctr',function($scope,$rootScope,$http){
	$scope.name='tzx';
	$scope.typelist=['java相关','数据库','框架','设计模式','linux系统','开发工具'];
    //	首页查询方法
	$scope.getIndex=function(){
		$http.get('./getIndex').then(function(response){
    //	console.log(response.data);
			$scope.typelist=response.data.typeList;
			fileCount=response.data.fileCount;
			console.log(fileCount);
			$scope.blogs=response.data.fileModels;
			pageMethod(fileCount);
			
			$rootScope.isShow=false;
    //		document.getElementById("content").show();
		});
	};
    //	页码生成
	var pageMethod=function(fileCount){
		var pageArr=new Array();
		var pageSize=0;
		if(fileCount > 10 && fileCount%10 == 0){
			pageSize=fileCount/10;
		}else if(fileCount > 10 && fileCount%10 != 0){
			pageSize=fileCount/10+1;
		}else if(fileCount < 10){
			pageSize=1;
		}else{
			pageArr=new Array();
		};
		console.log(pageSize);
		for(var i=1;i <= pageSize;i++){	
			pageArr[i-1]=i;
			console.log(pageArr);
			$scope.pages=pageArr;	
		};
	}
     //	查询分类下笔记列表
	$scope.getTypeList=function(type){
     //		console.log(type);
		$http.post('./getTypeList',type).then(function(response){
     //			console.log(response.data);
     //			$scope.blogs=response.data;
     //			$scope.typelist=response.data.typeList;
			fileCount=response.data.fileCount;
			console.log(fileCount);
			$scope.blogs=response.data.fileModels;
			pageMethod(fileCount);
			$("#content").css("display","block");
            document.getElementById("content1").innerHTML = '';
		});
	};
	
     //	分页跳转
	$scope.getPage=function(page){
		console.log(page);
	}
	
     //	笔记详情展示
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
	
     //	关键字搜索
	$scope.search=function(){
		var searchValue=document.getElementById("search").value;
		console.log(searchValue);
		$http.post('./search',searchValue).then(function(response){
			fileCount=response.data.fileCount;
			console.log(fileCount);
			$scope.blogs=response.data.fileModels;
			pageMethod(fileCount);
			$("#content").css("display","block");
            document.getElementById("content1").innerHTML = '';
		});
	}
});