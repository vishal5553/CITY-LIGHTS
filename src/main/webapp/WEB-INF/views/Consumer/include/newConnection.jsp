
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		<form action='<spring:url value="/Consumer/connectionRequest"/>' method="post"
			enctype="multipart/form-data"
			onsubmit="return checkCheckBoxes(this);">
			<h2 class="text-center">City Light New Connection Application Form </h2>
			<div class="row jumbotron">
<div class="col-lg-12 form-group">
					<label for="name-f">Consumer ID</label> <input type="text"
						class="form-control" name="consumerNo" id="consumerNo"
						oninput="allow_alphabets(this)" placeholder="**********"
						value="${sessionScope.userDetails.consumerNo}" readonly>
					<p>${requestScope.nameerror}</p>
				</div>

				<div class="col-sm-6 form-group">
					<label for="name-f">Consumer Name</label> <input type="text"
						class="form-control" name="name" id="name"
						oninput="allow_alphabets(this)" placeholder="Enter your Full Name"
						value="${sessionScope.userDetails.name}" required>
					<p>${requestScope.nameerror}</p>
				</div>

				<div class="col-sm-6 form-group">
					<label for="name-f">Email</label> <input
						value="${sessionScope.userDetails.email}" type="email"
						name="email" class="form-control col-lg-12"  />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Date Of Birth</label> <input
						value="${sessionScope.userDetails.dob}" type="date" name="dob"
						class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Contact Number</label> <input
						value="${sessionScope.userDetails.mobile}" type="text"
						name="mobile" class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Street Line</label> <input
						value="${sessionScope.userDetails.address.streetLine}" type="text"
						name="streetLine" class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">City</label> <input
						value="${sessionScope.userDetails.address.city}" type="text"
						name="city" class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">District</label> <input
						value="${sessionScope.userDetails.address.district}" type="text"
						name="district" class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">State</label> <input
						value="${sessionScope.userDetails.address.state}" type="text"
						name="state" class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Pincode</label> <input
						value="${sessionScope.userDetails.address.pinCode}" type="text"
						name="pincode" class="form-control col-lg-12" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Country</label> <input
						value="${sessionScope.userDetails.address.country}" type="text"
						name="country" class="form-control col-lg-16" />
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Aadhar Number</label> <input
						type="text" name="aadharNo" class="form-control col-lg-16" required/>
				</div>
				<div class="col-sm-6 form-group">
					<label for="name-f">Aadhar Card(JPEG/PNG below 500KB)</label> <input
						type="file" name="aadhar" class="form-control col-lg-16" />
				</div>
				<div class="col-sm-12">
					<input type="checkbox" name="MyCheckbox"
						class="form-check d-inline" id="chb" required><label
						for="chb" class="form-check-label">
						&nbsp;I accept all terms and conditions. </label>
				</div>
				<div class="col-sm-12 form-group mb-0">
					<button class="btn btn-primary float-right">Proceed To Payment</button>
				</div>
			</div>
		</form>
	</div>
</div>