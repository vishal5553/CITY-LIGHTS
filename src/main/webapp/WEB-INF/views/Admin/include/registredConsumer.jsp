
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		
		<div class="alert alert-primary text-center" role="alert">
			<h2>Registred User List</h2>
		</div>

			<h3
				style="color: green; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.success}</h3>

		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Sr No</th>
							<th scope="col">Name</th>
							
							<th scope="col">Email</th>
							<th scope="col">Status</th>
							
						</tr>
					</thead>
					<tbody>
						<%
							int sr_no = 0;
						%>
						<c:forEach var="consumer" items="${sessionScope.consumerList}">
							<tr>
								<td scope="row">${sr_no=sr_no+1}</td>
								<td>${consumer.name}</td>
								
								<td>${consumer.email}</td>
								<td>${consumer.status}</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>