
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
	<h3 style="color: red;">${requestScope.message}</h3>
	<div class="container-fluid">
		<form action='<spring:url value="/Admin/updateCompaint"/>'
			method="post" enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Assign Employee to resolve Complaint</h2>
			<div class="row jumbotron">
				<div class="row">
					<input type="text" name="cid" value="${sessionScope.comp.id}"
						hidden>
					<div class="col-lg-8 form-group">
						<label for="name-f"><h5>Name</h5></label> <input type="text"
							class="form-control" value="${sessionScope.comp.consumer.name}"
							readonly>

					</div>

					<div class="col-lg-8 form-group">
						<label for="name-f">Address</label> <input type="text"
							class="form-control"
							value="${sessionScope.comp.consumer.address.city}" readonly>

					</div>
					<div class="col-lg-12 form-group">
						<label for="name-f">Description</label>
						<textarea type="text" class="form-control" name="type" id="type"> ${sessionScope.comp.type} </textarea>

					</div>
					<div class="col-lg-12 form-group">
						<label for="name-f">Assign Employee</label> <select type="text"
							class="form-control" name="emp" id="emp">
							<c:forEach var="emp" items="${sessionScope.empList}">
								<option value="${emp.id}">${emp.name}</option>
							</c:forEach>
						</select>

					</div>


				</div>


				<div class="col-lg-6 form-group mb-0">
					<button class="btn btn-primary float-right">Update</button>
				</div>
			</div>
	</div>
	</form>


</div>
</div>