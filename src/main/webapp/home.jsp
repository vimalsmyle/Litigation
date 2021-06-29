<!doctype html>
<html lang="en">
  <head>
    
    <meta charset="utf-8">
  
	<title>Home</title>
  </head>
  <body>
	<jsp:include page="header.jsp" />
<div class="container">
	<div class="row mt-5 mb-5">
		<div class="col-md-12 mt-5 mb-5">
		
		</div>
	</div>
</div>
	<jsp:include page="footer.jsp" />

<script>
$(document).ready(function() {
    var table = $('#datetable').DataTable( {
			fixedHeader: true,
					language: {
				paginate: {
				  next: '<i class="fa fa-angle-right"></i>', 
				  previous: '<i class="fa fa-angle-left"></i>;'
				}
			  }
		} );
} );
</script>
</body>
</html>