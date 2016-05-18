$(function() {

	// Load signature images lists
	$
			.ajax({
				type : 'GET',
				url : 'http://localhost:8080/SignatureComparisonModule/jaxrs/getClientSignaturesList',
				async : true,
				dataType : 'json',
				success : function(resp) {
					var data = resp.data;
					$.each(data, function(i, obj) {
						$('#img1_dropdown_options').append(
								$("<option></option>").attr("value", obj.name)
										.text(obj.name));
					});
				}
			});

	// Load check images lists
	$
			.ajax({
				type : 'GET',
				url : 'http://localhost:8080/SignatureComparisonModule/jaxrs/getChecksList',
				async : true,
				dataType : 'json',
				success : function(resp) {
					$.each(resp.data, function(i, obj) {
						$('#img2_dropdown_options').append(
								$("<option></option>").attr("value", obj.name)
										.text(obj.name));
					});
				}
			});

	// Display image (Check)
	getAndDisplayImage('img2_dropdown_options', 'check', 'img2_img');

	// Get and display image (Signature)
	getAndDisplayImage('img1_dropdown_options', 'client', 'img1_img');

	// Compare
	$('#compare_button')
			.on(
					'click',
					function() {
						if ($('#img1_dropdown_options').val()
								&& $('#img2_dropdown_options').val()) {

							var queryObj = {
								clientSignatureImageName : $(
										'#img1_dropdown_options').val(),
								checkImageName : $('#img2_dropdown_options')
										.val(),
							}

							$
									.ajax({
										type : 'GET',
										url : 'http://localhost:8080/SignatureComparisonModule/jaxrs/compare',
										async : true,
										dataType : 'json',
										data : queryObj,
										timeout : 10000,
										success : function(resp) {
											// TODO: Report result
											$('#result_row').hide()
													.removeClass('hidden')
													.slideDown(250);
										}
									});
						} else {
							$('#compare_row').effect('shake', {times: 2, distance: 5}, 250);
						}
					});
});

function getAndDisplayImage(nameID, imageType, destinationID) {

	$('#' + nameID)
			.change(
					function() {
						var queryObj = {
							type : imageType,
							name : $('#' + nameID).val(),
						};

						// Load image
						$
								.ajax({
									type : 'GET',
									url : 'http://localhost:8080/SignatureComparisonModule/jaxrs/getImage',
									async : true,
									dataType : 'json',
									data : queryObj,
									timeout : 10000,
									success : function(resp) {
										$('#' + destinationID).attr("src", "data:image/png;base64," + resp.image);
									}
								});
					});
}