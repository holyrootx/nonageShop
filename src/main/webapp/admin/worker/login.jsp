<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

</head>
<body>
<!--관리자 로그인 페이지 시작-->
        <article>
            <h1>Login</h1>
            <form method="post" action="NonageServlet?command=admin_login">
                <fieldset>
                <legend></legend>
                  <label>User ID</label>
                  <input name="id" type="text" value="" placeholder="아이디 입력"><br>
                  <label>Password</label>
                  <input name="pwd" type="password" value="" placeholder="비밀번호 입력"><br>
                </fieldset>
                <div class="clear"></div>
                <div id="buttons">
                    <input type="submit" value="로그인" class="submit">
                </div>
            </form>
          </article>
<!--관리자 로그인 페이지 끝  -->
</body>
</html>