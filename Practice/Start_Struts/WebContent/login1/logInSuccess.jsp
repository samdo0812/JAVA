<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- Action���� Session ��ü�� ������ userInfo �ڹ� �� ��ü�� ����Ѵ�. --%> 
<jsp:useBean class="strutsguide.beans.UserInfoBean" id="userInfo" scope="session"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	�α��� ����ڸ� : <jsp:getProperty name="userInfo" property="userName"/><br /> 
         ��ȭ��ȣ : <jsp:getProperty name="userInfo" property="phone"/><br />
         �̸��� : <jsp:getProperty name="userInfo" property="email"/><br />
</body>
</html>