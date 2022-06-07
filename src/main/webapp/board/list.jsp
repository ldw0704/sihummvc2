<%@page import="domain.BoardInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="domain.BoardVO"%>
<%@page import="java.util.Collection"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BoardInfo boardInfo = (BoardInfo) request.getAttribute("boardInfo");
Collection<BoardVO> list = boardInfo.getList();
int totalRow = boardInfo.getTotalRow();

int totalNum = (Integer) request.getAttribute("totalNum");
int pageNum = (Integer) request.getAttribute("pageNum");
int pageRow = (Integer) request.getAttribute("pageRow");

int pagingNum = (Integer) request.getAttribute("pagingNum");
int startNum = (Integer) request.getAttribute("startNum");

String keyWordTitle = (String) request.getAttribute("keyWordTitle");
String keyWordContent = (String) request.getAttribute("keyWordContent");
String keyWordWriter = (String) request.getAttribute("keyWordWriter");

int lastPage = totalRow/pageRow+((totalRow%pageRow == 0)?0:1);
if(pageNum > lastPage || pageNum < 1){
	//response.sendRedirect("list");
}
String field = (String) request.getAttribute("field");


//Collection<BoardVO> list = (Collection)request.getAttribute("list");
//int totalRow = (Integer)request.getAttribute("totalRow");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div>
			<div class="pull-left">전체글수 : <%=totalRow%></div>
			<div class="pull-right" style="width:350px;">
				<form>					
					<div>제목<input type="text" name="keyWordTitle" value="<%=keyWordTitle %>" class="form-control" style="display:inline-block; width:50%"></div>
					<div>내용<input type="text" name="keyWordContent" value="<%=keyWordContent %>" class="form-control" style="display:inline-block; width:50%"></div>
					<div>작성자<input type="text" name="keyWordWriter" value="<%=keyWordWriter %>" class="form-control" style="display:inline-block; width:50%"></div>
					<button class="btn btn-default">검색</button>
				</form>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>					
					<th>작성자</th>
					<th>첨부파일</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<%
				Iterator<BoardVO> it = list.iterator();
				while (it.hasNext()) {
					BoardVO vo = it.next();
				%>
				<tr>
					<td><%=vo.getNum()%></td>
					<td><a href="view?num=<%=vo.getNum()%>"><%=vo.getTitle()%></a></td>
					<td><%=vo.getWriter()%></td>
				<%
					if(vo.getRealFileName()!=null)
					{
				%>
					<td><img src='clip.webp' style='width:25px;'/></td>
				<%
					}else{
				%>	
					<td></td>
				<%	
					}				
				%>		
					<td><%=vo.getWriterDate()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<nav style="text-align: center;">
		
			<ul class="pagination">
				<%
				if (pageNum == 1) {
				%>
				<li class="disabled">
					<span aria-hidden="true">&laquo;</span>
				</li>
				<%
				} else {
				%>
				<li>
					<a href="?pageNum=<%=pageNum-1 %>&keyWordTitle=<%=keyWordTitle%>&keyWordContent=<%=keyWordContent%>&keyWordWriter=<%=keyWordWriter%>" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<%
				}
				%>

				<%
				for(int i = startNum; i <= (startNum+pagingNum)-1; i++){
					if(i > lastPage) break;
					if(pageNum == i){
				%>
				<li class="active"><a><%=i%></a></li>
				<%		
					} else {
				%>
				<li><a href="?&pageNum=<%=i%>&keyWordTitle=<%=keyWordTitle%>&keyWordContent=<%=keyWordContent%>&keyWordWriter=<%=keyWordWriter%>"><%=i%></a></li>
				<%		
					}
				}
				%>

				<%
				if(lastPage == pageNum){
				%>
				<li class="disabled">
					<span aria-hidden="true">&raquo;</span>
				</li>
				<%
				} else {
				%>
				<li>
					<a href="?pageNum=<%=pageNum+1 %>&keyWordTitle=<%=keyWordTitle%>&keyWordContent=<%=keyWordContent%>&keyWordWriter=<%=keyWordWriter%>" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
				<%
				}
				%>
			</ul>
		</nav>
		<div class="pull-right">
			<a href="writer" class="btn btn-default">글쓰기</a>
		</div>
	</div>
</body>
</html>
