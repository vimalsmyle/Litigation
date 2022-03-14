
/**
 * 
 */


$(document).ready(function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
table = $('#counselTable')
.DataTable(
{
"dom": dom1,
"responsive" : true,
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
"scrollX" : true,
"ajax" : {
"url":"./counsel",
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
"data" : "name"
},{
"data" : "counselType"
},{
"data" : "address"
},{
"data" : "mobileNumber"
},{
"data" : "emailID"
},{
"data" : "telephoneNumber"
},{
"data" : "courtName"
}
,{
	"mData" : "action",
	"render" : function(data, type, row) {
		
		/*<button type="button"
			class="btn btn-raised btn-primary float-right"
			data-toggle="modal" data-target="#exampleModal">
			<i class="fa fa-user"></i>
		</button>*/
	//return "<a href='#communityEditModal' class='teal modal-trigger' data-toggle='modal' data-target='#communityEditModal' id='communityEditModal' onclick='getSocietyFormEdit("+row.communityID+")'><i class='material-icons' style='color:#17e9e9'>edit</i></a>"
		
		return "<div id = actionfield> <a href=# id=CounselEdit data-toggle=modal data-target=#myCounselEdit onclick='getCounselFormEdit("
																	+ row.counselID
																	+ ")'>"
																	+ "<i class='fa fa-edit'></i>"
																	+ "</a>"
																	/*+"<a onclick='getCounselFormDelete("
																	+ row.counselID
																	+ ")'>"
																	+ "<i class='fa fa-trash'></i>"
																	+ "</a>*/+"</div>"
	}
	}



],
"columnDefs" : [ {
//	orderable : false,
	targets: 5, visible: (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && (((sessionStorage.getItem("roleID") == 1) || (sessionStorage.getItem("roleID") == 2)) && !(sessionStorage.getItem("roleID") == 3) || !(sessionStorage.getItem("roleID") == 4) || !(sessionStorage.getItem("roleID") == 5)))
},
{
	"className": "dt-center", "targets": "_all"
}], "buttons": [
	   
	]
});

$("div.headname").html('<h3>Counsel Management</h3>');

$("div.addevent").html('<button type="button" id="counselAddButton"'
		+'class="btn btn-raised btn-primary float-right"'
			+'data-toggle="modal" data-target="#exampleModal">'
			+'<i class="fa fa-user-plus"></i>'
			+'</button>');

});






$(document)
				.ready(
						function() {
							$('#counselDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													counselNameAdd : {
														message : 'Counsel Name is not valid',
														validators : {
															notEmpty : {
																message : 'Counsel Name is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Counsel Name must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
																message : 'Counsel Name can only consist of Alphanumaric'
															}
														}
													},
													/*
													termFromAdd : {
														message : 'Term From is not valid',
														validators : {
															 notEmpty: {
														          message: 'Term From is required and cannot be empty'
														           },
														           date: {
														          message: 'The value is not a valid date',
														          format: 'yyyy-mm-dd'
														           }
														}
													},
													
													termUptoAdd : {
														message : 'Term Upto is not valid',
														validators : {
															 notEmpty: {
														          message: 'Term Upto is required and cannot be empty'
														           },
														           date: {
														          message: 'The value is not a valid date',
														          format: 'yyyy-mm-dd'
														           }
														}
													},*/
													
													counselAddressAdd : {
														message : 'Address is not valid',
														validators : {
															notEmpty : {
																message : 'Address is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Address must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]+$/,
																message : 'Address can only consist of alphabetical'
															}
														}
													},
													mobileAdd : {
														message : 'Mobile is not valid',
														validators : {
															notEmpty : {
																message : 'Mobile is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'Mobile can only consist of number'
															}
														}
													},
													emailIDAdd : {
														message : 'Email is not valid',
														validators : {
															notEmpty : {
																message : 'Email is required and cannot be empty'
															}/*,
															regexp : {
																regexp : /^[a-zA-Z0-9]+$/,
																message : 'Community Address can only consist of alphabetical and number'
															}*/
														}
													},
													telephoneNumberAdd : {
														message : 'Telephone is not valid',
														validators : {
															notEmpty : {
																message : 'Telephone is required and cannot be empty'
															},
															regexp : {
																regexp : /^[0-9]{10}$/,
																message : 'Telephone can only consist of number'
															}
														}
													},
													
													remarkAdd : {
														message : 'Remark is not valid',
														validators : {
															notEmpty : {
																message : 'Remark is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Remark must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]+$/,
																message : 'Remark can only consist of alphabetical'
															}
														}
													}
												}
											});
							
							
							
							
							$('#counselEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											counselNameEdit : {
												message : 'Counsel Name is not valid',
												validators : {
													notEmpty : {
														message : 'Counsel Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Counsel Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Counsel Name can only consist of Alphanumaric'
													}
												}
											},
											
											/*termFromEdit : {
												message : 'Term From is not valid',
												validators : {
													notEmpty : {
														message : 'Term From is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Term From must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Term From can only consist of Alphanumaric'
													}
												}
											},
											
											termUptoEdit : {
												message : 'Term Upto is not valid',
												validators : {
													notEmpty : {
														message : 'Term Upto is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Term Upto must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Term Upto can only consist of Alphanumaric'
													}
												}
											},*/
											
											counselAddressEdit : {
												message : 'Address is not valid',
												validators : {
													notEmpty : {
														message : 'Address is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Address must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]+$/,
														message : 'Address can only consist of alphabetical'
													}
												}
											},
											mobileNumberEdit : {
												message : 'Mobile is not valid',
												validators : {
													notEmpty : {
														message : 'Mobile is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'Mobile can only consist of number'
													}
												}
											},
											emailIDEdit : {
												message : 'Email is not valid',
												validators : {
													notEmpty : {
														message : 'Email is required and cannot be empty'
													}/*,
													regexp : {
														regexp : /^[a-zA-Z0-9]+$/,
														message : 'Community Address can only consist of alphabetical and number'
													}*/
												}
											}
											,
											telephoneNumberEdit : {
												message : 'Telephone is not valid',
												validators : {
													notEmpty : {
														message : 'Telephone is required and cannot be empty'
													},
													regexp : {
														regexp : /^[0-9]{10}$/,
														message : 'Telephone can only consist of number'
													}
												}
											},
											
											remarkEdit : {
												message : 'Remark is not valid',
												validators : {
													notEmpty : {
														message : 'Remark is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Remark must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]+$/,
														message : 'Remark can only consist of alphabetical'
													}
												}
											}
										}
									});
							
							
							
							

							$('#counselDetails')
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
																	false).attr('class', 'btn btn-success submit-button');;
												} else {
													$('.submit-button', $(this))
															.attr('disabled',
																	true);
												}
											});

							
							
							
							$('#counselEdit').on(
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
											$('#counselEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#counselEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
							
												$(document).on('click', '#counselAdd', function () {
													if($("#selecttitleAdd").val() == -1 || $("#selecttitleAdd").val() == null || $("#selecttitleAdd").val() == ""){
														bootbox
														.alert("Select Title");
														return false;
													}
													
												if($("#courtIDAdd").val() == -1 || $("#courtIDAdd").val() == null || $("#courtIDAdd").val() == ""){
													bootbox
													.alert("Select Court Type");
													return false;
												}
												
												if($("#counselTypeIDAdd").val() == -1 || $("#counselTypeIDAdd").val() == null || $("#counselTypeIDAdd").val() == ""){
													bootbox
													.alert("Select Court Type");
													return false;
												}
												
												if($("#termFromAdd").val() == -1 || $("#termFromAdd").val() == null || $("#termFromAdd").val() == ""){
													bootbox
													.alert("Select Term Form");
													return false;
												}
												
												if($("#termUptoAdd").val() == -1 || $("#termUptoAdd").val() == null || $("#termUptoAdd").val() == ""){
													bootbox
													.alert("Select Term Upto");
													return false;
												}
												
												var data1 = {}
												data1["title"] = $("#selecttitleAdd").val();
												data1["courtID"] = $("#courtIDAdd").val();
												data1["counselTypeID"] = $("#counselTypeIDAdd").val();
												data1["name"] = $("#counselNameAdd").val();
												data1["termFrom"] = $("#termFromAdd").val();
												data1["termUpto"] = $("#termUptoAdd").val();
												data1["address"] = $("#counselAddressAdd").val();
												data1["mobileNumber"] = $("#mobileAdd").val();
												data1["emailID"] = $("#emailIDAdd").val();
												data1["telephoneNumber"] = $("#telephoneNumberAdd").val();
												data1["remarks"] = $("#remarkAdd").val();

												$('#counselAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												/*console.log("===>"
														+ JSON.stringify(data1));*/
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./counsel/add",
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
																		    window.location = "counsel.jsp";
																		    
																		});
																	return false;
																
																	

																} else if(data.result == "Failure"){
									
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "counsel.jsp";
																		    
																		});
																	return false;
																	
																	
																}else {
																	
																	swal.fire({
																		  title: "error",
																		  text: "Something went Wrong",
																		  icon: "error"
																		}).then(function() {
																		    window.location = "counsel.jsp";
																		    
																		});
																	
																}
															}
														});
												return false;
											});
							
							
							
							$("#counselEditsave")
							.click(
									function() {

										if($("#selecttitleEdit").val() == -1 || $("#selecttitleEdit").val() == null || $("#selecttitleEdit").val() == ""){
											bootbox
											.alert("Select Title");
											return false;
										}
										
									if($("#courtIDEdit").val() == -1 || $("#courtIDEdit").val() == null || $("#courtIDEdit").val() == ""){
										bootbox
										.alert("Select Court Type");
										return false;
									}
									
									if($("#counselTypeIDEdit").val() == -1 || $("#counselTypeIDEdit").val() == null || $("#counselTypeIDEdit").val() == ""){
										bootbox
										.alert("Select Counsel Type");
										return false;
									}
									
									
									if($("#termFromEdit").val() == -1 || $("#termFromEdit").val() == null || $("#termFromEdit").val() == ""){
										bootbox
										.alert("Select Term Form");
										return false;
									}
									
									if($("#termUptoEdit").val() == -1 || $("#termUptoEdit").val() == null || $("#termUptoEdit").val() == ""){
										bootbox
										.alert("Select Term Upto");
										return false;
									}
									
										var data1 = {}
										data1["title"] = $("#selecttitleEdit").val();
										data1["courtID"] = $("#courtIDEdit").val();
										data1["counselTypeID"] = $("#counselTypeIDEdit").val();
										data1["name"] = $("#counselNameEdit").val();
										data1["termFrom"] = $("#termFromEdit").val();
										data1["termUpto"] = $("#termUptoEdit").val();
										data1["address"] = $("#counselAddressEdit").val();
										data1["mobileNumber"] = $("#mobileNumberEdit").val();
										data1["emailID"] = $("#emailIDEdit").val();
										data1["telephoneNumber"] = $("#telephoneNumberEdit").val();
										data1["remarks"] = $("#remarkEdit").val();
								
										/*alert("===>"
												+ JSON.stringify(data1));*/
										$('#counselEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./counsel/edit/"+$("#counselIdhidden").val(),
													data : JSON
															.stringify(data1),
													dataType : "JSON",

													success : function(
															data) {
														/*alert("data"
																+ JSON
																		.stringify(data));*/
														if (data.result == "Success") {

															/*alert( "data"
																	+ data.result);*/
															
															swal.fire({
																  title: "Saved",
																  text: data.Message,
																  icon: "success"
																}).then(function() {
																    window.location = "counsel.jsp";
																    
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "counsel.jsp";
																    
																});
															return false;
															//});
														}
													}
												});
										return false;
									});
							
							
						});




function getCounselFormEdit(id) {

 // alert(id);

	$.getJSON("./counsel", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.counselID) {
				
				$('#selecttitleEdit').val(item.title).trigger("change");
				$("#formselecttitleEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				
				$('#counselNameEdit').val(item.name).trigger("change");
				$("#formcounselNameEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$('#courtIDEdit').val(item.courtId).trigger("change");
				$("#formcourtIDEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				//counselTypeId(item.courtId);
				$('#counselTypeIDEdit').val(item.counselTypeId).trigger("change");
				$("#formcounselTypeIDEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#counselAddressEdit').val(item.address).trigger("change");
				$("#formcounselAddressEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
				$("#formtermFromEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$("#formtermUptoEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				
			var date = new Date();
            var currentMonth = date.getMonth();
            var currentDate = date.getDate();
            var currentYear = date.getFullYear();
			
			 $('#termFromEdit').datepicker({
                 autoclose: true,
                 format: "yyyy-mm-dd"
             });
			 
			 $('#termUptoEdit').datepicker({
                 autoclose: true,
                 format: "yyyy-mm-dd"
             });
				
			    $('#mobileNumberEdit').val(item.mobileNumber).trigger("change");
				$("#formmobileEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#emailIDEdit').val(item.emailID).trigger("change");
				$("#formemailIDEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled");
				
				$('#telephoneNumberEdit').val(item.telephoneNumber).trigger("change");
				$("#formtelephoneNumberEdit").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				
				$("#counselIdhidden").val(item.counselID);
			
				$('#counselEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myCounselEdit').modal('show');
	});
}


/*
function getCounselFormDelete(counselId){
	
	swal.fire({
		  title: "Are you sure?",
		  text: "ARE YOU SURE TO DELETE COUNSEL!",
		  icon: "warning",
		  showCancelButton: true,
		  confirmButtonClass: "btn-danger",
		  confirmButtonText: "Yes, delete it!",
		  cancelButtonText: "No, cancel plz!",
		  closeOnConfirm: false,
		  closeOnCancel: false
		}).then(
		function(isConfirm) {
		  if (isConfirm.isConfirmed) {
			  
			  $.ajax({
					type : "POST",
					contentType : "application/json",
					url : "./counsel/delete/" + counselId,
					dataType : "JSON",
					success : function(data) {
						//alert("Success====" + data.result);
						if (data.result == "Success") {
							swal.fire({
								  title: "Deleted",
								  text: data.Message,
								  icon: "success"
								}).then(function() {
								    window.location = "counsel.jsp";
								});
							return false;

						} else {
							swal.fire({
								  title: "error",
								  text: data.Message,
								  icon: "error"
								}).then(function() {
								    window.location = "counsel.jsp";
								    
								});
							return false;
						}
					}
				});
		  } else {
		    swal.fire("Cancelled", "Your Counsel Details are safe :)", "error");
		  }
		});

	
}*/