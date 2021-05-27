<%@ include file="../includes/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<div class="content">
	<div class="row">
		<div class="col-md-12">
			<h3 class="description">36.5℃ 사진관</h3>
		</div>
	</div>
</div>


<!-- input -->
<div class="writebox">
	<div class="form-group">
		<label for="exampleFormControlInput1">작품</label> <input type="text"
			class="form-control" name="title">
	</div>
	<div class="form-group">
		<label for="exampleFormControlTextarea1">내용</label>
		<textarea class="form-control" id="content" rows="3"></textarea>
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">작가</label> <input type="text"
			class="form-control" name="writer"
			value="<sec:authentication property="principal.username"/>"
			readonly="readonly">
	</div>
	<span class="input-group-btn">
		<button class="btn btn-fab btn-round btn-primary">
			<input type="file" multiple="multiple" name="uploadFile">
		</button>
	</span>
	<div class="uploadResult">
		<ul>

		</ul>

	</div>
</div>


<!-- 등록 버튼 -->
<div class="row">
	>
	<button class="btn btn-outline-primary btn-round" id="registerButton">
		<i class="fa fa-heart"></i> 작품전시
	</button>
	<button class="btn btn-outline-primary btn-round" id="listButton">
		<i class="fa fa-heart"></i> 목록
	</button>
</div>


<!-- 모달창 -->
<div class="modal" tabindex="-1" role="dialog" id="registerModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>등록하시겠습니까</p>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary btn-round" id="yesButton">네</button>
				<button class="btn btn-primary btn-round" id="noButton">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 이중모달 확인창 -->
<div class="modal" tabindex="-1" role="dialog" id="closeModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>등록 되었습니다</p>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>


<form class="actionForm" action="/diary/list" method="get">
	<input type="hidden" name="page" value="${pageDTO.page }"> <input
		type="hidden" name="perSheet" value="${pageDTO.perSheet }"> <input
		type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

</form>

<script>


    var csrfHeaderName = '${_csrf.headerName}';
    var csrfTokenValue = '${_csrf.token}';
    
    
    $.ajaxSetup({
        headers: {
        	csrfHeaderName: csrfTokenValue
        }
    });

    const action = document.querySelector(".actionForm");
    var inputFile = document.querySelector("input[name='uploadFile']");
    var formData = new FormData();

    //===========등록ajax=================

    function registerAjax(data) {
        console.log("==========registerAjax==========")

        //controller호출
        return fetch("/diary/register", {
            method: 'post',
          //  headers: {'Content-Type': 'application/json'},
            proceessData:false,
       	    contentType:false,
			headers: {'${_csrf.headerName}': '${_csrf.token}', 'Content-Type': 'application/json'},
            body: data
            	//JSON.stringify(data)
            	
        })
            // controller return 받아옴
            .then(res => {

                if (!res.ok) {
                    throw new Error(res);
                }

                console.log("=========fetch=============");
                console.log(res);
                return res.text();
                //produces = { "text/plain" }) text니깐  제대로 받아옴

            })
            .catch(res => {
                console.log("=========catch=============");
                return res;
            })
    }


    //=============등록버튼 누르면 모달창뜨게함================

    document.querySelector("#registerButton").addEventListener("click", e => {
        e.preventDefault();

        //모달창
        const registerModal = $("#registerModal");
        registerModal.modal('show')


    }, false)


    //============yes버튼 누르면 ajax실행===============

    document.querySelector("#yesButton").addEventListener("click", e => {
        e.preventDefault();


        const registerModal = $("#registerModal");
        const closeModal = $("#closeModal");


        //ajax 할 변수 선언
        const titleInput = document.querySelector("input[name='title']");
        const contentTextarea = document.querySelector("#content");
        const writerInput = document.querySelector("input[name='writer']");


       
        //ajax에 담기
        const lists = document.querySelectorAll(".uploadResult ul li");
        
        console.log("lists");
        
        //DTO에 들어가는거니깐 변수명 맞춰야됨
        //ajax에 담는거는 diaryDTO니깐
        var attachList = [];
        
        lists.forEach(l=>{
        	console.log(l)
        		
        	var tempData = {
        		fileName : l.getAttribute("data-filename"),
        		uploadPath : l.getAttribute("data-path"),
        		uuid : l.getAttribute("data-uuid"),
        		fileType : l.getAttribute("data-type")
        	}
        	//datalist에 ul담기
        	//push는 배열일때
        	attachList.push(tempData);
        	
        }); // list for end
        console.log(attachList);
        
        const data = {title : titleInput.value,
        		content : contentTextarea.value,
        		writer : writerInput.value,
        		attachList: attachList
        		
        }
        
 

      console.log(data);
        
      const jsonStr = JSON.stringify(data);
      
      console.log(jsonStr)


        //ajax return을 json으로 받아옴
        //받아오는거는 모달창 yes버튼 눌렀을때
    const registerResult = registerAjax(jsonStr); // <-- 데이터 받아옴

        registerModal.modal('hide')

        // yes => submit

        //갔다왔으면 이 처리를 해라
        registerResult.then(json => {

        	console.log("---------------------------------------------------------------------")
        	console.log(json)
        	
            action.getAttribute(closeModal.modal('show'));

            setTimeout(function () {
				action.submit();
            }, 1000); 



       })


    }, false)


    //=============목록버튼=====================

    document.querySelector("#listButton").addEventListener("click", e => {
        e.preventDefault();

        console.log(e);
        console.log("=======목록==========");


        action.submit();

    }, false)


    //=============모달창 닫기 버튼 =====================

    document.querySelector("#noButton").addEventListener("click", e => {
        e.preventDefault();

        console.log(e);
        console.log("=======닫기버튼==========");

        const registerModal = $("#registerModal");
        registerModal.modal('hide');


    }, false)

    //============ 첨부파일 보기 ==============
    const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    const maxSize = 5242880;


    

    
    
    function showUploaFile(uploadResultArr) {
    	
    	
    	
    	if(!uploadResultArr || uploadResultArr.length == 0){
    		return;
    	}
    	
    	console.log("== uploadResultArr =="+uploadResultArr);
    	
    	
    	 var uploadResult = document.querySelector(".uploadResult ul");
    	
    	 var str = "";

        //dto의를 루프돌리기
        uploadResultArr.forEach(function (obj) {
        	
        
            if (obj.fileType){

           
                var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

       

                str += "<li data-path='"+obj.uploadPath+"'";
                str += " data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'"
                str + "><div>"
                str += "<span> " + obj.fileName + "</span>";
                str += "<button type='button' id='xUpload' data-file=\'"+fileCallPath+"\' data-type='fileType' class='btn btn-primary btn-fab btn-icon btn-round'><i class='fa fa-times'></i></button><br>";
                str += "<img src='/display?fileName=" + fileCallPath + "'>";
                str += "</div>";
                str + "</li>";

            } else {
 	 
                var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);

                var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");

                str += "<li "
                str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><div>";
                str += "<span> " + obj.fileName + "</span>";
                str += "<button type='button' id='xUpload' data-file=\'"+fileCallPath+"\' data-type='file' class='btn btn-primary btn-fab btn-icon btn-round'><i class='fa fa-times'></i></button><br>";
                str += "<img src='/resources/img/attach.jpg'></a>"
                str += "</div>";
                str + "</li>";

                
             
            }

          
        });

        console.log(uploadResult)
        console.log(uploadResult.innerHTML)
        uploadResult.innerHTML += str
  
    }
    
     //====== 버튼 x버튼 삭제 =====
    document.querySelector(".uploadResult ul").addEventListener("click",function (e) {
        e.preventDefault();
        
     //   document.querySelector("#xUpload").target;
    
       var target = e.target;
     
       console.log("aa"+this.querySelector("li button"));
       
      
        var targetFile =  this.querySelector("li button").getAttribute("data-file");
        console.log(targetFile);
        var type =  this.querySelector("li button").getAttribute("data-type");
   
      //  var targetLi =  this.getAttribute("li");
        var targetLi =  this.querySelector("li");

        const data = {fileName: targetFile, fileType: type}

        console.log(data);
        console.log("li"+targetLi);
    	
    	targetLi.parentNode.removeChild(targetLi);
       fetch("http://localhost:8080/deleteFile",{
        	 method:"post",
        	 //
       
             headers: {'${_csrf.headerName}': '${_csrf.token}', 'Content-Type':  'application/x-www-form-urlencoded;charset=UTF-8'},
             //인코딩된 값으로 줘야됨
         	 body: "fileName=" + data.fileName+"&fileType="+ data.fileType
         	
        }) .then(res => {    	
        	if(res.ok){
        		return res.json();  
        	}	     
     }).then(res=>{
         console.log(res);

     })
   
        
     },false)
    	



    function checkExtension(fileName, fileSize) {

        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            return false;
        }

        if (regex.test(fileName)) {
            alert("해당 종류의 파일을 업로드 할 수 없습니다")
            return false;
        }

        return true;
    }


    document.querySelector("input[type='file']").addEventListener("change", function (e) {
        e.preventDefault();


        console.log(inputFile);
        //2.formData에 넣기
        var files = inputFile.files;
        var uploadResult = document.querySelector(".uploadResult ul");
        console.log("length : " + files.length);

        //3.파일 양식 체크하기
        for (let i = 0; i < files.length; i++) {

            if (!checkExtension(files[i].name, files.size)) {

                return false;
            }

            formData.append("uploadFile", files[i]);
            console.log("=== formData ===="+ formData);

        }
        //for
          fetch("http://localhost:8080/uploadAjax",{
                //첨부파일 데이터는 formdata에 추가한 뒤 ajax를 통해 formdata 자체를 전송  processData contentType false로 해야지 전송됨
                //보낼때는 formdata로 보내고 controller에서 받을때는 MultipartFile타입에 맞게 데이터처리
            	 method:"post",
            	 body:formData,
            	 proceessData:false,
            	 contentType:false,
				 headers: {'${_csrf.headerName}': '${_csrf.token}' },
            	 
            })  .then(res => {
            	
            	if(res.ok){
            		
            		return res.json();
            	}  
         }).then(res=>{
            
        	  showUploaFile(res);
        	 

         })
          
 
       
        
    },false)
    


</script>


<%@include file="../includes/footer.jsp"%>