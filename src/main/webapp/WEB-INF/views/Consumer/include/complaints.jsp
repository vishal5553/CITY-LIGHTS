
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
<h3 style="color:red;"> ${requestScope.message} </h3>
	<div class="container-fluid">
		<form action='<spring:url value="/Consumer/saveCompaint"/>' method="post" 
			enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Consumer Complaint form</h2>
			<div class="row jumbotron">
				<div class="row">
					<div class="col-lg-8 form-group">
						<label for="name-f"><h5>Name</h5></label> <input type="text"
							class="form-control" value="${sessionScope.userDetails.name}"
							readonly>

					</div>

					<div class="col-lg-8 form-group">
						<label for="name-f">Address</label> <input type="text"
							class="form-control" value="${sessionScope.userDetails.address.city}" readonly>

					</div>
					<div class="col-lg-12 form-group">
						<label for="name-f">Description</label> <textarea type="text"
							class="form-control" name="type" id="type"></textarea>

					</div>
					<div class="col-lg-8 form-group">
						<label for="name-f">Meter Image</label> <input type="file"
							class="form-control" name="image">

					</div>


					<div class="col-lg-6 form-group mb-0">
						<button class="btn btn-primary float-right">Send Complaint</button>
					</div>
				</div>
			</div>
		</form>
<div class="content-wrapper">
	<div class="container-fluid">
		
		<div class="alert alert-primary text-center" role="alert">
			<h2>My Past Complaints</h2>
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
					
							<th scope="col">Type</th>
						
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<%
							int sr_no = 0;
						%>
						<c:forEach var="cr" items="${sessionScope.complaintList}">
							<tr>
								<td scope="row">${sr_no=sr_no+1}</td>
								<td>${cr.consumer.name}</td>
								<td>${cr.type}</td>
								<td>${cr.status}</td>
							
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

	</div>
</div>