

<%@page import="com.app.pojos.Admin"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content-wrapper">
	
		<div class="container-fluid">
			<div class="alert alert-primary text-center" role="alert">
				<h1>Admin Profile</h1>
			</div>

			<h3
				style="color: green; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.success}</h3>
			<h3
				style="color: red; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.erroor}</h3>
			<div class="row">

				
	
	<div class="col col-sm-12 col-lg-8">
		<section class="vh-100" style="background-color: #eee;">
			<form action='<spring:url value="/Admin/updateProfile"/>' method="post">
				<div class="container">
					<div class="col">
						<div class="card text-black" style="border-radius: 25px;">
							<div class="card-body p-md-5">
								<div class="row justify-content-center">
									<div class="col-md-10 col-lg-12">
										<div class="row">
											<div class="col col-lg-6 mb-4">
												<div class="form-outline flex-fill mb-0">
													<label class="form-label">Your Name </label> <input
														value="${sessionScope.userDetails.name}" type="text"
														name="name" class="form-control col-lg-12 text-center" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col col-lg-6 mb-4">
												<div class="form-outline flex-fill mb-0">
													<label class="form-label">Your Email</label> <input
														value="${sessionScope.userDetails.email}" type="email"
														name="email" class="form-control col-lg-12" readonly/>
												</div>
											</div>


											
										</div>
										<div class="row">
											<div class="col col-lg-6 mb-4">
												<div class="form-outline flex-fill mb-0">
													<label class="form-label">Your Mobile </label> <input
														value="${sessionScope.userDetails.mobile}" type="text"
														name="mobile" class="form-control col-lg-12" />
												</div>
											</div>


											
										</div>
										

										</div>

										<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
											<input type="submit" class="btn btn-primary btn-lg"
												value="Update">
										</div>
			</form>
	</div>

</div>
</div>
</div>
</section>
</div>
</div>
