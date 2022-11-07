<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Plantation Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand">
					AqvaCulture Management App </a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">AqvaCulture</a></li>
			</ul>

		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${aqvaculture != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${aqvaculture == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${aqvaculture != null}">
            			Edit AqvaCulture
            		</c:if>
						<c:if test="${aqvaculture == null}">
            			Add New AqvaCulture
            		</c:if>
					</h2>
				</caption>

				<c:if test="${aqvaculture != null}">
					<input type="hidden" name="id"
						value="<c:out value='${aqvaculture.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Species </label> <input type="text"
						value="<c:out value='${aqvaculture.species}' />"
						class="form-control" name="plantation" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Area</label> <input type="text"
						value="<c:out value='${aqvaculture.area}' />"
						class="form-control" name="area">
				</fieldset>

				<fieldset class="form-group">
					<label>Start_Date</label> <input type="text"
						value="<c:out value='${aqvaculture.startdate}' />"
						class="form-control" name="pdate">
				</fieldset>

				<fieldset class="form-group">
					<label>Yield_Date</label> <input type="text"
						value="<c:out value='${aqvaculture.eydate}' />"
						class="form-control" name="ydate">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
				
			</div>
		</div><div class="container text-left"><a href="plantation-list.jsp">View</a></div>
	</div>
</body>
</html>
