<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">

<link rel="stylesheet" href="css/style.css">
<title>Counsel</title>
</head>
<body>

<%
		String user_id = (String) session.getAttribute("roleID");

	%>

	<%
		if (user_id == null) {
			response.sendRedirect("login.jsp");
		}else {
	%>
	
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row mt-5 mb-5">
			<div class="col-md-12 mt-5 mb-5">
				<table id="counselTable" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							
					style="width: 100%">
					<thead>
						<tr>
							<th>Name</th>
							<th>Type</th>
							<th>Address</th>
							<th>Mobile Number</th>
							<th>Email</th>
							<th>Telephone Number</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>

				</table>
			</div>
		</div>
	</div>
	
	
	
	
	<jsp:include page="footer.jsp" />
	<%} %>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
	
	
		
	<script src="js/rcReg.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		
	<script>
		$(document).ready(function() {
			var table = $('#datetable').DataTable({
				fixedHeader : true,
				language : {
					paginate : {
						next : '<i class="fa fa-angle-right"></i>',
						previous : '<i class="fa fa-angle-left"></i>;'
					}
				}
			});
		});
	</script>
</body>
</html>