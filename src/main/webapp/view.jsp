<%@page import="domain.BoardVO"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	BoardVO vo = (BoardVO)request.getAttribute("vo");
%>    
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
	<table class="table">
		<tr>
			<th>제목</th>
			<td><%=vo.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=vo.getContent() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=vo.getWriter() %></td>
		</tr>
	</table>
	<div class="pull-right"><a href="update?num=<%=vo.getNum() %>" class="btn btn-default">글수정</a></div>
</div>
</body>
</html>