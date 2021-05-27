<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8" />
<title>Insert title here</title>
</head>
<body>

<form action="uploadAction" method="post" enctype="multipart/form-data">
<input type="file" name='uploadFile' multiple>
<button>sub</button>

</form>

</body>
</html>