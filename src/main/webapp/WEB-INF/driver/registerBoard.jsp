<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
 <meta name="description" content="" />
 <meta name="author" content="" />
 <title>Blog Post - Start Bootstrap Template</title>
 <!-- Favicon-->
 <!-- Core theme CSS (includes Bootstrap)-->
 <link href="${pageContext.request.contextPath }/cssForComment/styles.css" rel="stylesheet" />
 <style>
 .input-field {
    width: 100%;
    padding: 10px 0;
    margin: 5px 0;
    border: none;
    border-bottom: 1px solid #999;
    outline: none;
    background: transparent;
}
 </style>
</head>

<body>
<script>
function createBoard(targetUri){	
	document.frm.action=targetUri;
	}
</script>
    <%request.setCharacterEncoding("EUC-KR");%>
    <body>
        <!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#!">Start Bootstrap</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Contact</a></li>
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Blog</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page content-->
        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-8">
                    <!-- Post content-->
                    <!-- Comments section-->
                    <section class="mb-5">
                        <div class="card bg-light">
                            <div class="card-body">
                                <!-- Comment form-->
                                <form class="mb-4" name="frm">
                         		<input type="hidden" name="boardId" value="${board.boardId }">
                                <textarea class="form-control" rows="3" placeholder="Join the discussion and leave a comment!" id="comment" name="comment"></textarea></form>
                               	<button onClick="sendComment('<c:url value='/board/comment'></c:url>')">버튼</button>
                                <!-- Comment with nested comments-->
                                <!-- Single comment-->
                                <p>
                                <c:forEach var="comment" items="${commentList}" varStatus="status">
                                <div class="d-flex">
                                	<p>
                                    <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                                    <div class="ms-3">
                                        <div class="fw-bold">${comment.customer.name }</div>
                                        <p>
                                        ${comment.details } 
                                       </div>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </section>
                </div>
                <!-- Side widgets-->
                <div class="col-lg-4">
                
                    <!-- Search widget-->
                    <form name="frm" action="<c:url value='/driver/register/board'></c:url>">>
	                    <div class="card mb-4">
	                        <div class="card-header">
	                        
							<input type="text" class="input-field" placeholder="출발지" name="departure" required>
							<p><input type="text" class="input-field" placeholder="도착지" name="arrival" required>
							</div>
	                        <div class="card-body">
	                            <div class="input-group">
	                                <input class="form-control" type="text" placeholder="Enter search term..." aria-label="Enter search term..." aria-describedby="button-search" />
	                                <button class="btn btn-primary" id="button-search" type="button">Go!</button>
	                            </div>
	                        </div>
	                    </div>
                    <!-- Categories widget-->
	                    <div class="card mb-4">
	          
	                        <div class="card-header" onClick="createBoard('<c:url value='/driver/register/board'></c:url>')">보드 등록</div>
	                        <div class="card-body">
	                            <div class="row">
	                                <div class="col-sm-6">
	                                    <ul class="list-unstyled mb-0">
	                                       	
	                                        <li><input type="text" class="input-field" placeholder="출발시간" name="departureTime" required></li>
	                                        <li><input type="text" class="input-field" placeholder="도착시간" name="arrivalTime" required></li>
	         
	                                        <li><input type="text" class="input-field" placeholder="탑승가능 인원 수" name="headCount" required></li>
	                                        <li><a href="#!">운전자 정보</a></li>
	                                        <li><a href="#!">${board.driver }</a></li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
                       
                    </div>
                    <button type="submit">등록</button>
                   </form>
                    <!-- Side widget-->
                    <div class="card mb-4">
                        <div class="card-header">Side Widget</div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy to use, and feature the Bootstrap 5 card component!</div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath }/js/main.js"></script>
    </body>
</html>
