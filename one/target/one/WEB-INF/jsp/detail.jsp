<%@ page import="com.koreait.board.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO)request.getAttribute("data2");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글정보</title>
</head>
<body>

        <div>제목 : <%= vo.getTitle() %> </div>
        <div>작성자 : <%= vo.getWriter() %> </div>
        <div>작성일시 :<%= vo.getRdt() %> </div>
        <div> <%= vo.getCtnt() %> </div>
        <div>
            <a href="/del?iboard=<%= vo.getIboard() %>">
                <input type="button" value="삭제">
            </a>
            <a href="/mod?iboard=<%= vo.getIboard() %>">
                <input type="button" value="수정">
            </a>
            <a href="/list"><input type="button" value="리스트"></a>
        </div>
</body>
</html>
