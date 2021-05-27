<%@ include file="../includes/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- security를 쓰겠다 쓸때  sec라고 이용해서 쓴다 -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<div class="content">
	<div class="row">
		<div class="col-md-12">
		<!-- 	<h3 class="description">36.5℃ 사진관</h3> -->
		</div>
	</div>
</div>




<button class="btn btn-outline-primary btn-round" id="registerButton">
	<i class="fa fa-heart"></i> 등록 </button>

<div class="table-responsive">
	<table class="table">
		<thead class=" text-primary">
			<tr>
				<th class=" text-primary"></th>
				<th>작품</th>
				<th>작가</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<!-- list를 하나씩 돌려야 되니깐 -->
			<c:forEach items="${list }" var="diary">
		
				<tr>
					<td><a class='listDiary' href='<c:out value="${diary.dno}"/>'>${diary.dno}</a></td>
					<td>${diary.title}  [ <c:out value="${diary.replyCount}" /> ]</td>
					<td>${diary.writer}</td>
					<td>${diary.updatedate}</td>
				</tr>
			</c:forEach>
	</table>
</div>

<!-- 페이지 네이션 -->
<ul class="pagination">

	<c:if test="${pageMaker.prev}">
		<li class="page-item"><span class="page-link"
			href="${pageMaker.start -1}" tabindex="-1">Previous</span></li>
	</c:if>

	<!--페이지네이션 첫과 끝을 루프돌음 그 루푸도는 이름음 num  -->
	<c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="num">
		<!--active 클릭했을때 링크상태  -->
		<!--루프 돌았을때랑 내가 클릭한 거랑 같을때 실행해라-->
		<li
			class="page-item ${pageMaker.pageDTO.page == num ? 'active' : '' }">
			<!--누른 번호 페이지로 가라--> <a class="page-link" href="${num}">${num}</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next}">
		<li class="page-item"><a class="page-link"
			href="${pageMaker.end +1}">Next</a></li>
	</c:if>

</ul>
<!-- 등록 버튼 -->

<!-- 검색창 -->
<div class="search">

	<div class="form-row">
		<div class="form-group col-md-4">
			<label for="inputState"></label>
			 <select id="inputState" class="form-control searchType" >	
				<option value = 't' ${pageDTO.type =='t' ? "selected" : ""}>작품</option>
				<option value = 'c' ${pageDTO.type =='c' ? "selected" : ""}>내용</option>
				<option value = 'tc' ${pageDTO.type =='tc' ? "selected" : ""}>작품+내용</option>
			</select>
		</div>
		<div class="form-group col-md-6">
			<label for="inputCity"></label> 
			<input type="text" name="searchKeyword" class="form-control" id="inputCity"
			value="<c:out value= '${pageDTO.keyword}'/>">	
		</div>
		<button type="submit" class="btn btn-primary searchButton" >검색</button>
	</div>
</div>

<!-- 모달창 -->
<div class="modal" tabindex="-1" role="dialog" id="searchModal" >
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>     검색을 해주세요    </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<form class="actionForm" action="/diary/list" method="get">
	<input type="hidden" name="page" value="${pageDTO.page }"> 
	<input type="hidden" name="perSheet" value="${pageDTO.perSheet }">
	<input type="hidden" name="keyword" value="${pageDTO.keyword }">
	<input type="hidden" name="type" value="${pageDTO.type }">
</form>

<script>

const action = document.querySelector(".actionForm");



//페이지네이션 이벤트
document.querySelector(".pagination").addEventListener("click", e =>{
	e.preventDefault();
	
	//페이지 네이션이 여러개니깐 타겟 지정
	const target = e.target;
	console.log(target);
	const pageNum = target.getAttribute("href");
	console.log(pageNum);
	console.log(target);
	
	//클릭한넘버에 값이 없을때 
	if(pageNum == null){
		//return값이 없으니깐 끝!
	//	document.querySelector(".pagination")	
	return;
	
	}
	
	//perSheet는 10이 고정이므로 값을 안가져와도됨
	action.querySelector("input[name='page']").value= pageNum;
	action.submit();
}, false)

//검색창 이벤트
document.querySelector(".searchButton").addEventListener("click", e =>{
	e.preventDefault();
	console.log("===============SEARCH BUTTON=============");
	console.log(e);
	
	//searchType의 select 불러오기
	const searchType = document.querySelector(".searchType");
	//검색타입을 배열로 담음
	const index = searchType.selectedIndex;
	//검색타입을 값 가져오기(옵션값)
	const typeValue = searchType[index].value;
	//키워드 값
	const keyValue = document.querySelector("input[name='searchKeyword']").value
	
	const searchModal = $("#searchModal");
	
	console.log(searchType);
	console.log(typeValue);
	console.log(keyValue);
	
	//키워드값이 없을때 모달창 띄우기
	//데이터가 주고받는게 아니니깐 ajax로 안해도되는거임
	if(keyValue == null || keyValue == ''){
		console.log("===============KEYVALUE EMPTY=============");
		searchModal.modal('show');
		console.log(searchModa);
		return;
	}
	
	
	//옵션값을 pageDTO type에 넣기
	//검색창 input에 있던 값을  pageDTO keyword에 넣기
	action.querySelector("input[name='type']").value= typeValue;
	action.querySelector("input[name='keyword']").value
	= keyValue;
    
	//검색내역이 없을때 모달창 뜨게
	
	
	
	action.submit();
	
	console.log("input[name='type']");
	console.log("input[name='keyword']");
	
	
	
}, false)

//등록버튼 이벤트
document.querySelector("#registerButton").addEventListener("click", e =>{
	e.preventDefault();
	
	console.log(e)
	
	action.setAttribute("action", "/diary/register")
	action.submit();
	
	
	
},false)	
	


//==========게시글 조회=============
//모든 리스트의 조회니깐 querySelectorAll로 해야됨 list의 모든것이 이벤트
//하나만 돌리는게 아니라 전체를 해야되니깐 foreach 돌리는거
document.querySelectorAll(".listDiary").forEach(a=>{
	a.addEventListener("click", function(e){
	e.preventDefault();
	
	
	const dno = e.target.getAttribute("href");
	console.log(dno);
	
     action.setAttribute("action", "/diary/view");
     action.innerHTML += "<input type = 'hidden' name='dno' value='"+dno+"'>";
     action.submit();
     
     
},false) ;    
})	
	

	
	
</script>


<%@include file="../includes/footer.jsp"%>