
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
	<div class="container-fluid">
		<form action='<spring:url value="/Consumer/payment"/>' method="post"
			enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">Process Payment</h2>
			<div class="row jumbotron">
				<div class="row">
					<div class="col-lg-4 form-group">
						<label for="name-f"><h5>Amount</h5></label> <input type="text"
							class="form-control" name="amount" id="amount" value="5000" readonly>
					</div>
					<div class="col-lg-6 form-group">
						<label for="name-f">Card Number</label> <input type="text"
							class="form-control" name="cardNo" id="cardNo" placeholder="Enter Card Number">
					</div>
					<div class="col-lg-6 form-group">
						<label for="name-f">Card Holder Name</label> <input type="text"
							class="form-control" name="cardName" id="cardName">
					</div>
					<div class="col-lg-2 form-group">
						<label for="name-f">Expiry Date </label> <input type="text"
							class="form-control" name="expDate" id="expDate" placeholder="MM/YY">
					</div>
					<div class="col-lg-2 form-group">
						<label for="name-f">CVV</label> <input type="password"
							class="form-control" name="cvv" id="cvv">
					</div>
					<div class="col-lg-6 form-group mb-0">
						<button class="btn btn-primary float-right">Pay</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>