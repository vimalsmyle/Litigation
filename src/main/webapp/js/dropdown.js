/**
 * 
 */



$(function() {
	var id =-1;
	ministry(id);
	
	
	
	
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
	});
}



