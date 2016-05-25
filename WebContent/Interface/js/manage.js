$(function() {
	//Load page content
	$('#navbar').load("./html/navbar.html", function() {
		$('#page_content').load("./html/manage_content.html", function() {
			//Variables
			var base64Image;

			// Activate manage tab
			$(".nav").find(".active").removeClass("active");
			$('#manage').addClass('active');
			
			//Insert image button
			$('#btn_insert_image').on('click', function() {
				if(!$('#image_name').val() || !$('input[name=image_type]:checked').val() || !base64Image) { // Don't upload
					if(!$('#insert_image_error_alert').is(":visible")) {
						$('#insert_image_error_alert').hide().removeClass('hidden').slideDown(250);
					}
					return;
				}
				alert("name: " + $('#image_name').val());
				alert("type: " + $('input[name=image_type]:checked').val());

				alert("file base64: " + base64Image);
			});
			
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
			
			function getBase64(fileToLoad) {
				var fileReader = new FileReader();
				fileReader.onload = function(fileLoadedEvent) {
					var srcData = fileLoadedEvent.target.result;
					base64Image = btoa(srcData);
				}
				fileReader.readAsDataURL(fileToLoad);
			}
		});
	});
});