<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="utf-8" />
<title>upload Ajax</title>
</head>
<body>

<!-- <style>

    .uploadResult {
        width: 100%;
        background-color: #dca7a7;
    }

    .uploadResult ul {
        display: flex;
        flex-flow: row;
        justify-content: center;
        align-items: center;
    }

    .uploadResult ul li {
        list-style: none;
        padding: 10px;
        align-content: center;
        text-align: center;
    }

    .uploadResult ul li img {
        width: 20px;
    }
    
     .uploadResult ul li span {
        color: white;
    }

    .bigPictureWrapper {
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top: 100%;
        width: 100%;
        height: 100%;
        background-color: #ebccd1;
        z-index: 100;
        background: rgba(255, 255, 255, 0.5);
    }

    .bigPicture {
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .bigPicture img {
        width: 600px;
    
}
</style> -->

	<div class='uploadDiv'>
		<input type='file' name='uploadFile' multiple>

	</div>

	<div class='uploadResult'>
	
	<div class='bigPictureWrapper'>
	  <div class='bigPicture'>
	  </div>
	</div>
	
		<ul>

		</ul>

	</div>

	<button id="uploadButton">upload</button>

	<script>
	
	//업로드된 파일 크게 보기
	function showImg(fileCallPath){
		
		$(".bigPictureWrapper").css("display","flex").show();
		
		$(".bigPicture")
		.html("<img src='/display?fileName=" +encodeURI(fileCallPath)+"'>")
		.animate({width:'100%', height:'100%'},1000);
	}
	
	
		$(document).ready(function() {

			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; // 5MB
			var cloneObj = $(".uploadDiv").clone(); //div복사
			
			
			$(".bigPictureWrapper").on("click",function(e){
				$(".bigPicture").animate({width:'100%', height:'100%'},1000);
				setTimeout(() =>{
					$(this).hide();
				},1000);
			});

			//파일 확장자 크기 설정 검수
			function checkExtension(fileName, fileSize) {

				if (fileSize >= maxSize) {
					alert("파일 크기 초과");
					return false;
				}

				if (regex.test(fileName)) {
					alert("해당 종류의 파일을 업로들 할 수 없습니다");
					return false;
				}

				return true;

			}

			var uploadResult = $(".uploadResult ul")

			function showUploaFile(uploadResultArr) {

				var str = "";

				$(uploadResultArr).each(function(i, obj) {

					if(!obj.image){
						
						var fileCallPath = encodeURIComponent(obj.uploadPath+ "/"+ obj.uuid+"_"+obj.fileName);
						
						var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
						
						str += "<li><div><a href='/download?fileName="+fileCallPath+"'>"+"<img src='/resources/img/attach.jpg'>"+obj.fileName+"</a>"+
								"<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>"+"<div></li>";				
					
					}else{
						//str += "<li>" + obj.fileName + "</li>";
						
						   var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_"+ obj.uuid+"_"+obj.fileName);
						   
						   var originPath = obj.uploadPath + "\\"+ obj.uuid + "_" + obj.fileName;
						   
						   originPath = originPath.replace(new RegExp(/\\/g),"/");
						  

						str += "<li><a href=\"javascript:showImg(\'"+originPath+"\')\">"+"<img src='display?fileName="+fileCallPath+"'></a>"+
								"<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"+"</li>";
								
					}
					

				});

				uploadResult.append(str);
			}
			
			

			
			

			
			
			$("#uploadButton").on("click", function(e) {

	
				//formdate 만들기
				var formData = new FormData();

				console.log(formData);
				
				var inputFile = $("input[name='uploadFile']");

				var files = inputFile[0].files;

				console.log("files--->" + files);

				//add fileData를 formData에 추가
				for (var i = 0; i < files.length; i++) {

					if (!checkExtension(files[i].name, files[i].size)) {

						return false;
					}

					//"uploadFile" 키, files[i] 값
					formData.append("uploadFile", files[i]);
				}

				$.ajax({
					url : '/uploadAjax',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					dataType : 'json',
					success : function(result) {

						console.log(result);

						showUploaFile(result);

						$(".uploadDiv").html(cloneObj.html());

					}
				});// $.ajax

				$(".uploadResult").on("click","span",function(e){
					
					var targetFile = $(this).data("file");
					var type = $(this).data("type");
					//console.log(targetFile);
					
				$.ajax({
					url: '/deleteFile',
					data: {fileName: targetFile, type:type},
					dataType: 'text',
					type: 'POST',
					success:function(result){
						alert(result);
					} //ajax
				})

			});

		});//uploadbtn
			
	});
		
		
		
	</script>

</body>
</html>