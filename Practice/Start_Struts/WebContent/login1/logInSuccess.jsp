<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- Action에서 Session 객체에 저장한 userInfo 자바 빈 객체를 사용한다. --%> 
<jsp:useBean class="strutsguide.beans.UserInfoBean" id="userInfo" scope="session"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	로그인 사용자명 : <jsp:getProperty name="userInfo" property="userName"/><br /> 
         전화번호 : <jsp:getProperty name="userInfo" property="phone"/><br />
         이메일 : <jsp:getProperty name="userInfo" property="email"/><br />
</body>
</html>