
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content-wrapper">
	<div class="container-fluid">
		
		<div class="alert alert-primary text-center" role="alert">
			<h2>Bill List</h2>
		</div>

			<h3
				style="color: green; background-color: white; margin-right: 150px; margin-left: 150px;">${requestScope.message}</h3>

		<div class="row">
			<div class="col-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Sr No</th>
							<th scope="col">Consumer Name</th>
							<th scope="col">Consume Unit</th>
							<th scope="col">Date</th>
							<th scope="col">Pending Amount</th>
							<th scope="col">Due Date</th>
						
							<th scope="col">Status</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<%
							int sr_no = 0;
						%>
						<c:forEach var="b" items="${sessionScope.bills}">
							<tr>
								<td scope="row">${sr_no=sr_no+1}</td>
								<td>${b.consumer.name}</td>
								<td>${b.bill/10}</td>
								<td>${b.date}</td>
								<td>${b.bill}</td>
								<td>${b.dueDate}</td>
								<td>${b.status}</td>
							<td> <a href='<spring:url value="/Consumer/payBill?bid=${b.id}"/>'><i class='fa fa-credit-card'> Pay</i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>