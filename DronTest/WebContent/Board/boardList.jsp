<%@page import="dron.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="sun.java2d.loops.Blit"%> 
<%@page import="java.util.List"%> 
<%@page import="dron.board.db.BoardBean"%> 

<% 
String username=null; 
if(session.getAttribute("username")!=null){ 
	username=(String)session.getAttribute("username"); 
} 


List boardList =(List)request.getAttribute("boardlist"); 
int listcount=((Integer)request.getAttribute("listcount")).intValue(); 
int nowpage=((Integer)request.getAttribute("page")).intValue(); 
int maxpage=((Integer)request.getAttribute("maxpage")).intValue(); //최대 페이지수
int startpage=((Integer)request.getAttribute("startpage")).intValue(); //현재 페이지에 표시할 첫 페이지 수
int endpage=((Integer)request.getAttribute("endpage")).intValue(); //현제 페이지에 표시 할 끝 페이지 수



List d_boardList =(List)request.getAttribute("d_boardlist"); 
int d_listcount=((Integer)request.getAttribute("d_listcount")).intValue(); 

%>

<html lang="en">

  <head>
	
<script>
		function btn() {
			<%if(session.getAttribute("username") == null) { %>
			alert('로그인 해주세요');
			location.href="./MemberLogin.dron";
		<% } else { %>
			location.href="./video.dron";
		<% } %>
		}
</script>
<script>
		function btn2() {
			<%if(session.getAttribute("username") == null) { %>
			alert('로그인 해주세요');
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

    <title>3조 조미정</title>

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
        <a class="navbar-brand" href="./main.dron">3조 조미정</a>
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
              <a class="nav-link" href="#" onclick="btn();">Drone demonstration video</a> <!-- 제품 소개 -->
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#" onclick="btn2();" >Production process</a> <!-- 제작 과정 -->
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
						<a href="/MemberJoin.dron" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%> 님 환영합니다.<span class="caret"></span></a>
						 <ul class="dropdown-menu" style="right:auto; left:auto;">
							<li><a href="./MemberLogoutAction.dron">로그아웃</a></li>			
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
              <h1>조미정</h1>
              <span class="subheading">AI IOT DRONE WEB</span>
            </div>
          </div>
        </div>
      </div>
    </header>




<!-- ------------------------------------------------- -->
<!-- 게시판 리스트 -->

<div class="container"> 
<table class="table table-striped"> 
    <tr align="center" valign="middle"> 
        <td colspan="4"><k1>제작 과정</k1></td> 
        <td align=right> <font size=2><span class="glyphicon glyphicon-pencil"></span>&nbsp;글 개수 : ${listcount}</font></td> 
    </tr> 
    <tr align="center" valign="middle" bordercolor="#333333"> 
        <td style="font-family:Tahoma;font-size:8pt" width="8%" height="26"> 
            <div align="center"> 번호</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="50%"> 
            <div align="center"> 제목</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="14%"> 
            <div align="center"> 작성자</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="17%"> 
            <div align="center"> 날짜</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="11%"> 
            <div align="center"> 조회수</div> 
        </td>                 
    </tr>     
    <% 
        for(int i=0 ; i<boardList.size() ; i++){ 
            BoardBean bl=(BoardBean)boardList.get(i);                     
    %> 
    
    <tr align="center" valign="middle" bordercolor="#333333"  
        onmouseover="this.style.backgroundColor='f8f8f8'" onmouseout="this.style.backgroundColor=''"> 
        <td height="23" style="font-family:Tahoma;font-size:10pt"><%=bl.getBOARD_NUM() %></td> 
        <td style="font-family:tahoma;font-size:10pt"> 
            <div align="left"> 
            <%if(bl.getBOARD_RE_LEV()!=0){ %> 
                <%for(int a=0 ; a<=bl.getBOARD_RE_LEV()*2 ; a++){ %> 
                    &nbsp; 
                <%} %> 
                    ▶ 
            <%}else{ %> 
                    
            <%} %> 
            
            <a href="./BoardDetailAction.bo?num=<%=bl.getBOARD_NUM() %>"> 
                <%=bl.getBOARD_SUBJECT() %></a></div>                 
        </td>         
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl.getBOARD_NAME() %></div> 
        </td> 
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl.getBOARD_DATE() %></div> 
        </td> 
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl.getBOARD_READCOUNT() %></div> 
        </td> 
    </tr> 
    <%} %> 
    <tr align=center height=20> 
        <td colspan=7 style=font-family.Tahoma, font-size:10pt> 
            <%if(nowpage<=1){ %>    [이전] &nbsp;         
            <%}else{ %> 
                <a href ="./BoardList.no?page=<%=nowpage-1%>">[이전]</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                        [<%=a %>]     
               <%}else{ %> 
                <a href="./BoardList.bo?page=<%=a %>">[<%=a%>]</a>&nbsp;
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> [다음] 
            <%}else{ %> 
                <a href ="./BoardList.bo?page=<%=nowpage+1%>">[다음]</a> 
            <%} %>          
        </td> 
    </tr> 
</table> 
</div>
<hr/>
<div class="container">
<tfooter>
<%if(username!=null && username.equals("admin")){ %> 
<a class="btn btn-default pull-right" href="./BoardWrite.bo">글쓰기</a>
 <%} %> 
</tfooter>
</div>


<!-- 관리자 모드 -->
<%if(username!=null && username.equals("admin")){ %> 

<div class="container"> 
<table class="table table-striped"> 
    <tr align="center" valign="middle"> 
        <td colspan="4"><k1>휴지통</td> 
        <td align=right> <font size=2><span class="glyphicon glyphicon-pencil"></span>&nbsp;글 개수 : ${d_listcount}</font></td> 
    </tr> 
    <tr align="center" valign="middle" bordercolor="#333333"> 
        <td style="font-family:Tahoma;font-size:8pt" width="8%" height="26"> 
            <div align="center"> 번호</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="50%"> 
            <div align="center"> 제목</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="14%"> 
            <div align="center"> 작성자</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="17%"> 
            <div align="center"> 날짜</div> 
        </td> 
        <td style="font-family:Tahoma;font-size:8pt" width="11%"> 
            <div align="center"> 조회수</div> 
        </td>                 
    </tr>
    
    
    
    
    
    
    
    
    <% 
        for(int i=0 ; i<d_boardList.size() ; i++){ 
            BoardBean bl2=(BoardBean)d_boardList.get(i);                     
    %> 
    
    <tr align="center" valign="middle" bordercolor="#333333"  
        onmouseover="this.style.backgroundColor='f8f8f8'" onmouseout="this.style.backgroundColor=''"> 
        <td height="23" style="font-family:Tahoma;font-size:10pt"><%=bl2.getBOARD_NUM() %></td> 
        <td style="font-family:tahoma;font-size:10pt"> 
            <div align="left"> 
            <%if(bl2.getBOARD_RE_LEV()!=0){ %> 
                <%for(int a=0 ; a<=bl2.getBOARD_RE_LEV()*2 ; a++){ %> 
                    &nbsp;
                <%} %> 
                    ▶ 
            <%}else{ %> 
                    
            <%} %> 
            
            <a href="./DeleteDetailAction.bo?num=<%=bl2.getBOARD_NUM() %>">
                <%=bl2.getBOARD_SUBJECT() %></a></div>
        </td>         
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl2.getBOARD_NAME() %></div> 
        </td> 
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl2.getBOARD_DATE() %></div> 
        </td> 
        <td style="font-family:Tahoma;font-size:10pt"> 
            <div align="center"><%=bl2.getBOARD_READCOUNT() %></div> 
        </td> 
    </tr> 
    <%} %> 
    <tr align=center height=20> 
        <td colspan=7 style=font-family.Tahoma, font-size:10pt> 
            <%if(nowpage<=1){ %>    [이전] &nbsp;         
            <%}else{ %> 
                <a href ="./BoardList.no?page=<%=nowpage-1%>">[이전]</a>&nbsp; 
            <%} %> 
             
            <%for(int a=startpage ; a<=endpage ; a++){  
                    if(a==nowpage){ %>  
                        [<%=a %>]     
               <%}else{ %> 
                <a href="./BoardList.bo?page=<%=a %>">[<%=a%>]</a>&nbsp; 
                <%} %> 
             <%} %>  
              
             <%if(nowpage>=maxpage){ %> [다음] 
            <%}else{ %> 
                <a href ="./BoardList.bo?page=<%=nowpage+1%>">[다음]</a> 
            <%} %>          
        </td> 
    </tr>

 
</table> 
   
</div>
<%} %> 

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