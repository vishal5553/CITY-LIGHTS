
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
	<h3 style="color: red;">${requestScope.message}</h3>
	<div class="container-fluid">
		<form action='<spring:url value="/Operator/billing"/>'
			method="post" enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Billing Form</h2>
			<div class="row jumbotron">
				<div class="row">
					

					<div class="col-lg-12 form-group">
						<label for="name-f">Consumer</label> <select type="text"
							name="id" class="form-control text-center">
							<c:forEach var="con" items="${sessionScope.Consumers}">
								<option value="${con.id}">${con.consumerNo} - ${con.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-4 form-group">
						<label for="name-f">Unit Consumption</label>
						<input type="text" class="form-control" oninput="allow_alphabets(this)"name="unit" id="unit" required>

					</div>
					<div class="col-lg-4 form-group">
						<label for="name-f">Date</label> <input type="date"
							class="form-control" name="date" required>

					</div>
					<div class="col-lg-4 form-group">
						<label for="name-f">Due Date </label> <input type="date"
							class="form-control" name="dueDate" required>

					</div>
					<script>
					function allow_alphabets(element) {
						let textInput = element.value;
						textInput = textInput.replace(/[^0-9 ]*$/gm, "");
						element.value = textInput;
					}
				</script>

					<div class="col-lg-6 form-group mb-0">
						<button class="btn btn-primary float-right">Send Bill</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>