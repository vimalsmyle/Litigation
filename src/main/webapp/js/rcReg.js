
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

$("div.addevent").html('<a href="HCRegister.jsp"><button type="button" id="counselAddButton"'
		+'class="btn btn-raised btn-primary float-right">'
			+'<i class="fa fa-user-plus"></i>'
			+'</button></a>');

});
/* Form Validation*/
$(document).ready(function() {
	$('#hcRegForm')
	.bootstrapValidator(
					{
						
						fields : {
							caseType : {
								message : 'case Type Name is not valid',
								validators : {
									notEmpty : {
										message : 'case Type is required and cannot be empty'
									},
									stringLength : {
										min : 4,
										max : 30,
										message : 'case Type must be more than 4 and less than 30 characters long'
									},
									regexp : {
										regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
										message : 'case Type can only consist of Alphanumaric'
									}
								}
							},
							fileNumber : {
								message : 'file Number is not valid',
								validators : {
									notEmpty : {
										message : 'file Number is required and cannot be empty'
									},
									stringLength : {
										min : 4,
										max : 30,
										message : 'file Number must be more than 4 and less than 30 characters long'
									},
									regexp : {
										regexp : /^[0-9]{10}$/,
										message : 'file Number can only consist of Number'
									}
								}
							},
							filedBy : {
								message : 'filed By is not valid',
								validators : {
									notEmpty : {
										message : 'filed By is required and cannot be empty'
									},
									regexp : {
										regexp : /^[a-zA-Z][a-zA-Z0-9.,$; ]+$/,
										message : 'filed By can only consist of number'
									}
								}
							},
							filedAgainst : {
								message : 'filed Against is not valid',
								validators : {
									notEmpty : {
										message : 'filed Against is required and cannot be empty'
									}
								}
							},
							caseNumber : {
								message : 'Case Number Against is not valid',
								validators : {
									notEmpty : {
										message : 'Case Number is required and cannot be empty'
									}
								}
							},
							frNumber : {
								message : 'fr Number Against is not valid',
								validators : {
									notEmpty : {
										message : 'fr Number is required and cannot be empty'
									}
								}
							},
							numberOfCases : {
								message : 'numberOfCases Against is not valid',
								validators : {
									notEmpty : {
										message : 'numberOfCases is required and cannot be empty'
									}
								}
							},
							remarks : {
								message : 'remarks Against is not valid',
								validators : {
									notEmpty : {
										message : 'remarks is required and cannot be empty'
									}
								}
							},
							respondents : {
								message : 'respondents Against is not valid',
								validators : {
									notEmpty : {
										message : 'respondents is required and cannot be empty'
									}
								}
							}
						}
					});
});

/* */
/*$('#hcRegForm').on('status.field.bv',function(e, data) {
			formIsValid = true;
			$('.group.form-group', $(this)).each(function() {
				formIsValid = formIsValid && $(this).hasClass('has-success');
			});
			if (formIsValid) {
				$('.submit-button', $(this)).attr('disabled',false).attr('class', 'btn btn-success submit-button');;
			} else {$('.submit-button', $(this)).attr('disabled',true);
			}
});*/
$("#hcRegSubmit").click(function() {
	var data1 = {}
	data1["courtType"] = $("#courtType").val();
	data1["fileNumber"] = $("#fileNumber").val();
	data1["fileYear"] = $("#fileYear").val();
	data1["filedByTitle"] = $("#filedByTitle").val();
	data1["filedBy"] = $("#filedBy").val();
	data1["filedAgainst"] = $("#filedAgainst").val();
	data1["caseTypeID"] = $("#caseTypeID").val();
	data1["caseType"] = $("#caseType").val();
	data1["caseNumber"] = $("#caseNumber").val();
	data1["caseYear"] = $("#caseYear").val();
	data1["frNumber"] = $("#frNumber").val();
	data1["frYear"] = $("#frYear").val();
	data1["numberOfCases"] = $("#numberOfCases").val();
	data1["ministryID"] = $("#ministryID").val();
	data1["departmentID"] = $("#departmentID").val();
	data1["counselID"] = $("#counselID").val();
	data1["counselOnRecordID"] = $("#counselOnRecordID").val();
	data1["remarks"] = $("#remarks").val();
	data1["old"] = $("#oldCheck").val()=="true"?true:false;
	data1["respondents"] = $("#respondents").val();
	data1["enclosure"] = $("#enclosure").val()=="true"?true:false;
	data1["reference"] = $("#reference").val()=="true"?true:false;
	if($("#reference").val()=="true"){
		data1["referenceDate"] =$("#referenceDate").val();
		data1["referenceNumber"] = $("#referenceNumber").val();
		data1["referenceFrom"] = $("#referenceFrom").val();
		data1["referenceType"] = $("#referenceType").val();
	}
	
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "./nomination/add",
		data : JSON.stringify(data1),
		dataType : "JSON",
		success : function(data) {

			if (data.result == "Success") {
				swal.fire({
					  title: "Saved",
					  text: data.Message,
					  icon: "success"
					}).then(function() {
					    window.location = "hcReg.jsp";
					    
					});
				return false;
			} else if(data.result == "Failure"){
				swal.fire({
					  title: "error",
					  text: data.Message,
					  icon: "error"
					}).then(function() {
					    window.location = "hcReg.jsp";
					    
					});
				return false;
			}else {
				
				swal.fire({
					  title: "error",
					  text: "Something went Wrong",
					  icon: "error"
					}).then(function() {
					    window.location = "hcReg.jsp";
					    
					});
				
			}
		}
	});
return false;
});
$(document).ready(function() {
$("#reference").click(function(){
	if($("#reference").is(":checked")==true){
		 $(".hideShow").show();
	}else{
		 $(".hideShow").hide();
	}
	 
	})
});