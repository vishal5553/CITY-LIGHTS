<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
	<div class="container-fluid">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Work</a></li>
			<li class="breadcrumb-item active">Complaints</li>
			<li class="breadcrumb-item"><a class="btn btn-primary right"
				href='<spring:url value="/Employee/Dashboard"/>'>Back</a></li>
		</ol>
		<div class="alert alert-primary text-center" role="alert">
			<h2>Complaint Requests</h2>
		</div>

		<h3
			style="color: red; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.message}</h3>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Sr No</th>
					<th scope="col"> Name</th>
					<th scope="col">Address</th>
					<th scope="col">Contact</th>
					<th scope="col">Meter Image</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					int sr_no = 0;
				%>
				<c:forEach var="c" items="${sessionScope.complaints}">

					<tr>
						<th scope="row">${sr_no=sr_no+1}</th>
						<td> ${c.consumer.name}</td>
						<td> ${c.consumer.address.city}</td>
						<td>${c.consumer.mobile}</td>
						<td><img src="data:image/jpeg;base64,${c.imgUtility}" height=100px width=100px/></td>
					<td><a
									href='<spring:url value="/Employee/toggleComplaint?rid=${c.id}"/>'><button
											type="button" class="btn btn-primary">
											<i class="fa fa-tags">${c.status}</i>
										</button></a></td>
					</tr>
					</c:forEach>
			</tbody>
		</table>

	</div>
</div>

