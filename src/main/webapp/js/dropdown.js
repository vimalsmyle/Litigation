/**
 * 
 */
$(function() {
	var id =-1;
	ministry(id);
	//department(id);
	courtType(id);
	
	$.getJSON("./courts", function(data) {
		$('#courtIDAdd').empty();
		var Options = "<option value='-1'>Select Court Type</option>";
		
		$.each(data.dropDownCourts, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#courtIDAdd').append(Options);
		$('#courtIDEdit').append(Options);
		
	});
	
});

function ministry(id){
	$.getJSON("./ministries", function(data) {
		var Options = "<option value='-1'>Select  Ministry Name</option>";
		
		$.each(data.dropDownMinistries, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#departmentMinistryNameAdd').append(Options);
		$('#departmentMinistryNameEdit').append(Options);
		$('#ministryID').append(Options);
	});
}

function counselTypeId(id){
	$.getJSON("./counsels/"+id, function(data) {
		$('#counselTypeIDAdd').empty();
		//$('#counselTypeIDEdit').empty();
		var Options = "<option value='-1'>Select Counsel Type Id</option>";
		
		$.each(data.dropDownCounsels, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#counselTypeIDAdd').append(Options);
		$('#counselTypeIDEdit').append(Options);
		
	});
}

function courtType(id){
	$.getJSON("./courts", function(data) {
		$('#courtType').empty();
		var Options = "<option value='-1'>Select Court Type</option>";
		
		$.each(data.dropDownCourts, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#courtType').append(Options);
		
	});
}


function caseofId(id){
	
	if(id==1){
		$("#frNumber").val('');
		$("#frYear").val(-1).change();
		$("#frNumber").prop("disabled",false);
		$("#frYear").prop("disabled",false);
		
	}
	else{
		$("#frNumber").prop("disabled",true);
		$("#frYear").prop("disabled",true);
	}
	
	$.getJSON("./cases/"+id, function(data) {
		$('#caseTypeID').empty();
		var Options = "<option value='-1'>Select Cases Id</option>";
		
		$.each(data.dropDownCases, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#caseTypeID').append(Options);
		
	});
	$.getJSON("./counsels/"+id, function(data) {
		$('#counselID').empty();
		var Options = "<option value='-1'>Select counsel</option>";
		
		$.each(data.dropDownCounsels, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#counselID').append(Options);
		
	});
	
	
}

function department(id){
	
	$('#departmentID').empty();
	$.getJSON("./departments/"+id, function(data) {
		$('#departmentID').empty();
		var Options = "<option value='-1'>Select  Department Id</option>";
		
		$.each(data.dropDownDepartments, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#departmentID').append(Options);
		
	});
}

function CounselIdRecord(id){
	if(id==34){
		$("#counselOnRecordID").prop("disabled",false);
	}else{
		$("#counselOnRecordID").prop("disabled",true);
	}
	
	$.getJSON("./counsels/"+id, function(data) {
		$('#departmentID').empty();
		var Options = "<option value='-1'>Select Counsel Id Record</option>";
		
		$.each(data.dropDownDepartments, function(key, value) {
			Options = Options + "<option value='" + key + "'>" + value
					+ "</option>";
		});
		$('#counselOnRecordID').append(Options);
		
	});
}

/*filedByTitle*/
var filledTitle = ["DR", "MS", "M/S", "SHRI", "SMT"];
var option = "<option value='-1'>Select Title</option>";;
for (var i=0;i<filledTitle.length;i++){
   option += '<option value="'+ filledTitle[i] + '">' + filledTitle[i] + '</option>';
}
$('#filedByTitle').append(option);
/*filedByTitle*/
/* Yrs List*/
function generateArrayOfYears() {
	  var max = new Date().getFullYear()
	  var min = max - 19
	  var years = []

	  for (var i = max; i >= min; i--) {
	    years.push(i)
	  }
	  return years
	}

var years = generateArrayOfYears();
//var years = [years];
var option = "<option value='-1'>Select Year</option>";
for (var i=0;i<years.length;i++){
   option += '<option value="'+ years[i] + '">' + years[i] + '</option>';
}
$('#fileYear').append(option);
$('#caseYear').append(option);
$('#frYear').append(option);
/* Yrs List*/





