$(function(){
	
	$('#switch_qlogin').click(function() {
			project.controller.login();
		});
	$('#switch_login').click(function() {
			project.controller.register();
	});
});

var project = {

	controller : {

		login : function () {

			$('#reg').click(function() {

				if ($('#phone').val() == "") {
					$('#phone').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×手机号不能为空</b></font>");
					return false;
				}



				if ($('#phone').val().length < 4 || $('#phone').val().length > 16) {

					$('#phone').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
					return false;

				}
				$.ajax({
					type: POST,
					url: "/member/ajaxyz.php",
					data: "uid=" + $("#phone").val() + '&temp=' + new Date(),
					dataType: 'html',
					success: function(result) {

						if (result.length > 2) {
							$('#user').focus().css({
								border: "1px solid red",
								boxShadow: "0 0 2px red"
							});$("#userCue").html(result);
							return false;
						} else {
							$('#user').css({
								border: "1px solid #D7D7D7",
								boxShadow: "none"
							});
						}

					}
				});


				if ($('#passwd').val().length < pwdmin) {
					$('#passwd').focus();
					$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
					return false;
				}
				if ($('#passwd2').val() != $('#passwd').val()) {
					$('#passwd2').focus();
					$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
					return false;
				}

				var sqq = /^[1-9]{1}[0-9]{4,9}$/;
				if (!sqq.test($('#qq').val()) || $('#qq').val().length < 5 || $('#qq').val().length > 12) {
					$('#qq').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×QQ号码格式不正确</b></font>");return false;
				} else {
					$('#qq').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				$('#regUser').submit();
			});

		},

		register : function () {



		}

	}

}

