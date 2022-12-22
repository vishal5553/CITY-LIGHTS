
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
	<div class="container-fluid">
		<form action='<spring:url value="/Consumer/calculate"/>' method="post"
			enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Consumption Calculator</h2>
			<div class="row jumbotron">
				<div class="row">
					<div class="col-lg-2 form-group">
						<label for="name-f">Number of Fan</label> <input type="number" value="0"
							class="form-control" name="fan" id="fan" 
							>

					</div>

					<div class="col-lg-2 form-group">
						<label for="name-f">Number of Bulb</label> <input type="number" value="0"
							class="form-control" name="bulb" id="bulb" >

					</div>
					<div class="col-lg-2 form-group">
						<label for="name-f">Number of AC</label> <input type="number" value="0"
							class="form-control" name="ac" id="ac">

					</div>
					<div class="col-lg-2 form-group">
						<label for="name-f">Number of Refregrator </label> <input type="number" value="0"
							class="form-control" name="ref" id="ref">

					</div>
					<div class="col-lg-2 form-group">
						<label for="name-f">Number of TV</label> <input type="number" value="0"
							class="form-control" name="tv" id="tv">

					</div>
					<div class="col-lg-2 form-group">
						<label for="name-f">Count Of Day</label> <input type="number" value="0"
							class="form-control" name="day" id="day">

					</div>

					<div class="col-lg-6 form-group mb-0">
						<button class="btn btn-primary float-right">Calculate</button>
					</div>
				</div>
				<h1 style="color:orange;">${requestScope.message}</h1>
			</div>
		</form>


	</div>
</div>