/**
 * 
 */


$(window).on('load',function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
	
table = $('#departmentTable')
.DataTable(
{
	
	"dom": dom1,
	/*"processing" : true,*/
	"serverSide" : false,
	"bDestroy" : true,
	"bPaginate": true,
	"pagging" : true,
	"bProcessing" : true,
	"ordering" : true,
	"order" : [ 0, "desc" ],
	"lengthMenu" : [ 5, 10, 25, 30, 50, 75 ],
	"pageLength" : 5,
	"scrollX" : false,
"ajax" : {
"url":"./department",
"type" : "GET",
"data" : function(search) {
},
"complete" : function(json) {
	console.log(json);
return json.data;
},
},
"columns" : [
	{
		"data" : "departmentName"
		},{
"data" : "ministryName"
},{
"data" : "address"
},{
"data" : "typeOfAddress"
},{
"data" : "date"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		return "<div id=tdfiled><a href=# id=DepartmentEdit data-toggle=modal data-target=#myDepartmentEdit onclick='getDepartmentFormEdit("
																	+ row.departmentID
																	+ ")'>"
																	+ "<i class='fa fa-edit'></i>"
																	+ "</a></div>"
	}
	}



],
"columnDefs" : [ 
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
   
],
language: {
    paginate: {
      next: '<i class="fa fa-angle-right"></i>', // or '→'
      previous: '<i class="fa fa-angle-left"></i>;' // or '←' 
    }
  }

});
$("div.headname").html('<h3>Department Details</h3>');

$("div.addevent").html('<button type="button" id="departmentpopup"' 
		+'class="btn btn-raised btn-primary float-right"'
		+'	data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-user-plus"></i>'
		+'</button>');


});






$(window).on('load', 
						function() {
								$('#departmentDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													departmentNameAdd : {
														message : 'Name is not valid',
														validators : {
															notEmpty : {
																message : 'Name is required and cannot be empty'
															},
															stringLength : {
																min : 6,
																max : 30,
																message : 'Name must be more than 6 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]*$/,
																message : 'Name can only consist of alphabet'
															}
														}
													},
													departmentAddressAdd : {
														message : 'Address is not valid',
														validators : {
															notEmpty : {
																message : ' Address is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'Address must be more than 6 and less than 30 characters long'
															}
														}
													},
													departmentTypeOfAddressAdd : {
														message : 'Type Of address is not valid',
														validators : {
															notEmpty : {
																message : 'Type of Address is required and cannot be empty'
															},
															stringLength : {
																min : 2,
																max : 30,
																message : 'Type of Address must be more than 6 and less than 30 characters long'
															}
														}
													}
												}
											});
							
							
							
							
							$('#departmentEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											departmentNameEdit : {
												message : 'Name is not valid',
												validators : {
													notEmpty : {
														message : 'Name is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'Name must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]*$/,
														message : 'Name can only consist of alphabet'
													}
												}
											},
											departmentAddressEdit : {
												message : 'Address is not valid',
												validators : {
													notEmpty : {
														message : 'Address is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'Address must be more than 6 and less than 30 characters long'
													}
												}
											},
											departmentTypeOfAddressEdit : {
												message : 'Type Of address is not valid',
												validators : {
													notEmpty : {
														message : 'Type Address is required and cannot be empty'
													},
													stringLength : {
														min : 2,
														max : 30,
														message : 'Type Address must be more than 6 and less than 30 characters long'
													}
												}
											}
										}
									});
							
							
							
							

							$('#departmentDetails')
									.on(
											'status.field.bv',
											function(e, data) {
												formIsValid = true;
												$('.group.form-group', $(this))
														.each(
																function() {
																//	alert(this+"@@=>"+formIsValid);
																	formIsValid = formIsValid
																			&& $(
																					this)
																					.hasClass(
																							'has-success');
																	
																	//alert("!!@@=>"+formIsValid);
																});

												if (formIsValid) {
													$('.submit-button', $(this))
															.attr('disabled',
																	false);
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#departmentEdit').on(
									'status.field.bv',
									function(e, data) {
										formIsValid = true;
										$('.group.form-group', $(this))
												.each(
														function() {
														//	alert(this+"@@=>"+formIsValid);
															formIsValid = formIsValid
																	&& $(
																			this)
																			.hasClass(
																					'has-success');
															
															//alert("!!@@=>"+formIsValid);
															
														});

										if (formIsValid) {
											$('#departmentEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#departmentEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
												$(document).on('click', '#departmentAdd', function () {

												var data1 = {}
												if($("#departmentMinistryNameAdd").val() == -1 || $("#departmentMinistryNameAdd").val() == null || $("#departmentMinistryNameAdd").val() == "Select Ministry Name"){
													swal.fire({
														  title: "error",
														  text: "Select Ministry",
														  icon: "error"
														});
													return false;
												}
												data1["departmentName "] = $("#departmentNameAdd")
												.val();
												
												data1["ministryID"] = $("#departmentMinistryNameAdd")
														.val();
												data1["typeOfAddress"] = $("#departmentTypeOfAddressAdd").val();
												
												data1["address"] = $("#departmentAddressAdd").val();
										
												$('#departmentAdd').prop('disabled', true).addClass('disabled').off( "click" );
												alert(JSON
														.stringify(data1));
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./department/add",
															data : JSON
																	.stringify(data1),
															dataType : "JSON",

															success : function(
																	data) {
																
																if (data.result == "Success") {

																	/*alert( "data"
																			+ data.result);*/
																	swal.fire({
																		  title: "Saved",
																		  text: data.Message,
																		  icon: "success"
																		}).then(function() {
																		    window.location = "Department.jsp";
																		});
																	return false
																	

																} else if(data.result == "Failure"){
																	
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "Department.jsp";
																		});
																	return false;
																}
															}
														});
												return false;
											});
							
							
							
										$(document).on('click', '#departmentEditsave', function () {
											
										var data1 = {}
										
										if($("#departmentMinistryNameEdit").val() == -1 || $("#departmentMinistryNameEdit").val() == null || $("#departmentMinistryNameEdit").val() == "Select Ministry Name"){
											swal.fire({
												  title: "error",
												  text: "Select Ministry",
												  icon: "error"
												});
											return false;
										}
										
										data1["departmentName"] = $("#departmentNameEdit")
												.val();
										
										data1["ministryID"] = $("#departmentMinistryNameEdit")
										.val();
										
										data1["typeOfAddress"] = $("#departmentTypeOfAddressEdit").val();
										
										data1["address"] = $("#departmentAddressEdit").val();
								
										$('#departmentEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./department/edit/"+$("#departmentIdhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														
														if (data.result == "Success") {

														
															
															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "Department.jsp";
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "Department.jsp";
																    return false;
																});
															
														}
													}
												});
										return false;
									});
							
							
						});




function getDepartmentFormEdit(id) {

//	 alert(id);

	$.getJSON("./department", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.departmentID) {
				$('#departmentNameEdit').val(item.departmentName).trigger("change");
				$("#formdepartmentName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$('#departmentMinistryNameEdit').val(item.ministryName).trigger("change");
				$("#formdepartmentMinistryName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#departmentTypeOfAddressEdit').val(item.typeOfAddress).trigger("change");
				$("#formdepartmentTypeOfAddress").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#departmentAddressEdit').val(item.address).trigger("change");
				$("#formdepartmentAddress").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$("#departmentIdhidden").val(item.departmentID);
				var ids = -1;
				ministry(ids);
				$('#departmentEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myDepartmentEdit').modal('show');
	});
}