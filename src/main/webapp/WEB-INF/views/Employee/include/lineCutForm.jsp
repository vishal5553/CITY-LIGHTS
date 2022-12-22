
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
	<h3 style="color: red;">${requestScope.message}</h3>
	<div class="container-fluid">
		<form action='<spring:url value="/Employee/lineCutForm"/>'
			method="post" enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Consumer Complaint form</h2>
			<div class="row jumbotron">
				<div class="row">
					<div class="col-lg-8 form-group">
						<label for="name-f"><h5>Employee Name</h5></label> <input
							type="text" class="form-control"
							value="${sessionScope.userDetails.name}" readonly>

					</div>

					<div class="col-lg-8 form-group">
						<label for="name-f">Complaint</label> <select type="text"
							name="complaintId" class="form-control">
							<c:forEach var="com" items="${sessionScope.complaints}">
								<option value="${com.id}">${com.consumer.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-12 form-group">
						<label for="name-f">Description</label>
						<textarea type="text" class="form-control" name="description" id="description"></textarea>

					</div>
					<div class="col-lg-8 form-group">
						<label for="name-f">Date </label> <input type="date"
							class="form-control" name="date">

					</div>
					<div class="col-lg-8 form-group">
						<label for="name-f">Start Time </label> <input type="time"
							class="form-control" name="stime">

					</div>
					<div class="col-lg-8 form-group">
						<label for="name-f">End Time </label> <input type="time"
							class="form-control" name="etime">

					</div>

					<div class="col-lg-6 form-group mb-0">
						<button class="btn btn-primary float-right">Submit Request</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>