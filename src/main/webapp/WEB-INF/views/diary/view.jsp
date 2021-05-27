<%@ include file="../includes/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sec:authentication property="principal" var="pinfo" />
<sec:authorize access="isAuthenticated()">
	<c:if test="${pinfo.username eq diary.writer}">
		<button class="btn btn-outline-primary btn-round" id="deleteButton">
			<i class="fa fa-heart"></i> 삭제
		</button>
		<button class="btn btn-outline-primary btn-round" id="modifyButton">
			<i class="fa fa-heart"></i> 수정
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


<!-- 수정 삭제 목록 버튼 -->
<div class="row">
	>
	<button class="btn btn-outline-primary btn-round" id="modifyButton">
		<i class="fa fa-heart"></i> 수정
	</button>
	<button class="btn btn-outline-primary btn-round" id="deleteButton">
		<i class="fa fa-heart"></i> 삭제
	</button>
	<button class="btn btn-outline-primary btn-round" id="listButton">
		<i class="fa fa-heart"></i> 목록
	</button>
</div>


<!-- input -->
<div class="writebox">
	<div class="form-group">
		<label for="exampleFormControlInput1">작품</label> <input type="text"
			class="form-control" name="title"
			value='<c:out value="${diaryDTO.title }"/>' readonly="readonly">
	</div>


	<div class="form-group">
		<label for="exampleFormControlTextarea1">내용</label>
		<textarea class="form-control inputFileVisible" id="content" rows="3"
			readonly="readonly"><c:out value="${diaryDTO.content }" /></textarea>
	</div>


	<div class="form-group">
		<label for="exampleFormControlInput1">작가</label> <input type="text"
			class="form-control" name="writer"
			value='<c:out value="${diaryDTO.writer }"/>' readonly="readonly">>
	</div>

	<div class="row">
		<div class="uploadResult">
			<ul>

			</ul>

		</div>
	</div>

</div>


<!-- ===========게시글 모달창 ==============-->
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

<!--================= 이중모달 확인창====================== -->
<div class="modal" tabindex="-1" role="dialog" id="closeModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>삭제 되었습니다</p>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>


<!-- ============댓글 수정확인버튼 ========================== -->
<div class="modal" tabindex="-1" role="dialog" id="replyregisterModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<p>등록되었습니다</p>
			</div>
		</div>
	</div>
</div>


<!-- 댓글창 -->
<div class="table-responsive">
	<table class="table">
		<thead class=" text-primary">
			<tr>
				<th class="text-">댓글</th>

			</tr>
		</thead>

		<!-- 댓글 담을 곳 -->


		<tbody class="reply-list">

		</tbody>


	</table>
</div>


<!-- ======댓글 수정 삭제 모달창 ======= -->
<div class="modal fade" id="replyModal" tabindex="-1" role="">
	<div class="modal-dialog modal-login" role="document">
		<div class="modal-content">
			<div class="card card-signup card-plain">

				<div class="modal-body">

					<p class="description text-center">댓글</p>
					<div class="card-body">

						<input type="hidden" class="form-control" id="backup"
							name="replyRno">
						<div class="form-group bmd-form-group">
							<div class="input-group">

								<input type="text" class="form-control" id="backup"
									name="updateReply">
							</div>
						</div>
						<div class="form-group bmd-form-group">
							<div class="input-group">
								<input type="text" class="form-control" id="backup2"
									name="updateNickname">
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer justify-content-center">
					<button class="btn btn-primary btn-round" id="replyDeleteButton">삭제</button>
					<button class="btn btn-primary btn-round" id="replyModifyButton">수정</button>
					<button class="btn btn-primary btn-round" id="xxx">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 이미지 모달 -->
<div class="modal imgModal" tabindex="-1" role="dialog"">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">사진</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>



<!-- 댓글 페이지네이션 창 -->
<nav aria-label="Page navigation example" class="replyPageNation">

</nav>


<!-- 댓글입력 창 -->
<div class="input-group mb-3">
	<input type="text" class="form-control" placeholder="댓글내용"
		aria-label="Recipient's username" aria-describedby="button-addon2"
		name="replyBox"> <input type="hidden" class="form-control"
		aria-label="Recipient's username" aria-describedby="button-addon2"
		name="nickname" readonly="readonly">
	<sec:authorize access="isAuthenticated()">
		<button class="btn btn-outline-primary mb-0" type="button"
			id="replyButton">댓글등록</button>
	</sec:authorize>
</div>


<form class="actionForm" action="/diary/list" method="get">
	<input type="hidden" name="dno" value="${diaryDTO.dno }"> <input
		type="hidden" name="page" value="${pageDTO.page }"> <input
		type="hidden" name="perSheet" value="${pageDTO.perSheet }">

</form>

<script>


var csrfHeaderName = '${_csrf.headerName}';
var csrfTokenValue = '${_csrf.token}';


var updateNickname = null;
var nickname = null;

<sec:authorize access="isAuthenticated()">

nickname = '<sec:authentication property="principal.username"/>';
updateNickname = '<sec:authentication property="principal.username"/>';
</sec:authorize>



    var action = document.querySelector(".actionForm");

    var dnoInput = action.querySelector("input[name='dno']").value

    var dno = action.querySelector("input[name='dno']").value
    
  
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
                    str += "<img src='/display?fileName=" + fileCallPath + "'>";
                    str += "</div>";
                    str + "</li>";

                } else {

                    str += "<li data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.image + "'><div>";
                    str += "<span> " + obj.fileName + "</span><br/>";
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
        var target = e.target;
   
        console.log(target);
       
        
        var li = this.querySelector(".uploadResult ul li");
        console.log("li"+li);
      
        var path = encodeURIComponent(li.getAttribute("data-path")+"/"+li.getAttribute("data-uuid")+"_"+li.getAttribute("data-fileName"));
        
        li.getAttribute("data-type")+showImg(path.replace(new RegExp(/\\/g),"/"));
        			
      
    
    },false);
        

    function showImg(fileCallPath){
    	  	
        var im = $(".imgModal");
        im.modal('show');
       
        
        
        console.log(fileCallPath);
        
        
        $(".imgModal .modal-body").html("<img style='width:100%' src='/display?fileName=" +fileCallPath+ "'>");  
        
         }     
    
    
    document.querySelector(".imgModal").addEventListener("click", function (e){
   	 e.preventDefault();
   	 
   	var im = $(".imgModal");
   	
   	im.modal('hide');
    
    },false);
  
        
    //키 : 값
    var mypage = {dno: dnoInput, page: 1};

    //==============댓글삭제ajax==============//

    function replyDelete(reply) {
        //data = rno 니깐 url에 data 넣으면 된다
        return fetch("http://localhost:8080/replies/" + reply, {
            method: 'delete',
            headers: {'${_csrf.headerName}': '${_csrf.token}', 'Content-Type': 'application/json' },
            body: JSON.stringify(reply)


        })
            // controller return 받아옴
            .then(res => {

                if (!res.ok) {
                    throw new Error(res);
                }

                console.log("=========fetch=============");
                console.log(res);
                return res.json();
                //produces = json이니깐

            })
            .catch(res => {
                console.log("=========catch=============");
                return res;
            })
    }


    //==============댓글수정ajax==============//

    function replyUpdate(reply) {
        //data = rno 니깐 url에 data 넣으면 된다
        return fetch("http://localhost:8080/replies/" + reply.rno, {
            method: 'put',
            headers: {'${_csrf.headerName}': '${_csrf.token}', 'Content-Type': 'application/json' },
            body: JSON.stringify(reply)


        })
            // controller return 받아옴
            .then(res => {

                if (!res.ok) {
                    throw new Error(res);
                }

                console.log("=========fetch=============");
                console.log(res);
                return res.json();
                //produces = json이니깐

            })
            .catch(res => {
                console.log("=========catch=============");
                return res;
            })
    }



    //==============댓글 수정 삭제 모달창 띄우기==============//
    //================================================//
    //==========새로 갱신 되니깐 삭제 수정을 따로 해야됨=========//
    

 
     document.querySelector(".reply-list").addEventListener("click", function (e) {
    	
    	e.stopPropagation(); 
    	 
        var target = e.target;

        //replyDiv

       //이벤트 확인
        console.log("target : ");

        //모달 창 띄우기
        const replyModal = $("#replyModal");
        
       
        
        const tr = target.closest("tr")
        const trHTML = tr.innerHTML;
        
        //console.dir(tr);
        
        const tds = tr.querySelectorAll("td");
        const td = tr.querySelector("td");
        
        
        const replyContent = tr.querySelector(".replyContent");
        const replyContentVal = replyContent.getAttribute("data-replybox");
        console.log("------------------replyContentVal-----------------" +replyContentVal);
        
        const replyer = tr.querySelector(".text-right");
        const replyerVal = replyer.getAttribute("data-replyer");
        console.log("------------------replyerVal-----------------" +replyerVal);
        
        
        
        
        console.log(tds[0].innerHTML);
        console.log(tds[1].innerHTML);
        console.log(tds[2].innerHTML);
        console.log("-----------------------------------");
        
        const replyRno = tr.getAttribute("data-rno");
       // const replyRno = tr.getAttribute("data-rno");
       

 
        const rno  = document.querySelector("input[name='replyRno']").value = replyRno;
        const updateReply  = document.querySelector("input[name='updateReply']").value = replyContentVal;
    
     //   const nickname = document.querySelector("input[name='updateNickname']").value = tds[2].innerHTML;
         const nickname = document.querySelector("input[name='updateNickname']").value = replyerVal;
 
       
          if(nickname !=updateNickname){
        	
        	
        	  replyModal.modal('hide');
        	  alert("사용자가 아닙니다"); 
    /* 	   action.setAttribute("action", "/diary/view");
    	   action.submit() ; */
    	  
    	   return  ;  
    	  
     	 }else {
     		replyModal.modal('show');
     	 }
   
          
    }, false) 
 

    //======================댓글 삭제====================//
    //================================================//
    document.querySelector("#replyDeleteButton").addEventListener("click", function (e) {
        e.preventDefault();

        //이벤트 확인
        console.log(e);

        const replyModal = $("#replyModal");


        const closeModal = $("#closeModal");


        const reply  = document.querySelector("input[name='replyRno']").value;
        

        console.log("==replyRno===> " + reply);
        replyModal.modal('hide');

        const replyDeleteResult = replyDelete(reply);

        closeModal.modal('show');
        replyDeleteResult.then(json => {

            setTimeout(function () {


                showReplyList(mypage);
                closeModal.modal('hide');
            }, 1800);


        })


    }, false)


    //======================댓글 수정====================//
    //================================================//
    document.querySelector("#replyModifyButton").addEventListener("click", function (e) {
        e.preventDefault();

        //이벤트 확인
        var target = e.target;

        const replyModal = $("#replyModal");


        const replyregisterModal = $("#replyregisterModal");

     
        const rno  = document.querySelector("input[name='replyRno']").value;
       
        console.log("====rno=====" + rno);
    
 
        const replyInput = document.querySelector("input[name='updateReply']");
        const nicknameInput = document.querySelector("input[name='updateNickname']");

        const reply = {

            rno: rno,
            reply: replyInput.value,
            replyer: nicknameInput.value
        }


        console.log("==reply===> " + reply);
        replyModal.modal('hide');

        const updateResult = replyUpdate(reply);

        replyregisterModal.modal('show');

         updateResult.then(json => {

            setTimeout(function () {


                showReplyList(mypage);
                replyregisterModal.modal('hide');


            }, 1000);


        })

         
    }, false)


    //===========삭제ajax=================

    function deleteAjax(data) {
        console.log("==========registerAjax==========")

        //controller호출
        return fetch("/diary/delete", {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        })
            // controller return 받아옴
            .then(res => {

                if (!res.ok) {
                    throw new Error(res);
                }

                //ㅏ성공
                console.log("=========fetch=============");
                console.log(res);
                return res.text();
                //응답을 받아옴
                //produces = { "text/plain" }) text니깐  제대로 받아옴

            })
            .catch(res => {
                console.log("=========catch=============");
                return res;
            })
    }


    //=============삭제버튼 누르면 모달창뜨게함================

    document.querySelector("#deleteButton").addEventListener("click", e => {
        e.preventDefault();

        //모달창
        const deleteModal = $("#deleteModal");
        deleteModal.modal('show')


    }, false)


    //============삭제 모달에서 yes버튼 누르면 ajax실행===============

    document.querySelector("#yesButton").addEventListener("click", e => {
        e.preventDefault();


        const deleteModal = $("#deleteModal");
        const closeModal = $("#closeModal");


        //ajax 넣을 data
        const dnoInput = document.querySelector("input[name='dno']");


        //ajax에 담기
        const data = {dno: dnoInput.value}


        console.log(data);

        deleteModal.modal('hide');

        //ajax return을 json으로 받아옴
        //받아오는거는 모달창 yes버튼 눌렀을때
        //const deleteResult -->데이터 받아온애 = deleteAjax(data); ---->데이터 보낸애
        const deleteResult = deleteAjax(data);
        closeModal.modal('show');

        //갔다왔으면 이 처리를 해라
        //json으로 받아왔으니깐 처리해라
        deleteResult.then(json => {


            setTimeout(function () {


                action.submit();
            }, 1800);


        })


    }, false)


    //=============목록버튼=====================

    document.querySelector("#listButton").addEventListener("click", e => {
        e.preventDefault();

        console.log(e);
        console.log("=======목록==========");


        action.submit();

    }, false)


    //=============모달창 닫기 버튼=====================

    document.querySelector("#noButton").addEventListener("click", e => {
        e.preventDefault();

        console.log(e);
        console.log("=======닫기버튼==========");

        const deleteModal = $("#deleteModal");
        deleteModal.modal('hide');


    }, false)

    //=========== 수정버튼==============

    document.querySelector("#modifyButton").addEventListener("click", e => {
        e.preventDefault();


        action.setAttribute("action", "/diary/modify");
        action.submit();


    }, false)

    //==============댓글======================

    //===========댓글 ajax 가져오기==============	

    function getReplyList(reply) {
        return fetch("http://localhost:8080/replies/pages/" + reply.dno + "/" + reply.page, {
            method: 'get',
            headers:{'Content-Type': 'application/json'}


        })
            // controller return 받아옴
            .then(res => {

                if (!res.ok) {
                    throw new Error(res);
                }

                console.log("=========fetch=============");
                console.log(res);
                return res.json();
                //jsno ->return 값

            })
            .catch(res => {
                console.log("=========catch=============");
                return res;
            })
    }


    function showReplyList(mypage) {

        //ajax return을 json으로 받아옴
        //받아오는거는 모달창 yes버튼 눌렀을때
    
        var reply = mypage;
        console.log("rreply-->" + reply);
        const replyListResult = getReplyList(reply);
        
        console.log("replyListResult-->" + replyListResult);

        //갔다왔으면 이 처리를 해라
        replyListResult.then(json => {
            //json에는 댓글(데이터) 담겨져 있음


            var list = json['replyList'];
            var rlist = document.querySelector(".reply-list");
            rlist.innerHTML = ''; // innerHTML 비우기

            for (var i = 0; i < list.length; ++i) {


                rlist.innerHTML += '<tr class="replyData"  data-rno ="' + list[i].rno + '"  data-dno ="' + list[i].dno + '" ><td class="replyBox">' + list[i].rno + '</td>' +
                    '<td  class="replyContent" data-replybox ="' +  list[i].reply + '"  >' + list[i].reply + '</td>' +
                    '<td class="text-right" id="replyer" data-replyer ="' + list[i].replyer + '" >' + list[i].replyer + '</td>' +
                    '<td class="text-right" id="replyupdateDate" data-reply ="' + list[i].updateDateStr + '" >' + list[i].updateDateStr + '</td></tr>'


            }
        })

    }


    showReplyList(mypage);

    //}

    //===========리플 페이지===========
    function replyPage(mypage) {


        var reply = mypage;
        console.log("replyPage : "+ reply);

        //ajax return을 json으로 받아옴
        //받아오는거는 모달창 yes버튼 눌렀을때
        const replyListResult = getReplyList(reply);

        replyListResult.then(json => {

            var pageMaker = json['pageMaker'];


            var pageNav = document.querySelector(".replyPageNation");
            var length = pageMaker.start - pageMaker.end;
            var pageStr = "<ul class='pagination'>";

            if (pageMaker.prev) {
                pageStr += "<li class='page-item'>" +
                    "<a class='page-link' href='" + (pageMaker.start - 1) + "' aria-label='Previous'><i class='fa fa-angle-left'></i><span class='sr-only'>Previous</span></a></li>";
            }

            for (var i = pageMaker.start; i < pageMaker.end + 1; ++i) {
                pageStr += "<li class='page-item " + (pageMaker.pageDTO.page == i ? 'active' : '') + " ''><a class='page-link' href='" + i + "'>" + i + "</a></li>"
            }

            if (pageMaker.next) {
                pageStr += "<li class='page-item'><a class='page-link' href='" + (pageMaker.end + 1) + "' aria-label='Next'><i class='fa fa-angle-right'></i><span class='sr-only'>Next</span></a></li>"

            }


            pageStr += "</ul>";


            pageNav.innerHTML = pageStr;


        })


    }

    replyPage(mypage);


    //리플 페이지 네이션 이벤트


    document.querySelector(".replyPageNation").addEventListener("click", function (e) {
        e.preventDefault();

        const target = e.target;
        console.log(target);

        const pageNum = target.getAttribute("href");

        console.log("pageNum-->" + pageNum)
        if (pageNum == null) {

            return;
        }
        //  
        //   const page =replyPage().getAttribute("Inputpage");
        //  console.log(page);

        replyPage(mypage);

        mypage['page'] = pageNum;
        var result = showReplyList(mypage);

    }, false);



    
    
    //======댓글 등록 ajax================ 
    function add(data) {
        return fetch("http://localhost:8080/replies/new", {
            method: 'post',
       	 proceessData:false,
    	 contentType:false,
		 headers: {'${_csrf.headerName}': '${_csrf.token}', 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
         /*    body: "fileName=" + data.fileName+"&fileType="+ data.fileType */


        })
            // controller return 받아옴
            .then(res => {
            	console.log(res)
                 console.log(csrfHeaderName);
    console.log(csrfTokenValue);
                if (!res.ok) {
                    throw new Error(res);
                }

                console.log("=========fetch=============");
                console.log(res);
                return res.json();
                //produces = json이니깐

            })
            .catch(res => {
                console.log("=========catch=============");
                return res;
            })
    }


    //========댓글 등록===============
    //bno를 가져와야되니깐 

    document.querySelector("#replyButton").addEventListener("click", function (e) {
        e.preventDefault();

        console.log(e);


        //모달창 
        const replyregisterModal = $("#replyregisterModal");
        replyregisterModal.modal('show');


        //data에 담을것
        const replyBox = document.querySelector("input[name='replyBox']").value
        const replyerValue = document.querySelector("input[name='nickname']").value = nickname

        const dnoValue = action.querySelector("input[name='dno']").value
        console.log(replyerValue);

        const data = {reply: replyBox, replyer: replyerValue, dno: dnoValue}
        console.log("data"+ data)

        const addResult = add(data);

         addResult.then(json => {

            setTimeout(function () {

                replyregisterModal.modal('hide');
                showReplyList(mypage);
            }, 1500);


        })  

 
    }, false);


    document.querySelector("#xxx").addEventListener("click", function (e) {
        e.preventDefault();

        var replyModal = $("#replyModal");
        replyModal.modal('hide');


    }, false);


</script>


<%@include file="../includes/footer.jsp"%>