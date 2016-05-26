$(function() {
	//Load page content
	$('#navbar').load("./html/navbar.html", function() {
		$('#page_content').load("./html/manage_content.html", function() {
			//Variables
			var base64Image;

			// Activate manage tab
			$(".nav").find(".active").removeClass("active");
			$('#manage').addClass('active');

			var serverURL, checkIfExistsLink, insertImageLink, getAlgorithmParamsLink, updateAlgorithmParamsLink;
			
			// Import config file
			$.getJSON("./config.json", function(data) {
				serverURL = data.server.url;
				checkIfExistsLink = serverURL + data.api.checkIfExists;
				insertImageLink = serverURL + data.api.insertImage;
				getAlgorithmParamsLink = serverURL + data.api.getAlgorithmParams;
				updateAlgorithmParamsLink = serverURL + data.api.updateAlgorithmParams;
			}).done(function() {
				//Insert image button
				$('#btn_insert_image').on('click', function() {
					var name = $('#image_name').val();
					var type = $('input[name=image_type]:checked').val();
					var image = base64Image;
					if(!name || !type || !image) { // Don't upload
						if(!$('#insert_image_error_alert').is(":visible")) {
							$('#insert_image_error_alert').hide().removeClass('hidden').slideDown(250);
						}
						return;
					}
					//Check if it is repeated
					var queryObj = {
						type : type,
						name : name
					};
					$.ajax({
						type : 'GET',
						url : checkIfExistsLink,
						async : true,
						dataType : 'json',
						data : queryObj,
						success : function(data) {
							if(data['exists']) {
								$('#alert_image_name').html(name);
								$('#alert_modal').modal('show');
								
								$('#alert_confirm').off().on('click', function() {
									$('#alert_modal').modal('hide');
									var bodyObj = {
										name : name,
										type : type,
										image : image
									};
									requestInsertImage(insertImageLink, bodyObj);
								});
							} else {
								var bodyObj = new Object();
								bodyObj.name = name;
								bodyObj.type = type;
								bodyObj.image = image;
								/*{
									"name" : name,
									"type" : type,
									"image" : image
								};*/
								requestInsertImage(insertImageLink, bodyObj);
							}
						},
						error : function() {
							alert("Couldn't verify image existence");
						}
					});
					
					/*alert("name: " + $('#image_name').val());
					alert("type: " + $('input[name=image_type]:checked').val());
					alert("file base64: " + base64Image);*/
				});

				var width, height, threshold, minNumMatches, minMatchesPercent;
				$.ajax({
					type : 'GET',
					url : getAlgorithmParamsLink,
					async : true,
					dataType : 'json',
					success: function(data) {
						width = data.normMaxSize['width'];
						height = data.normMaxSize['height'];
						threshold = data['threshold'];
						minNumMatches = data['minNumMatches'];
						minMatchesPercent = data['minMatchesPercent'];

						$('#width').attr("placeholder", width);
						$('#height').attr("placeholder", height);
						$('#threshold').attr("placeholder", threshold);
						$('#min_matches').attr("placeholder", minNumMatches);
						$('#min_matches_percent').attr("placeholder", minMatchesPercent);
					}
				});
				
				$('#btn_update_config').on('click', function() {
					var widthPUT, heightPUT, thresholdPUT, minNumMatchesPUT, minMatchesPercentPUT;
					widthPUT = $('#width').val();
					heightPUT = $('#height').val();
					thresholdPUT = $('#threshold').val();
					minNumMatchesPUT = $('#min_matches').val();
					minMatchesPercentPUT = $('#min_matches_percent').val();
					
					if(widthPUT || heightPUT || thresholdPUT || minNumMatchesPUT || minMatchesPercentPUT) {
						if(!widthPUT) {
							widthPUT = width;
						}
						if(!heightPUT) {
							heightPUT = height;
						}
						if(!thresholdPUT) {
							thresholdPUT = threshold;
						}
						if(!minNumMatchesPUT) {
							minNumMatchesPUT = minNumMatches;
						}
						if(!minMatchesPercentPUT) {
							minMatchesPercentPUT = minMatchesPercent;
						}
						
						var queryUpdate = {
							width : widthPUT,
							height : heightPUT,
							threshold : thresholdPUT,
							minMatches : minNumMatchesPUT,
							minMatchesPercent : minMatchesPercentPUT,
						}
						
						$.ajax({
							type : 'GET',
					        contentType: "application/json",
							url : updateAlgorithmParamsLink,
							async : true,
							data : queryUpdate,
							success : function() {
								location.reload();
							},
							error : function() {
								alert('failed');
							}
						});
					} else {
						if(!$('#update_config_error_alert').is(":visible")) {
							$('#update_config_error_alert').hide().removeClass('hidden').slideDown(250);
						}
						return;
						alert('Please fill at leasts one field to be updated.');
					}
				});
			});
			
			//Hide insert image alert
			$('input[name=image_type]').on('change', function() {
				$('#insert_image_error_alert').slideUp(250);
			});
			
			$('input[name=image_name]').on('change', function() {
				$('#insert_image_error_alert').slideUp(250);
			});
			
			$('#image_browse').on('change', function() {
				$('#insert_image_error_alert').slideUp(250);
				
				var fileToLoad = this.files[0];
				if(fileToLoad) {
					getBase64(fileToLoad);
				} else {
					base64Image = "";
				}
			});
			
			//Hide update config alert
			$('input[name=width]').on('change', function() {
				$('#update_config_error_alert').slideUp(250);
			});
			$('input[name=height]').on('change', function() {
				$('#update_config_error_alert').slideUp(250);
			});
			$('input[name=threshold]').on('change', function() {
				$('#update_config_error_alert').slideUp(250);
			});
			$('input[name=min_matches]').on('change', function() {
				$('#update_config_error_alert').slideUp(250);
			});
			$('input[name=min_matches_percent]').on('change', function() {
				$('#update_config_error_alert').slideUp(250);
			});
			
			function requestInsertImage(insertImageLink, dataObj) {
				console.log(JSON.stringify(dataObj));
				$.ajax({
					type : 'POST',
					headers: { 
						'Accept': 'application/json',
						'Content-Type': 'application/json' 
					},
			        contentType: "application/json",
					url : insertImageLink,
					async : true,
					data : JSON.stringify(dataObj),
					success : function() {
						location.reload();
					},
					error : function(){
						alert("Ups! An error ocurred while tring to insert the image.");
					}
				});
			}
			
			// Get image base64
			function getBase64(fileToLoad) {
				var fileReader = new FileReader();
				fileReader.onload = function(fileLoadedEvent) {
					base64Image = fileLoadedEvent.target.result;
					base64Image = base64Image.substring(base64Image.indexOf("base64,") + 7);
				}
				fileReader.readAsDataURL(fileToLoad);
			}
		});
	});
});