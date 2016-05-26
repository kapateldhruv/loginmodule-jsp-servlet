/**
 * validation rules
 */
$(document).ready(function(){
	
	$("#adminloginfrm").validate({
		
		rules: {
			username: {
				required: true,
				email: true
			},
			password: {
				required: true,
			},
		},
		messages: {
			username: {
				required: "Please enter username.",
				email: "Please enter valid username.",
			},
			password: {
				required: "Please enter password.",
				
			},
		},
		errorElement: "em",
		errorPlacement: function ( error, element ) {
			// Add the `help-block` class to the error element
			error.addClass( "help-block" );

			if ( element.prop( "type" ) === "checkbox" ) {
				error.insertAfter( element.parent( "label" ) );
			} else {
				error.insertAfter( element );
			}
		},
		highlight: function ( element, errorClass, validClass ) {
			$( element ).parents( ".col-md-4" ).addClass( "has-error" ).removeClass( "has-success" );
		},
		unhighlight: function (element, errorClass, validClass) {
			$( element ).parents( ".col-md-4" ).addClass( "has-success" ).removeClass( "has-error" );
		}
		
	});
	
});

