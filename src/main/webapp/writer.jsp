<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<form method="post" action="writer">
		<table class="table">
			<tr>
				<th>제목</th>
				<td><input name="title" class="form-control"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" class="form-control"></textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input name="writer" class="form-control"></td>
			</tr>
		</table>
		<div class="pull-right"><button class="btn btn-default">글쓰기</button></div>
	</form>
</div>
</body>
</html>