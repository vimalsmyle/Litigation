<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css"
	rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
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
		} else {
	%>

	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="row mt-5 mb-5">
			<div class="col-md-12 mt-5 mb-5">
				<div class="card">
					<div class="card-header">HCRegister Form</div>
					<div class="card-body">
						<form id="hcRegForm">
							<div class="row">
								<!-- <div class="col-md-4">
								 <div class="group form-group">
									<label for="text">HighCourtID:</label>
									<input type="text" class="form-control  form-control-sm select2"  name="highCourtID"
										id="highCourtID" placeholder = "HighCourtID">
								  </div>
								</div>
							<div class="col-md-4">
								 <div class="group form-group">
									<label for="text">CatID:</label>
									<input type="text" class="form-control form-control-sm"  name="catID"
										id="catID" placeholder = "CatID">
								  </div>
								</div> -->

								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">CourtType:</label> <select
											class="form-control  form-control-sm select2" id="courtType"
											name="courtType" onChange="caseofId(this.value)">
										</select>
									</div>
								</div>


								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Case Type:</label>  <input type="text"
											class="form-control  form-control-sm select2" id="caseType"
											name="caseType" />
										
									</div>
								</div>


								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">File Number:</label> <input type="text"
											class="form-control form-control-sm" name="fileNumber"
											id="fileNumber" placeholder="File Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">File Year:</label> <select
											class="form-control  form-control-sm select2" id="fileYear"
											name="fileYear">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">Filed By Title:</label> <select
											class="form-control  form-control-sm select2"
											id="filedByTitle" name="filedByTitle">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Filed By:</label> <input type="text"
											class="form-control form-control-sm" name="filedBy"
											id="filedBy" placeholder="Filed By">
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Filed Against:</label> <input type="text"
											class="form-control form-control-sm" name="filedAgainst"
											id="filedAgainst" placeholder="Filed Against">
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">case Type ID:</label> <select
											class="form-control  form-control-sm select2" id="caseTypeID"
											name="caseTypeID">
										</select>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Case Number:</label> <input type="text"
											class="form-control form-control-sm" name="caseNumber"
											id="caseNumber" placeholder="Case Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">Case Year:</label> <select
											class="form-control  form-control-sm select2" id="caseYear"
											name="caseYear">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Fr Number:</label> <input type="text"
											class="form-control form-control-sm" name="frNumber"
											id="frNumber" placeholder="Fr Number">
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">Fr Year:</label> <select
											class="form-control  form-control-sm select2" id="frYear"
											name="frYear">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Number Of Cases:</label> <input type="text"
											class="form-control form-control-sm" name="numberOfCases"
											id="numberOfCases" placeholder="Number Of Cases">
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">Ministry ID:</label> <select
											class="form-control  form-control-sm select2" id="ministryID"
											name="ministryID" onChange="department(this.value)">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">Department ID:</label> <select
											class="form-control  form-control-sm select2"
											id="departmentID" name="departmentID">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group has-feedback has-success">
										<label for="text">Counsel ID:</label> <select
											class="form-control  form-control-sm select2" id="counselID"
											name="counselID" onChange="CounselIdRecord(this.value)">
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Counsel On Record ID:</label> <select
											class="form-control  form-control-sm select2"
											id="counselOnRecordID" name="counselOnRecordID" disabled>
										</select>
									</div>
								</div>
								<div class="col-md-4">
									<div class="group form-group"> 
										<label for="text">Remarks:</label>
										<textarea class="form-control form-control-sm" name="remarks"
											id="remarks"></textarea>
									</div>
								</div>

							</div>
							<div class="row">
								<div class="col-md-4">

									<label for="text"></label>
									<div class="form-check">
										<label class="form-check-label"> <input
											type="checkbox" id="oldCheck" class="form-check-input"
											value="true">Old
										</label>
									</div>
								</div>

								<div class="col-md-4">
									<div class="group form-group">
										<label for="text">Respondents:</label> <input type="text"
											class="form-control form-control-sm" name="respondents"
											id="respondents" placeholder="Respondents">
									</div>
								</div>
								<div class="col-md-4">
									<label for="text"></label>
									<div class="form-check">

										<label class="form-check-label"> <input
											type="checkbox" class="form-check-input"  id="enclosure" value="true">Enclosure
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-check form-group">
										<label class="form-check-label"> <input
											type="checkbox" class="form-check-input" id="reference"
											value="true">Reference
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 hideShow">
									<div class="group form-group">
										<label for="text">Reference Date:</label> <input type="text"
											class="form-control form-control-sm"
											data-date-format="dd/mm/yyyy" name="referenceDate"
											id="referenceDate" placeholder="Reference Date">
										<!--  <input data-date-format="dd/mm/yyyy" id="referenceDate"> -->
									</div>
								</div>
								<div class="col-md-4 hideShow">
									<div class="group form-group">
										<label for="text">Reference Number:</label> <input type="text"
											class="form-control form-control-sm" name="referenceNumber"
											id="referenceNumber" placeholder="Reference Number">
									</div>

								</div>
								<div class="col-md-4 hideShow">
									<div class="group form-group has-feedback has-success">
										<label for="text">Reference From:</label> <select
											class="form-control  form-control-sm select2"
											id="referenceFrom" name="referenceFrom">
											<option value="-1">Select Reference From</option>
											<option value="department">Department</option>
											<option value="other">Other</option>
										</select>
									</div>
								</div>
								<div class="col-md-4 hideShow">
									<div class="group form-group has-feedback has-success">
										<label for="text">Reference Type:</label> <select
											class="form-control  form-control-sm select2"
											id="referenceType" name="referenceType">
											<option value="-1">Select Reference Type</option>
											<option value="letter">Letter</option>
											<option value="email">Email</option>
										</select>
									</div>
								</div>
								<!-- <div class="col-md-4">
							<div class="group form-group">
									<label for="text">Renominated CounselID:</label>
									<select class="form-control  form-control-sm select2" id="renominatedCounselID" name="renominatedCounselID">
									</select>
							 </div>
						</div> -->
							</div>
							<div class="row">
								<div class="col-md-12 text-right">
									<button class="btn btn-primary submit-button" value="Save!"
										id="hcRegSubmit" type="button">Submit</button>

								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>





	<jsp:include page="footer.jsp" />
	<%
		}
	%>
	<script type="text/javascript"
		src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="js/dropdown.js"></script>
	<script src="js/rcReg.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		$('#referenceDate').datepicker({
			weekStart : 1,
			daysOfWeekHighlighted : "6,0",
			autoclose : true,
			todayHighlight : true,
		});
		$('#referenceDate').datepicker("setDate", new Date());
	</script>
</body>
</html>