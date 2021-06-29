/**
 * 
 */


$(window).on('load',function() {
	if(sessionStorage.getItem("roleID") == 1){
		var dom1 = "<'row'<'col-sm-6 headname'><'col-sm-6'f>>" +"<'row'<'col-sm-4'B><'col-sm-4'l><'col-sm-4 addevent'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
	}else{
		var dom1 = "<'row'<'col-sm-4 headname'><'col-sm-2'><'col-sm-1'><'col-sm-2'f>>" +"<'row'<'col-sm-4'B><'col-sm-2'l><'col-sm-2'><'col-sm-2'><'col-sm-1'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-6 text-black'i><'col-sm-6 text-black'p>>"
		
	}
	
table = $('#ministryTable')
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
"url":"./ministry",
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
		
		return "<div id=tdfiled><a href=# id=MinistryEdit data-toggle=modal data-target=#myMinistryEdit onclick='getMinistryFormEdit("
																	+ row.ministryID
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
$("div.headname").html('<h3>Ministry Details</h3>');

$("div.addevent").html('<button type="button" id="ministrypopup"' 
		+'class="btn btn-raised btn-primary float-right"'
		+'	data-toggle="modal" data-target="#exampleModal">'
		+'<i class="fa fa-user-plus"></i>'
		+'</button>');


});






$(window).on('load', 
						function() {
								$('#ministryDetails')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													ministryNameAdd : {
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
													ministryAddressAdd : {
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
													ministryTypeOfAddressAdd : {
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
							
							
							
							
							$('#ministryEdit')
							.bootstrapValidator(
									{
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											ministryNameEdit : {
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
											ministryAddressEdit : {
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
											ministryTypeOfAddressEdit : {
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
							
							
							
							

							$('#ministryDetails')
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

							
							
							
							$('#ministryEdit').on(
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
											$('#ministryEditsave', $(this))
													.attr('disabled',
															false);
										} else {
											$('#ministryEditsave', $(this))
													.attr('disabled',
															true);
										}
									});
							
							
												$(document).on('click', '#ministryAdd', function () {

												var data1 = {}
												data1["ministryName"] = $("#ministryNameAdd")
														.val();
												data1["typeOfAddress"] = $("#ministryTypeOfAddressAdd").val();
												
												data1["address"] = $("#ministryAddressAdd").val();
										
												$('#ministryAdd').prop('disabled', true).addClass('disabled').off( "click" );
												
												$
														.ajax({
															type : "POST",
															contentType : "application/json",
															url : "./ministry/add",
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
																		    window.location = "Ministry.jsp";
																		});
																	return false
																	

																} else if(data.result == "Failure"){
																	
																	swal.fire({
																		  title: "error",
																		  text: data.Message,
																		  icon: "error"
																		}).then(function() {
																		    window.location = "Ministry.jsp";
																		});
																	return false;
																}
															}
														});
												return false;
											});
							
							
							
										$(document).on('click', '#ministryEditsave', function () {
											
										var data1 = {}
										data1["ministryName"] = $("#ministryNameEdit")
												.val();
										data1["typeOfAddress"] = $("#ministryTypeOfAddressEdit").val();
										
										data1["address"] = $("#ministryAddressEdit").val();
								
										$('#ministryEditsave').prop('disabled', true).addClass('disabled').off( "click" );
										
										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "./ministry/edit/"+$("#ministryIdhidden").val(),
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
																    window.location = "Ministry.jsp";
																});
															return false;
															

														} else if(data.result == "Failure"){
															
															swal.fire({
																  title: "error",
																  text: data.Message,
																  icon: "error"
																}).then(function() {
																    window.location = "Ministry.jsp";
																    return false;
																});
															
														}
													}
												});
										return false;
									});
							
							
						});




function getMinistryFormEdit(id) {

//	 alert(id);

	$.getJSON("./ministry", function(data) {
		$.each(data.data, function(i, item) {
			if (id == item.ministryID) {
				$('#ministryNameEdit').val(item.ministryName).trigger("change");
				$("#formministryName").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#ministryTypeOfAddressEdit').val(item.typeOfAddress).trigger("change");
				$("#formministryTypeOfAddress").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$('#ministryAddressEdit').val(item.address).trigger("change");
				$("#formministryAddress").addClass("group form-group has-feedback has-success bmd-form-group is-filled")
				$("#ministryIdhidden").val(item.ministryID);
			
				$('#ministryEditsave')
				.attr('disabled',
						false);
				
			} else {
			}
		});
		$('#myMinistryEdit').modal('show');
	});
}