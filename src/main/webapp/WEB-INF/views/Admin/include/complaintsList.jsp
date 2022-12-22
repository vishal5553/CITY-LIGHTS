
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		
		<div class="alert alert-primary text-center" role="alert">
			<h2>New Complaint Received List</h2>
		</div>

			<h3
				style="color: green; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.message}</h3>

		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Sr No</th>
							<th scope="col">Consumer Name</th>
							<th scope="col">Address</th>
							
							<th scope="col">Meter Image</th>
							<th scope="col">Status</th>
							
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
							int sr_no = 0;
						%>
						<c:forEach var="cr" items="${sessionScope.complaintsList}">
							<tr>
								<td scope="row">${sr_no=sr_no+1}</td>
								<td>${cr.consumer.name}</td>
								<td>${cr.consumer.address.streetLine} , ${cr.consumer.address.city} , ${cr.consumer.address.district}</td>
								
								<td><img src="data:image/jpeg;base64,${cr.imgUtility}" height=100px width=100px/></td>
								<td> ${cr.status} </td>
								
								<td><a
									href='<spring:url value="/Admin/assignEmployee?rid=${cr.id}"/>'><button
											type="button" class="btn btn-primary">
											<i class="fa fa-tags">Assign Employee</i>
										</button></a>
										
										 </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>