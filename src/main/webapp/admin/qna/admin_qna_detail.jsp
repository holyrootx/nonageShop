<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
   <!-- Admin Q&A Detail 페이지 시작 -->
<article>
    <h1> admin Detail </h2>
    <form name="formm" method="post">
      <table id="cartList">
      <tr>
        <th scope="row">제목</th>
        <td>${adminQnaVO.subject}</td>
      </tr>
      <tr>
        <th scope="row">등록일</th>
        <td><fmt:formatDate value="${adminQnaVO.indate}" type="date"/></td>
      </tr>
      <tr>
        <th scope="row">내용</th>
        <td>${adminQnaVO.content}</td>
      </tr>
      </table>
      <div class="clear"></div>
    </form>
  </article>
  <c:choose>
      <c:when test="${adminQnaVO.rep==1}">
        <form action="NonageServlet?command=admin_qna_comment" method="post">
            <table>
                <textarea name="reply" minlength="1"></textarea>
                <input type="hidden" name="qseq" value="${adminQnaVO.qseq}">
                <input type="submit" value="저장">

            </table>
        </form>
      </c:when>
      <c:when test="${adminQnaVO.rep==2}">
          <table>
            <tr>
              <th scope="row">댓글</th>
              <td>${adminQnaVO.reply}</td>
            </tr>
          </table>
      </c:when>
  </c:choose>
    <input type="button" value="목록" onclick="location.href='NonageServlet?command=admin_qna_list'">

    <!-- Admin Q&A Detail 페이지 끝 -->
</body>
</html>