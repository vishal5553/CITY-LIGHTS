
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		
		<div class="alert alert-primary text-center" role="alert">
			<h2>Availabe Employee List</h2>
			<a href='<spring:url value="/Admin/addEmp"/>' class="btn btn-primary">Add new</a>
		</div>

			<h3
				style="color: green; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.message}</h3>

		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Sr No</th>
							<th scope="col">Name</th>
							<th scope="col">Mobile</th>
							<th scope="col">Email</th>
							<th scope="col">type</th>
							
						</tr>
					</thead>
					<tbody>
						<%
							int sr_no = 0;
						%>
						<c:forEach var="emp" items="${sessionScope.empList}">
							<tr>
								<td scope="row">${sr_no=sr_no+1}</td>
								<td>${emp.name}</td>
								<td>${emp.mobile}</td>
								<td>${emp.email}</td>
								<td>${emp.type}</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>