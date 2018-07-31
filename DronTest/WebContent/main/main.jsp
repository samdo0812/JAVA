<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
String username=null; 
if(session.getAttribute("username")!=null){ 
	username=(String)session.getAttribute("username"); 
} 
%>

<!-- 로그인 없이 제작 과정 눌렀을 때 -->



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
             <li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         			 Connection
       		    </a>
				 <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        			  <a class="dropdown-item" href="./MemberLogin.dron">Login</a>    			  
                 	<div class="dropdown-divider"></div>
          			  <a class="dropdown-item" href="./MemberJoin.dron">register</a>
       				</div>
			</li>
			<% } else { %>
			<% session.setMaxInactiveInterval(180);%>
          	<ul class="nav navbar-nav navbar-right">
					<li class="nav-item">
						<a href="./MemberJoin.dron" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("username")%> 님 환영합니다.<span class="caret"></span></a>
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
              <span class="subheading">AI & IOT & DRONE & WEB</span>
            </div>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-preview">
              <h2 class="post-title">
               <img src="./Resources/img/kdh.png" class="img-circle"  > KIM DO HOON
              </h2>
              <h3 class="post-subtitle">일본어창의융합학부</h3>
              <p>
              	PART
              <br>	
              	<li>Web Application : Front-end, Back-end</li>
              	<li>Android Application : AI</li>
              </p>
          </div>
          
          <hr>
          
          <div class="post-preview">
              <h2 class="post-title">
                <img src="./Resources/img/cjb.jpg" class="img-responsive" > CHU JEONG BEOM
              </h2>
              <h3 class="post-subtitle">일본어창의융합학부</h3>
             <p>
              	PART
              <br>	
              	<li>Web Application : Support</li>
              	<li>Android Application : Camera, Support</li>
             </p>
          </div>  
          <hr>
          
    	<div class="post-preview">
              <h2 class="post-title">
                <img src="./Resources/img/leb.jpg" class="img-responsive" > LEE EUN BIN
              </h2>
              <h3 class="post-subtitle">법 경찰학부</h3>
              <p>
              	PART
              <br>	
              	<li>Web Application : Back-end</li>
              	<li>Android Application : AI, IOT</li>
             </p>
          </div>  
          <hr>
 
          <!-- Pager -->
          <div class="clearfix">
            <a class="btn btn-primary float-right" href="#" onclick="btn2();">제작 과정 보기 &rarr;</a> 
          </div>
        </div>
      </div>
    </div>

    <hr>

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
    <script src="./Resources/js/clean-blog.min.js"></script>

  </body>

</html>
