
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		<form action='<spring:url value="/Consumer/cpayment"/>' method="post"
			enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Process Payment</h2>
			<div class="row jumbotron">
				<div class="row">
					<div class="col-lg-12 form-group">
						<label for="name-f"><h5>OTP</h5></label> <input type="text"
							class="form-control" name="otp" id="otp" 							>
					<div class="col-lg-6 form-group mb-0">
						<button class="btn btn-primary float-right">Pay</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>