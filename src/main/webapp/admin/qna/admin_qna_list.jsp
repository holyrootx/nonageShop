<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Q&A List</title>
</head>
<body>
   <!-- Admin Q&A 페이지 시작 -->
<article>
    <h1> 고객들의 민원 사항을 확인하는 곳 admin </h2>
      <table id="cartList">
      <tr>
        <th>번호(답변여부)</th> <th>제목</th> <th>작성자</th> <th>작성일</th>
      </tr>

<c:forEach items = "${adminQnaList}" var="adminQnaVO">
      <tr>
        <td>
            ${adminQnaVO.qseq}
            <c:choose>
                <c:when test="${adminQnaVO.rep==1}"> (미처리)</c:when>
                <c:when test="${adminQnaVO.rep==2}"> (답변완료)</c:when>
            </c:choose>
         </td>
        <td><a href="NonageServlet?command=admin_qna_detail&qseq=${adminQnaVO.qseq}">${adminQnaVO.subject}</td>
        <td>${adminQnaVO.id}</td>
        <td><fmt:formatDate value="${adminQnaVO.indate}" type="date"/></td>
      </tr>
      </c:forEach>
      </table>
      <div class="clear"></div>
  </article>



    <!-- Admin Q&A 페이지 끝 -->
</body>
</html>