<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="dron.board.db.BoardBean"%> 
<%@ page language="java" contentType="text/html; charset=EUC-KR" %> 
<%BoardBean board = (BoardBean)request.getAttribute("boarddata"); %>

<% 
String username=null; 
if(session.getAttribute("username")!=null){ 
	username=(String)session.getAttribute("username"); 
} 
%>


<html lang="en">

  <head>
	
<script>
		function btn() {
			<%if(session.getAttribute("username") == null) { %>
			alert('�α��� ���ּ���');
			location.href="./MemberLogin.dron";
		<% } else { %>
			location.href="./video.dron";
		<% } %>
		}
</script>
<script>
		function btn2() {
			<%if(session.getAttribute("username") == null) { %>
			alert('�α��� ���ּ���');
			location.href="./MemberLogin.dron";
			<% } else { %>
			location.href="./BoardList.bo";
			<% } %>
			}
		
</script>
	
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>3�� ������</title>

    <!-- Bootstrap core CSS -->
    <link href="./Resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="./Resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="./Resources/css/clean-blog.min.css" rel="stylesheet">
	
	 <!-- font -->
  <link href='http://fonts.googleapis.com/earlyaccess/jejugothic.css' rel='stylesheet' type='text/css'>
  <link href="./Resources/css/customFont.css" rel='stylesheet'>
  </head>
  
  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" href="./main.dron"">3�� ������</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="./main.dron">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="btn();">product descriptions</a> <!-- ��ǰ �Ұ� -->
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="btn2();" >Production process</a> <!-- ���� ���� -->
            </li>
            
             <%if(session.getAttribute("username")==null) { %>
             <li class="nav-item">
				<a href="/MemberJoin.dron" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Connection<span class="caret"></span></a>
				 <ul class="dropdown-menu" style="right:auto; left:auto;">
					<li><a href="./MemberLogin.dron">Login</a></li>
					<li><a href="./MemberJoin.dron">register</a></li>
				</ul>
			</li>
			<% } else { %>
			<% session.setMaxInactiveInterval(180);%>
          	<ul class="nav navbar-nav navbar-right">
					<li class="nav-item">
						<a href="/MemberJoin.dron" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%> �� ȯ���մϴ�.<span class="caret"></span></a>
						 <ul class="dropdown-menu" style="right:auto; left:auto;">
							<li><a href="./MemberLogoutAction.dron">�α׾ƿ�</a></li>			
					   </ul>
					</li>
				</ul>
				<% } %>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Header -->
    <header class="masthead" style="background-image: url('./Resources/img/jumbotronBackGround.jpg')">
      <div class="overlay"></div>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <h1>������</h1>
              <span class="subheading">AI IOT DRONE WEB</span>
            </div>
          </div>
        </div>
      </div>
    </header>
	

<!-- ---------------------------------------------------------------------------------------------------------- -->
<div class="container">
<form name="boardform" action="./BoardReplyView.bo" method="post" > 
    <input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %>"> 
    <input type="hidden" name="BOARD_RE_REF" value="<%=board.getBOARD_RE_REF() %>"> 
    <input type="hidden" name="BOARD_RE_LEV" value="<%=board.getBOARD_RE_LEV() %>"> 
    <input type="hidden" name="BOARD_RE_SEQ" value="<%=board.getBOARD_RE_SEQ() %>"> 
    <input type="hidden" name="BOARD_NAME" value="<%=username%>"> 

<table class="table table-bordered"> 
    <tr align="center" valign="middle"> 
        <td colspan="5">REPLY</td>     
    </tr> 
    <tr> 
        <td style="font-family:����; font-size:12" height="16"> 
        <div align="center">�۾���</div></td>     
        <td><%=username %></td>     
    </tr> 
    <tr> 
        <td style="font-family:����; font-size:12" height="16"> 
            <div align="center">����</div> 
        </td> 
        <td> 
            <input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value="Re:<%=board.getBOARD_SUBJECT()%>" class="form-control"/> <!-- class="form-control" ���̺� ũ�Կ� �°� ������ -->
        </td>                     
        </td>         
    </tr> 
    <tr> 
        <td style="font-family:����; font-size:12"><div align="center" >����</div></td> 
        <td> 
            <textarea name="BOARD_CONTENT" cols="67" rows="15" align="left" class="form-control">RE:</textarea> 
        </td> 
    </tr> 
    <tr> 
        <td style="font-family:����; font-size:12"><div align="center">��й�ȣ</div></td> 
        <td><input name="BOARD_PASS" type="password"></td> 
    </tr> 
    <tr bgcolor="cccccc"> <td colspan="2" style="height:1px"> </td> </tr> 
    <tr><td colspan="2"> &nbsp;</td></tr> 
    <tr align="center" valign="middel"> 
        <td colspan="5"> 
            <a href="javascript:boardform.submit()">[���]</a>&nbsp;&nbsp; 
            <a href ="javascript:history.go(-1)">[�ڷ�]</a> 
        </td> 
    </tr>     
</table> 
</form> 
</div>

<!-- ---------------------------------------------------------------------------------------------- -->

<!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <ul class="list-inline text-center">
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="https://github.com/samdo0812">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
            </ul>
            <p class="copyright text-muted">Copyright &copy; DoHoon Kim 2018</p>
          </div>
        </div>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="./Resources/vendor/jquery/jquery.min.js"></script>
    <script src="./Resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="./js/clean-blog.min.js"></script>

  </body>

</html>