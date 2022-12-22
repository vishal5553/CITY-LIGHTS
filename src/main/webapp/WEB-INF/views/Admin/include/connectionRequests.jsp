
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		
		<div class="alert alert-primary text-center" role="alert">
			<h2>New Connection Request List</h2>
		</div>

			<h3
				style="color: green; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.success}</h3>

		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Sr No</th>
							<th scope="col">Consumer Name</th>
							<th scope="col">Address</th>
							<th scope="col">Aadhar No</th>
							<th scope="col">Aadhar Image</th>
							<th scope="col">Amount Paid</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
							int sr_no = 0;
						%>
						<c:forEach var="cr" items="${sessionScope.connectionRequest}">
							<tr>
								<td scope="row">${sr_no=sr_no+1}</td>
								<td>${cr.consumer.name}</td>
								<td>${cr.consumer.address.streetLine} , ${cr.consumer.address.city} , ${cr.consumer.address.district}</td>
								<td>${cr.aadharNo}</td> 
								<td><img src="data:image/jpeg;base64,${cr.imgUtility}" height=100px width=100px/></td>
								<td>5000</td>
								
								<td><a
									href='<spring:url value="/Admin/accept_request?rid=${cr.id}"/>'><button
											type="button" class="btn btn-primary">
											<i class="fa fa-tags"></i>
										</button></a>
										<a
									href='<spring:url value="/Admin/reject_request?rid=${cr.id}"/>'><button
											type="button" class="btn btn-danger">
											<i class="fa fa-tags"></i>
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