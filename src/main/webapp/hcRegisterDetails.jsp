<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<title>HC Register Details</title>
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
				<table id="NominatedTable" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							
					style="width: 100%">
					<thead>
						<tr>
							<th>File Number</th>
							<th>File Year</th>
							<th>File By</th>
							<th>Case Type Id</th>
							<th>Case Number</th>
							<th>Case Year</th>
							<th>FR Number</th>
							<th>Ministry Name</th>
							<th>Department Name</th>
							<th>Counsel Name</th>
							<th>Counsel On-record Id</th>
							<th>Date</th>
							<th>Re-Nominated Counsel Id</th>
							<th>Re-Nominated Counsel Date</th>
							<th>Remark</th>
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
	
	
		
	<script src="js/counsel.js"></script>
	<script src="js/dropdown.js"></script>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>      	
        
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
			
			
			var date = new Date();
            var currentMonth = date.getMonth();
            var currentDate = date.getDate();
            var currentYear = date.getFullYear();
			
			 $('#termFromAdd').datepicker({
                 autoclose: true,
                 format: "yyyy-mm-dd"
             });
			 
			 $('#termUptoAdd').datepicker({
                 autoclose: true,
                 format: "yyyy-mm-dd"
             });
			
			
			
		});
	</script>
</body>
</html>