<%@ page import="com.koreait.board.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO)request.getAttribute("data");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수정</title>
</head>
<body>
    <h1>글 수정</h1>
    <form action="/mod" method="post">
        <input type="hidden" name="iboard" value="<%=vo.getIboard()%>">
        <div>
            <input type="text" name="title" value="<%= vo.getTitle()%>">
        </div>
        <div>
            <textarea name="ctnt" placeholder="내용"><%= vo.getCtnt()%></textarea>
        </div>
        <div>
            <input type="text" name="writer" placeholder="글쓴이" value="<%= vo.getWriter()%>">
        </div>
        <div>
            <input tyep="submit" value="저장">
            <input type="reset" value="초기화">
        </div>

    </form>
</body>
</html>