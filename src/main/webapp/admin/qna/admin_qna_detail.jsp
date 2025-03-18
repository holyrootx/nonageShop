<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style><%@include file="/css/admin_design_head_footer.css"%></style>
    <style><%@include file="/css/default_design.css"%></style>
    <title>Document</title>
    <style>
    .qna-list {
    }
    .comment-table{
        margin-top: 28px;
    }
    .qna-list-th-height{
        width: 180px;
    }
    .article-intro{
        margin: 8px 0px;
    }
    .form-width{
        width: 1000px;
    }
    .textarea-design{
        height: 68px;
        width: 860px;
        font-size: 20px;
    }
    .flex-container-comment{
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 12px auto;
    }
    .btn-design-comment{
        height: 68px;
    }
    </style>


</head>
<body>
<%@ include file="/admin/admin_header.html" %>
<!-- Admin Q&A Detail 페이지 시작 -->
<div class="main-container">
    <div class="flex-container-center">
        <div class="left-navbar">
            <h2 class="navbar-intro">Admin Setting</h2>
            <ul class="navbar-list-wrapper">
                <li class=""><a class="navbar-list-text-style" href="NonageServlet?command=admin_product_list">상품리스트</a></li>
                <li class=""><a class="navbar-list-text-style" href="#">주문리스트</a></li>
                <li class=""><a class="navbar-list-text-style" href="#">회원리스트</a></li>
                <li class=""><a class="navbar-list-text-style" href="NonageServlet?command=admin_qna_list">Q&A리스트</a></li>
            </ul>
        </div>
        <article class="article">
            <h1 class="article-intro center">qna 게시판</h1>
            <form name="formm" method="post">
            <table class="qna-list table-color-design">
                <tr>
                    <th scope="row" class="table-th-design qna-list-th-height">제목</th>
                    <td>${adminQnaVO.subject}</td>
                </tr>
                <tr>
                    <th scope="row" class="table-th-design qna-list-th-height">등록일</th>
                    <td><fmt:formatDate value="${adminQnaVO.indate}" type="date"/></td>
                </tr>
                <tr>
                    <th scope="row" class="table-th-design qna-list-th-height">내용</th>
                    <td>${adminQnaVO.content}</td>
                </tr>
            </table>
            <div class="clear"></div>
            </form>

            <c:choose>
                <c:when test="${adminQnaVO.rep==1}">
                    <form action="NonageServlet?command=admin_qna_comment" class="form-width" method="post">
                        <div class="flex-container-comment">
                            <textarea name="reply" class="textarea-design" minlength="1"></textarea>
                            <input type="hidden" name="qseq" value="${adminQnaVO.qseq}">
                            <input type="submit" class="btn-design btn-design-comment" value="저장">
                        </div>
                    </form>
                </c:when>

                <c:when test="${adminQnaVO.rep==2}">
                    <table class="comment-table table-color-design">
                    <tr>
                        <th scope="row" class="table-th-design qna-list-th-height">댓글</th>
                        <td>${adminQnaVO.reply}</td>
                    </tr>
                    </table>
                </c:when>
            </c:choose>

        </article>
    </div>
    <div class="btn-container">
       <input type="button" class="btn-design" value="목록" onclick="location.href='NonageServlet?command=admin_qna_list'">
    </div>
</div>
<!-- Admin Q&A Detail 페이지 끝 -->
<%@ include file="/admin/admin_footer.html" %>
</body>
</html>