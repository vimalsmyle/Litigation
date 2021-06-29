
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
"data" : "title"
},{
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
		
		return "<div id = actionfield> <a href=# id=BlockEdit data-toggle=modal data-target=#myBlockEdit onclick='getBlockFormEdit("
																	+ row.counselID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9'>edit</i>"
																	+ "</a>"
																	+"<a onclick='getBlockFormDelete("
																	+ row.counselID
																	+ ")'>"
																	+ "<i class='material-icons' style='color:#17e9e9;cursor:pointer;'>delete</i>"
																	+ "</a></div>"
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
							
							/*.find('[name="selectcommunityName"]')
            .selectpicker()
            .change(function(e) {
                $('#blockDetails').bootstrapValidator('revalidateField', 'selectcommunityName');
            })
            .end()*/
							
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													/*selectcommunityName: {
									                    validators: {
									                        notEmpty: {
									                            message: 'Please select your native language.'
									                        }
									                    }
									                },*/
													blockNameAdd : {
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
													blockLocationAdd : {
														message : 'Location is not valid',
														validators : {
															notEmpty : {
																message : 'Location is required and cannot be empty'
															},
															stringLength : {
																min : 4,
																max : 30,
																message : 'Locaton must be more than 4 and less than 30 characters long'
															},
															regexp : {
																regexp : /^[a-zA-Z ]+$/,
																message : 'Location can only consist of alphabetical'
															}
														}
													},
													blockMobileAdd : {
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
													blockEmailAdd : {
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
											blockNameEdit : {
												message : 'Block Name is not valid',
												validators : {
													notEmpty : {
														message : 'Block Name is required and cannot be empty'
													},
													stringLength : {
														min : 4,
														max : 30,
														message : 'Block Name must be more than 4 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
														message : 'Block Name can only consist of Alphanumaric'
													}
												}
											},
											blockLocationEdit : {
												message : 'Location is not valid',
												validators : {
													notEmpty : {
														message : 'Location is required and cannot be empty'
													},
													stringLength : {
														min : 6,
														max : 30,
														message : 'Location must be more than 6 and less than 30 characters long'
													},
													regexp : {
														regexp : /^[a-zA-Z ]+$/,
														message : 'Location can only consist of alphabetical'
													}
												}
											},
											blockMobileEdit : {
												message : 'Mobile is not valid',
												validators : {
													notEmpty : {
														message : 'Mobile is required and cannot be empty'
													},
													regexp : {
														regexp : /^\d{10}$/,
														message : 'Mobile can only consist of number'
													}
												}
											},
											blockEmailEdit : {
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
							
							
							
												$(document).on('click', '#blockAdd', function () {
													 
												
												//alert(""+$("#selectcommunityName").val());

												if($("#selectCourtType").val() == -1 || $("#selectCourtType").val() == null || $("#selectCourtType").val() == "Select Court Type"){
													bootbox
													.alert("Select Court Type");
													return false;
												}
												
												if($("#selectCounselType").val() == -1 || $("#selectCounselType").val() == null || $("#selectCounselType").val() == "Select Counsel Type"){
													bootbox
													.alert("Select Court Type");
													return false;
												}
												
												var data1 = {}
												data1["courtType"] = $("#selectCourtType").val();
												data1["counselType"] = $("#selectCounselType").val();
												data1["name"] = $("#counselNameAdd").val();
												data1["termFrom"] = $("#termFromAdd").val();
												data1["termTo"] = $("#termtoAdd").val();
												data1["address"] = $("#addressAdd").val();
												data1["mobileNumber"] = $("#mobileNumberAdd").val();
												data1["emailID"] = $("#emailAdd").val();
												data1["telephoneNumber"] = $("#telephoneNumberAdd").val();
												data1["remarks"] = $("#remarksAdd").val();
												data1["createdByID"] = sessionStorage.getItem("createdByID");
												data1["loggedInUserID"] = sessionStorage.getItem("userID");
												data1["roleID"] = sessionStorage.getItem("roleID");

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

										var data1 = {}
										
										var data1 = {}
										data1["courtType"] = $("#selectCourtType").val();
										data1["counselType"] = $("#selectCounselType").val();
										data1["name"] = $("#counselNameAdd").val();
										data1["termFrom"] = $("#termFromAdd").val();
										data1["termTo"] = $("#termtoAdd").val();
										data1["address"] = $("#addressAdd").val();
										data1["mobileNumber"] = $("#mobileNumberAdd").val();
										data1["emailID"] = $("#emailAdd").val();
										data1["telephoneNumber"] = $("#telephoneNumberAdd").val();
										data1["remarks"] = $("#remarksAdd").val();
										data1["createdByID"] = sessionStorage.getItem("createdByID");
										data1["loggedInUserID"] = sessionStorage.getItem("userID");
										data1["roleID"] = sessionStorage.getItem("roleID");
								
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

	$.getJSON("./counsel/"+sessionStorage.getItem("roleID")+"/"+sessionStorage.getItem("ID"), function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.counselID) {
				
				$('#communityNameEdit').val(item.communityName).trigger("change");
				$("#formcomunityName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockNameEdit').val(item.blockName).trigger("change");
				$("#formblockName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockLocationEdit').val(item.Location).trigger("change");
				$("#formblocklocation").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
			    $('#blockMobileEdit').val(item.mobile).trigger("change");
				$("#formblockMobile").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#blockEmailEdit').val(item.email).trigger("change");
				$("#formblockEmail").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$("#blockIdhidden").val(item.blockID);
			
				$('#counselEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myCounselEdit').modal('show');
	});
}



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

	
}