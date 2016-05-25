$(function() {

	//Load page content
	$('#navbar').load("./html/navbar.html", function() {
		$('#page_content').load("./html/home_content.html", function() {
			// Activate home tab
			$(".nav").find(".active").removeClass("active");
			$('#home').addClass('active');
			
			var serverURL, getSignatureLink, getChecksLink, getImageLink, compareLink;

			// Import config file
			$
					.getJSON("./config.json", function(data) {
						serverURL = data.server.url;
						getSignatureLink = serverURL + data.api.getSignatureList;
						getChecksLink = serverURL + data.api.getCheckList;
						getImageLink = serverURL + data.api.getImage;
						compareLink = serverURL + data.api.compare;
					})
					.done(
							function() {
								// Load signature images lists
								$.ajax({
									type : 'GET',
									url : getSignatureLink,
									async : true,
									dataType : 'json',
									success : function(resp) {
										var data = resp.data;
										$.each(data, function(i, obj) {
											$('#singImage_dropdown_options').append(
													$("<option></option>").attr(
															"value", obj.name).text(
															obj.name));
										});
									}
								});

								// Load check images lists
								$.ajax({
									type : 'GET',
									url : getChecksLink,
									async : true,
									dataType : 'json',
									success : function(resp) {
										$.each(resp.data, function(i, obj) {
											$('#checkImage_dropdown_options').append(
													$("<option></option>").attr(
															"value", obj.name).text(
															obj.name));
										});
									}
								});

								// Display image (Check)
								getAndDisplayImage('checkImage_dropdown_options',
										'check', 'checkImage_img');

								// Get and display image (Signature)
								getAndDisplayImage('singImage_dropdown_options',
										'client', 'singImage_img');

								// Compare
								$('#compare_button')
										.on(
												'click',
												function() {
													if ($('#singImage_dropdown_options')
															.val()
															&& $(
																	'#checkImage_dropdown_options')
																	.val()) {

														var $this = $(this);
														$this.button('loading');
														
														var queryObj = {
															clientSignatureImageName : $(
																	'#singImage_dropdown_options')
																	.val(),
															checkImageName : $(
																	'#checkImage_dropdown_options')
																	.val(),
														}

														$
																.ajax({
																	type : 'GET',
																	url : compareLink,
																	async : true,
																	dataType : 'json',
																	data : queryObj,
																	timeout : 10000,
																	success : function(data) {
																		processResult(data);
																	    $this.button('reset');
																	},
																	error : function() {
																	    $this.button('reset');
																		alert("Comparison failed.");
																	},
																	complete : function(jqxhr, status) {
																		if(status == "success") {
																			$('html, body').animate({
																		        scrollTop: $("#result_panel").offset().top
																		    }, 1000);
																		}
																	}
																});
													} else {
														if($('#error_alert').is(":visible")) {
															$('#compare_row').effect(
																	'shake', {
																		times : 2,
																		distance : 5
																	}, 250);
														} else {
															$('#error_alert').hide().removeClass('hidden').slideDown(250);
														}
													}
												});
								
								
								// More info button
								var moreInfoToggled = false;
								$('#more_info_button').on('click', function() {
									if(moreInfoToggled) {
										moreInfoToggled = false;
										$('#result_panel_more_info').slideUp(250);
									} else {
										moreInfoToggled = true;
										$('#result_panel_more_info').hide().removeClass('hidden').slideDown(250);
										$('html, body').animate({
									        scrollTop: $("#result_panel_more_info").offset().top
									    }, 1000);
									}
								});
							});

			function getAndDisplayImage(nameID, imageType, destinationID) {
				$('#' + nameID).change(
						function() {
							
							//Hide error
							$('#error_alert').slideUp(250);

							// Hide result
							$('#result_row').slideUp(500);

							// Show loading icon
							$('#' + destinationID).attr("src", "./imgs/loading.gif");

							var queryObj = {
								type : imageType,
								name : $('#' + nameID).val(),
							};

							if (queryObj.name) {
								// Load image
								$.ajax({
									type : 'GET',
									url : getImageLink,
									async : true,
									dataType : 'json',
									data : queryObj,
									timeout : 10000,
									success : function(resp) {
										$('#' + destinationID).attr("src",
												"data:image/png;base64," + resp.image);
									},
									error : function() {
										$('#' + destinationID).attr("src",
												"./imgs/no_image.jpg");

										alert("Couldn't load file.");
									}
								});
							} else {
								$('#' + destinationID).attr("src",
										"./imgs/no_image.jpg");
							}
						});
			}

			function processResult(data) {
				//Clear panel data
				//$('#result_panel_heading').html('');
				$('#result_img').attr("src", "");
				$('#more_info').html('');
				
				// Hide result panel
				$('#result_row').hide().removeClass('hidden');

				/* if (data.verdict) {
					$('#result_panel').removeClass('panel-default').removeClass(
							'panel-danger').addClass('panel-success');
					$('#result_panel_heading').html('Match found');
				} else {
					$('#result_panel').removeClass('panel-default').removeClass(
							'panel-success').addClass('panel-danger');
					$('#result_panel_heading').html('No match found');
				} */
				
				$.each(data, function(key, value) {
					switch(key) {
					case "score":
						$('#score_display').html("<p><b>Score: " + (Math.round(value * 100) / 100) + "%</b></p>");
						var percent = data['minMatchesPercent'];
						if(value < (0.75 * percent)) { // No match
							$('#score_display').append("<p>The cheque doesn't seem to have a matching signature.</p>").removeClass('alert-success').removeClass('alert-warning').addClass('alert-danger');
						} else if(value < percent) { // Maybe match
							$('#score_display').append("<p>The existance of a matching signature on the cheque is plausible.</p>").removeClass('alert-success').removeClass('alert-danger').addClass('alert-warning');
						} else { // Match
							$('#score_display').append("<p>The cheque seems to have a matching signature.</p>").removeClass('alert-warning').removeClass('alert-danger').addClass('alert-success');
						}
					case "threshold":
					case "minNumMatches":
					case "minMatchesPercent":
					case "queryKP":
					case "trainKP":
					case "queryDescriptors":
					case "trainDescriptors":
					case "matches":
					case "bestMatches":
					case "matchRate":
					case "verdict":
						$('#more_info').append("<li class='list-group-item'><b>" + key + " :</b> " + value + "</li>");
						break;
					case "resultImg":
						$('#result_img').attr("src",
								"data:image/png;base64," + value);
					}
				});
				
				

				// Show result
				$('#result_row').slideDown(500);
			}
		});
	});
});