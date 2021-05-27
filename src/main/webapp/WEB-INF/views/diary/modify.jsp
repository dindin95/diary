<%@ include file="../includes/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<sec:authentication property="principal" var="pinfo" />
<sec:authorize access="isAuthenticated()">
	<c:if test="${pinfo.username eq diary.writer}">
		<button class="btn btn-outline-primary btn-round" id="modifyButton">
			<i class="fa fa-heart"></i> 수정</button>
		<button class="btn btn-outline-primary btn-round" id="modifyButton">
		<i class="fa fa-heart"></i> 수정완료
	</button>
	</c:if>
</sec:authorize>



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
			class="form-control" name="title"
			value='<c:out value="${diaryDTO.title }"/>'>
	</div>
	<div class="form-group">
		<label for="exampleFormControlTextarea1">내용</label>
		<textarea class="form-control" id="textarea" rows="3"><c:out value="${diaryDTO.content }"/></textarea>
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">작가</label> <input type="text"
			class="form-control" name="writer"
			value='<c:out value="${diaryDTO.writer }"/>'>>
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


<!-- =================수정완료 뒤로가기 버튼================== -->
<div class="row">
	>
	<button class="btn btn-outline-primary btn-round" id="modifyButton">
		<i class="fa fa-heart"></i> 수정완료
	</button>
	<button class="btn btn-outline-primary btn-round" id="backButton">
		<i class="fa fa-heart"></i>뒤로가기
	</button>
</div>




<!-- 모달창 -->
<div class="modal" tabindex="-1" role="dialog" id="modifyModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>수정 하시겠습니까</p>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary btn-round" id="yesButton">네</button>
				<button class="btn btn-primary btn-round" id="noButton">아니오</button>
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
				<p>수정 되었습니다</p>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary btn-round" id="xButton">닫기</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" role="dialog" id="deleteModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>삭제하시겠습니까</p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary btn-round" id="yesButton">네</button>
                <button class="btn btn-primary btn-round" id="noButton">닫기</button>
            </div>
        </div>
    </div>
</div>

<form class="actionForm" action="/diary/list" method="get">
	<input type="hidden" name="dno" value="${diaryDTO.dno }"> <input
		type="hidden" name="page" value="${pageDTO.page }"> <input
		type="hidden" name="perSheet" value="${pageDTO.perSheet }">
	<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">
</form>

<script>


var csrfHeaderName = '${_csrf.headerName}';
var csrfTokenValue = '${_csrf.token}';


const action = document.querySelector(".actionForm");

var dno = action.querySelector("input[name='dno']").value


var inputFile = document.querySelector("input[name='uploadFile']");
var formData = new FormData();

//============파일 수정================
	
	  function dnoArr(arr) {

        var str = "";

        //get으로 가져오니깐 body하고 heard안붙여도됨 파라미터에 dno있어서 dno=
        fetch("http://localhost:8080/diary/getAttachList?dno=" + dno, {
            method: "get"
        }).then(arr => {
            if (!arr.ok) {

            }
            return arr.json();
        }).then(arr => {
            console.log(arr);
            arr.forEach(function (obj) {
                console.log(obj)

                if (obj.fileType) {
                    console.log("obj" + obj.fileType)
                    var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

                    str += "<li data-path='" + obj.uploadPath + "'data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'><div>"
                    str += "<span> "+ obj.fileName+"</span>";
                    str += "<button type='button' id='xUpload' data-file=\'"+fileCallPath+"\' data-type='fileType' class='btn btn-primary btn-fab btn-icon btn-round'><i class='fa fa-times'></i></button><br>";
                    str += "<img src='/display?fileName=" + fileCallPath + "'>";
                    str += "</div>";
                    str + "</li>";

                } else {

                    str += "<li data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'><div>";
                    str += "<span> " + obj.fileName + "</span><br/>";
                    str += "<button type='button' id='xUpload' data-file=\'"+fileCallPath+"\' data-type='fileType' class='btn btn-primary btn-fab btn-icon btn-round'><i class='fa fa-times'></i></button><br>";
                    str += "<img src='/resources/img/attach.jpg'></a>"
                    str += "</div>";
                    str + "</li>";

                }
            })

            document.querySelector(".uploadResult ul").innerHTML += str
            //dto의를 루프돌리기


        });

    }

    dnoArr(dno);
    
    //파일 
    document.querySelector(".uploadResult ul").addEventListener("click", function (e){
    	 e.preventDefault();

        var targetFile =  this.querySelector("li div button").getAttribute("data-file");
        var targetLi =  this.querySelector("li");
        console.log("li"+targetLi);
        
         
        if(confirm("삭제하시겠습니까")){
        	
        	targetLi.parentNode.removeChild(targetLi);
        }
    
    },false);
        
    const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    const maxSize = 5242880;
    
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

        }//for

        
             fetch("http://localhost:8080/uploadAjax",{
                //첨부파일 데이터는 formdata에 추가한 뒤 ajax를 통해 formdata 자체를 전송  processData contentType false로 해야지 전송됨
                //보낼때는 formdata로 보내고 controller에서 받을때는 MultipartFile타입에 맞게 데이터처리
            	 method:"post",
            	 body:formData,
            	 proceessData:false,
            	 contentType:false,
				 headers: {'${_csrf.headerName}': '${_csrf.token}' },
				 
            }) .then(res => {
            	if(res.ok){
            	 	return res.json();
            	}

         }).then(res=>{
             console.log(res);  
        	  showUploaFile(res);

         })
         
 
       
        
    },false)
    
  function showUploaFile(uploadResultArr) {
    	
    	if(!uploadResultArr || uploadResultArr.length == 0){
    		return;
    	}
    	
    	console.log("== uploadResultArr =="+uploadResultArr);
    	console.dir(uploadResultArr);
    	
    	 var uploadResult = document.querySelector(".uploadResult ul");
    	
    	 var str = "";

        //dto의를 루프돌리기
        uploadResultArr.forEach(function (obj) {
        	console.log(obj)
        	
        	// obj.
            if (obj.fileType){

            	 console.log("OBJ"+obj.fileType)
                var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

                console.log("fileCallPath")
                console.log(fileCallPath)

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
        
        uploadResult.innerHTML += str;
    }
//===========수정ajax=================
	
function modifyAjax(data){
	console.log("==========modifyAjax==========")
	
	//controller호출
	return fetch("/diary/modify",{
		  method:'post',
		   proceessData:false,
      	    contentType:false,
      	  headers: {'${_csrf.headerName}': '${_csrf.token}', 'Content-Type': 'application/json'},
	  //    headers: {'Content-Type': 'application/json'},
	      body: data
	   })	
	   // controller return 받아옴
	   .then(res =>{
	      
	      if(!res.ok){
	         throw new Error(res);
	      }
	      
	      console.log("=========fetch=============");
	      console.log(res);
	      return res.text();
	      //produces = { "text/plain" }) text니깐  제대로 받아옴
	      
	   })
	   .catch(res =>{
	      console.log("=========catch=============");
	      return res;
	   })
	}


//=============수정 누르면 모달창뜨게함================
	
document.querySelector("#modifyButton").addEventListener("click", e=>{
	e.preventDefault();
	
	//모달창
	const modifyModal = $("#modifyModal");
	modifyModal.modal('show');
	

    console.log(modifyModal);
	
}, false)



//============수정확인 모달에서 yes버튼 누르면 ajax실행===============
	
document.querySelector("#yesButton").addEventListener("click", e=>{
	e.preventDefault();
	
	
	const modifyModal = $("#modifyModal");
	const closeModal = $("#closeModal");
	
   

	//ajax 넣을 data
	const dnoInput = action.querySelector("input[name='dno']");
	const titleInput = document.querySelector("input[name='title']");
	const textareaInput = document.querySelector("#textarea");
	const writerInput = document.querySelector("input[name='writer']");
	
	  const lists = document.querySelectorAll(".uploadResult ul li");
      
      console.log("lists" +lists);
      
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

	//ajax에 담기
	const data = {dno: dnoInput.value,
			      title: titleInput.value,
			      content: textareaInput.value,
			      writer: writerInput.value,
			      attachList:attachList
			      }
			 

	console.log(data);
      console.dir("lists"+lists);

      
	   
    const jsonStr = JSON.stringify(data);
	
	
	//ajax return을 json으로 받아옴
	//받아오는거는 모달창 yes버튼 눌렀을때
	const modifyResult = modifyAjax(jsonStr);
	

	
	modifyModal.modal('hide');
	//갔다왔으면 이 처리를 해라
 	modifyResult.then(json =>{
		console.log(modifyResult);
	//완료 모달
	action.getAttribute(closeModal.modal('show'));
	
	setTimeout(function (){
		
		action.setAttribute("action","/diary/view");
		action.submit();
		
	},1000);
	       
	
	})  
	

	
}, false)

	
	
//=============모달창 닫기 버튼=====================
	
document.querySelector("#noButton").addEventListener("click", e=>{
	e.preventDefault();
	
	console.log(e);
	console.log("=======닫기버튼==========");
	
	const modifyModal = $("#modifyModal");
	modifyModal.modal('hide');
	

	}, false)
	
//===============수정완료 이중모달창 닫기 버튼==============
	
document.querySelector("#xButton").addEventListener("click", e=>{
	e.preventDefault();
	
	
		action.setAttribute("action","/diary/view");
		action.submit();
		
		
	},false)
	

	
//===============뒤로가기 버튼==============
document.querySelector("#backButton").addEventListener("click", e=>{
	e.preventDefault();

	action.setAttribute("action","/diary/view");
	action.submit();
		
		
	},false)
	
	
	
	
	
</script>




<%@include file="../includes/footer.jsp"%>