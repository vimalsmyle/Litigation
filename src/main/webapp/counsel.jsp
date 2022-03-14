<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
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
							<th>Court Name</th>
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
					<h5 class="modal-title" id="exampleModalLabel"> Add Counsel
						Form</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="counselDetails">
						<div class="row">
						
						<div class="col-md-6">
								 <div class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Title:</label>
									
									<select class="form-control  form-control-sm select2"
										id="selecttitleAdd" name="selecttitleAdd">
										<option value="">Select Title</option>
										<option value="Smt">Smt</option>
										<option value="Shri">Shri</option>
										<option value="Smt">Smt</option>
										<option value="Ms">Ms</option>
										<option value="Mr">Mr</option>
									</select>
									
								  </div>
								</div>
								
							<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Name:</label>
									<input type="text" class="form-control form-control-sm"  name="counselNameAdd"
										id="counselNameAdd" placeholder = "counsel Name">
								  </div>
								</div>
								
								
								<div class="col-md-6">
								 <div class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Court ID:</label>
									<select class="form-control  form-control-sm select2"
										id="courtIDAdd" name="courtIDAdd" onChange="counselTypeId(this.value)">
									</select>
								  </div>
								</div>
								
								
								
								<div class="col-md-6">
								 <div class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Counsel Type ID:</label>
									<select class="form-control  form-control-sm select2"
										id="counselTypeIDAdd" name="counselTypeIDAdd">
									</select>
								  </div>
								</div>
								
									<div class="col-md-6">
								 <div class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Term From</label>
									<input type="text" class="form-control form-control-sm" 
									name="termFromAdd"
										id="termFromAdd" placeholder = "Term From">
								  </div>
								</div>
							
							
							<div class="col-md-6">
								 <div class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Term Upto</label>
									
									<input type="text" class="form-control form-control-sm" 
									name="termUptoAdd"
										id="termUptoAdd" placeholder = "Term Upto">
								  </div>
								</div>
							
							<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Address:</label>
									<input type="text" class="form-control form-control-sm" name="counselAddressAdd"
										id="counselAddressAdd" placeholder = "counsel Address">
								  </div>
								</div>
								
								<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Mobile:</label>
									<input type="text" class="form-control form-control-sm" name="mobileAdd"
										id="mobileAdd" placeholder = "mobile">
								  </div>
								</div>
								
								
								<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Email ID:</label>
									<input type="email" class="form-control form-control-sm" name="emailIDAdd"
										id="emailIDAdd" placeholder = "Email">
								  </div>
								</div>
								
								
								<div class="col-md-6">
								 <div class="group form-group">
									<label for="text">Telephone Number:</label>
									<input type="text" class="form-control form-control-sm" name="telephoneNumberAdd"
										id="telephoneNumberAdd" placeholder = "telephoneNumber">
								  </div>
								</div>
								
							<div class="col-md-6">
								<div class="group form-group">
									<label for="text">Remark:</label>
									<input type="text" class="form-control form-control-sm" name="remarkAdd"
										id="remarkAdd" placeholder = "remarkAdd">
								  </div>
							</div>
							
							
							
				</div>
<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="counselAdd"
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

	<div class="modal fade" id="myCounselEdit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" align="center">Edit counsel</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form id="counselEdit">
						<div class="row">
							<div class="col-md-6">
								 <div id="formselecttitleEdit" class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Title:</label>
									
									<select class="form-control  form-control-sm select2"
										id="selecttitleEdit" name="selecttitleEdit">
										<option value="">Select Title</option>
										<option value="Smt">Smt</option>
										<option value="Shri">Shri</option>
										<option value="Smt">Smt</option>
										<option value="Ms">Ms</option>
										<option value="Mr">Mr</option>
									</select>
									
								  </div>
								</div>
								
							<div class="col-md-6">
								 <div id="formcounselNameEdit" class="group form-group">
									<label for="text">Name:</label>
									<input type="text" class="form-control form-control-sm"  name="counselNameEdit"
										id="counselNameEdit" placeholder = "counsel Name">
								  </div>
								</div>
								
								
								<div class="col-md-6">
								 <div id="formcourtIDEdit" class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Court ID:</label>
									<select class="form-control  form-control-sm select2"
										id="courtIDEdit" name="courtIDEdit" onChange="counselTypeId(this.value)">
									</select>
								  </div>
								</div>
								
								
								
								<div class="col-md-6">
								 <div id="formcounselTypeIDEdit" class="group form-group has-success bmd-form-group is-filled">
									<label for="text">Counsel Type ID:</label>
									<select class="form-control  form-control-sm select2"
										id="counselTypeIDEdit" name="counselTypeIDEdit">
									</select>
								  </div>
								</div>
								
									<div class="col-md-6">
								 <div id="formtermFromEdit" class="group form-group">
									<label for="text">Term From</label>
									<input type="text" class="form-control form-control-sm" 
									name="termFromEdit"
										id="termFromEdit" placeholder = "Term From">
								  </div>
								</div>
							
							
							<div class="col-md-6">
								 <div id="formtermUptoEdit" class="group form-group">
									<label for="text">Term Upto</label>
									
									<input type="text" class="form-control form-control-sm" 
									name="termUptoEdit"
										id="termUptoEdit" placeholder = "Term Upto">
								  </div>
								</div>
							
							<div class="col-md-6">
								 <div id="formcounselAddressEdit" class="group form-group">
									<label for="text">Address:</label>
									<input type="text" class="form-control form-control-sm" name="counselAddressEdit"
										id="counselAddressEdit" placeholder = "counsel Address">
								  </div>
								</div>
								
								<div class="col-md-6">
								 <div id="formmobileEdit" class="group form-group">
									<label for="text">Mobile:</label>
									<input type="text" class="form-control form-control-sm" name="mobileNumberEdit"
										id="mobileNumberEdit" placeholder = "mobile">
								  </div>
								</div>
								
								
								<div class="col-md-6">
								 <div id="formemailIDEdit" class="group form-group">
									<label for="text">Email ID:</label>
									<input type="email" class="form-control form-control-sm" name="emailIDEdit"
										id="emailIDEdit" placeholder = "Email">
								  </div>
								</div>
								
								
								<div class="col-md-6">
								 <div id="formtelephoneNumberEdit" class="group form-group">
									<label for="text">Telephone Number:</label>
									<input type="text" class="form-control form-control-sm" name="telephoneNumberEdit"
										id="telephoneNumberEdit" placeholder = "telephoneNumber">
								  </div>
								</div>
								
							<div class="col-md-6">
								<div id="formremarkEdit" class="group form-group">
									<label for="text">Remark:</label>
									<input type="text" class="form-control form-control-sm" name="remarkEdit"
										id="remarkEdit" placeholder = "remark">
								  </div>
							</div>
							
										<input type = "hidden" id="counselIdhidden">
							</div>
							<div class="row">
							<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button"
									 value="Save!" id="counselEditsave"
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