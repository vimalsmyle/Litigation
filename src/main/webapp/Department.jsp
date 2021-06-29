<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">

<link rel="stylesheet" href="css/style.css">
<title>Department</title>
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
				<table id="departmentTable" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							
					style="width: 100%">
					<thead>
						<tr>
							<th>Department Name</th>
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
					<h5 class="modal-title" id="exampleModalLabel"> Add Department
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="departmentDetails">
						<div class="row">
							<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Name:</label>
									<input type="text" class="form-control form-control-sm"  name="departmentNameAdd"
										id="departmentNameAdd" placeholder = "ministry Name">
								  </div>
								</div>
							<div class="col-md-6">
								 <div class="group form-group has-feedback has-success">
									<label for="text">Ministry Name:</label>
									<select
										class="form-control  form-control-sm select2" id="departmentMinistryNameAdd" name="departmentMinistryNameAdd">
									</select> 
									</div>
								</div>
							
							<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Address:</label>
									<input type="text" class="form-control form-control-sm" name="departmentAddressAdd"
										id="departmentAddressAdd" placeholder = "ministry Address">
								  </div>
								</div>
							
							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Type of Address:</label>
									<input type="text" class="form-control form-control-sm" name="departmentTypeOfAddressAdd"
										id="departmentTypeOfAddressAdd" placeholder = "Community Mobile">
								  </div>
							</div>
							
							
							
				</div>
<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="departmentAdd"
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

	<div class="modal fade" id="myDepartmentEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit Department</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="departmentEdit">
						<div class="row">
							<div class="col-md-6">
								<div id="formdepartmentName" class="group form-group">
									<label for="text">Name:</label>
									<input type="text" class="form-control form-control-sm"  name="departmentNameEdit"
										id="departmentNameEdit" placeholder = "ministry Name">
								  </div>
							</div>
							
							<div class="col-md-6">
								<div id="formdepartmentMinistryName" class="group form-group">
									<label for="text">Name:</label>
									<select
										class="form-control  form-control-sm select2" id="departmentMinistryNameEdit" name="departmentMinistryNameEdit">
									</select>
									</div>
							</div>
							<div class="col-md-6">
								<div id="formdepartmentAddress" class="group form-group">
									<label for="text">Address:</label>
									<input type="text" class="form-control form-control-sm"  name="departmentAddressEdit"
										id="departmentAddressEdit" placeholder = "Department Address">
								  </div>
							</div>

							<div class="col-md-6">
								
								<div id="formdepartmentTypeOfAddress" class="group form-group">
									<label for="text">Type of address:</label>
									<input type="text" class="form-control form-control-sm"  name="departmentTypeOfAddressEdit"
										id="departmentTypeOfAddressEdit" placeholder = "ministry type of address">
								  </div>
							</div>
							
										<input type = "hidden" id="departmentIdhidden">
							</div>
							<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="departmentEditsave"
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
	
	
		
	<script src="js/department.js"></script>
	
		<script src="js/dropdown.js"></script>
	
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
		
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