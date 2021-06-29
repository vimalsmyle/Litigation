<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">

<link rel="stylesheet" href="css/style.css">
<title>Ministry</title>
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
				<table id="ministryTable" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							
					style="width: 100%">
					<thead>
						<tr>
							<th>Ministry Name</th>
							<th>Address</th>
							<th>Type of Address</th>
							<th>Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>

				</table>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel"> Add Ministry
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="ministryDetails">
						<div class="row">
							<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Name:</label>
									<input type="text" class="form-control form-control-sm"  name="ministryNameAdd"
										id="ministryNameAdd" placeholder = "ministry Name">
								  </div>
								</div>
							
							
							<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Address:</label>
									<input type="text" class="form-control form-control-sm" name="ministryAddressAdd"
										id="ministryAddressAdd" placeholder = "ministry Address">
								  </div>
								</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Type of Address:</label>
									<input type="text" class="form-control form-control-sm" name="ministryTypeOfAddressAdd"
										id="ministryTypeOfAddressAdd" placeholder = "Community Mobile">
								  </div>
							</div>
							
							
							
				</div>
<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="ministryAdd"
									type="button" disabled>Save</button>
									<!-- <button type="button" class="btn btn-danger btn-raised resetFilter" id="resetFilter">Reset</button> -->
							<button type="button" class="btn btn-danger btn-raised"
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
							</div>
					</form>

				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="myMinistryEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Ministry</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="ministryEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formministryName" class="group form-group">
									<label for="text">Name:</label>
									<input type="text" class="form-control form-control-sm"  name="ministryNameEdit"
										id="ministryNameEdit" placeholder = "ministry Name">
								  </div>
							</div>
							<div class="col-md-6">
								<div id="formministryAddress" class="group form-group">
									<label for="text">Address:</label>
									<input type="text" class="form-control form-control-sm"  name="ministryAddressEdit"
										id="ministryAddressEdit" placeholder = "ministry Address">
								  </div>
							</div>

							<div class="col-md-6">
								
								<div id="formministryTypeOfAddress" class="group form-group">
									<label for="text">Type of address:</label>
									<input type="text" class="form-control form-control-sm"  name="ministryTypeOfAddressEdit"
										id="ministryTypeOfAddressEdit" placeholder = "ministry type of address">
								  </div>
							</div>
							
										<input type = "hidden" id="ministryIdhidden">
							</div>
							<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="ministryEditsave"
									type="button" disabled>Update</button>
							<button type="button" class="btn btn-danger btn-raised "
									data-dismiss="modal">
									Close
									<div class="ripple-container"></div>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<jsp:include page="footer.jsp" />
	<%} %>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
	
	
		
	<script src="js/ministry.js"></script>
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