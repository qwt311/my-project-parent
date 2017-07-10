//注册时验证手机号
function checkPhone (){

	var phone = $('#phone').val();
	if (phone == "") {
		$('#phone').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×手机号不能为空</b></font>");
		return false;
	}else {
		$('#phone').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});

	}

	var checkPhoen = /^1[3|4|5|7|8][0-9]{9}$/;
	if (!checkPhoen.test(phone)) {
		$('#phone').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×手机号码格式不正确</b></font>");
		return false;

	}else {
		$('#phone').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});

	}

	$.ajax({
		url:contextPath+'/user/checkCondition',
		type:"POST",
		dataType: 'json',
		data:{"phone":phone},
		success:function(data){

			if(data != null){
				$('#phone').focus().css({
					border: "1px solid red",
					boxShadow: "0 0 2px red"
				});
				$('#userCue').html("<font color='red'><b>×该手机号码已存在</b></font>");
				return false;
			}

		},
		error:function(){
			$('#phone').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
			$('#userCue').html("<font color='green'><b>该手机号码可以使用</b></font>");

		}


	});

}

//邮箱验证
function checkEmail () {

	var email = $("#email").val();
	if(email == ""){
		$('#email').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×邮箱不能为空</b></font>");
		return false;
	} else {
		$('#email').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});
	}

	/*var checkEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if (!checkEmail.test(email)) {
		$('#email').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×邮箱格式不正确</b></font>");
		return false;
	} else {
		$('#email').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});

	}*/


	$.ajax({
		url:contextPath+'/user/checkCondition',
		type:"POST",
		dataType: 'json',
		data:{"email":email},
		success:function(data){

			if(data != null){
				$('#email').focus().css({
					border: "1px solid red",
					boxShadow: "0 0 2px red"
				});
				$('#userCue').html("<font color='red'><b>×该邮箱已被使用</b></font>");
				return false;
			}

		},
		error:function(){
			$('#email').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
			$('#userCue').html("<font color='green'><b>该邮箱可以使用</b></font>");

		}


	});

}

//密码和确认密码验证
function checkPassword () {

	if ($('#password2').val() != $('#password').val()) {
		$('#password2').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
		return false;
	}else{
		$('#password').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});
		$('#password2').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});
		$('#userCue').html("<font color='green'><b>密码一致！</b></font>");
	}

}

//验证邀请码
function checkYqm () {

	var yaoqingma = $('#yaoqingma').val();
	if (yaoqingma == "") {
		$('#yaoqingma').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×请填写邀请码</b></font>");
		return false;
	} else {
		$('#yaoqingma').css({
			border: "1px solid #D7D7D7",
			boxShadow: "none"
		});

	}

	$.ajax({
		url:contextPath+'/user/checkYqm',
		type:"POST",
		dataType: 'json',
		data:{"yaoqingma":yaoqingma},
		success:function(data){

			if(data != null){
				$('#yaoqingma').css({
					border: "1px solid #D7D7D7",
					boxShadow: "none"
				});
				$('#userCue').html("<font color='green'><b>该邀请码可以使用</b></font>");

			}

		},
		error:function(){
			$('#yaoqingma').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×请输入正确的邀请码</b></font>");
			return false;
		}


	});

}

//验证码验证
function checkCode () {

	var code = $("#inputCode").val();

	if(code == ""){
		$('#inputCode').focus().css({
			border: "1px solid red",
			boxShadow: "0 0 2px red"
		});
		$('#userCue').html("<font color='red'><b>×请输入验证码</b></font>");
		return false;
	}

	$.ajax({
		url:contextPath+'/code/checkCode',
		type:"POST",
		//dataType: 'json',
		data:{"code":code},
		success:function(data){

			if(data != null){
				$('#inputCode').css({
					border: "1px solid #D7D7D7",
					boxShadow: "none"
				});
				$('#userCue').html("<font color='green'><b>验证码正确</b></font>");

			}

		},
		error:function(){
			$('#inputCode').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×请输入正确的验证码</b></font>");
			return false;
		}


	});

}

//验证码的刷新
function changeCode () {

	var time = new Date();
	var img = document.getElementById("exchange");
	//alert(contextPath);
	img.src = contextPath+"/code/getCode?time="+time;

}

//用户同意协议提交按钮可用
/*function checkAgree () {

	if ($("#agree").is(":checked")){
		$("#reg").removeAttr('disabled');
	}else{
		$("#reg").attr('disabled',"disabled");
	}

}*/


$(function(){

	project.controller.register();

});

var project = {

	controller : {

		register : function () {

			$('#reg').click(function() {

				var phone = $('#phone').val();
				if (phone == "") {
					$('#phone').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×手机号不能为空</b></font>");
					return false;
				}else {
					$('#phone').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}


				var checkPhoen = /^1[3|4|5|7|8][0-9]{9}$/;
				if (!checkPhoen.test(phone)) {
					$('#phone').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×手机号码格式不正确</b></font>");
					return false;

				}else {
					$('#phone').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				//邮箱验证
				var email = $('#email').val();

				if(email == ""){
					$('#email').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×邮箱不能为空</b></font>");
					return false;
				} else {
					$('#email').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});
				}

				var checkEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				if (!checkEmail.test(email)) {
					$('#email').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×邮箱格式不正确</b></font>");
					return false;
				} else {
					$('#email').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				//密码验证
				var pwdmin = 6;
				if ($('#password').val().length < pwdmin) {
					$('#password').focus();
					$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
					return false;
				}
				if ($('#password2').val() != $('#password').val()) {
					/*$('#password2').focus();*/
					$('#password').focus();
					$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
					return false;
				}

				//邀请码验证
				//var sqq = /^[1-9]{1}[0-9]{4,9}$/;
				var yaoqingma = $('#yaoqingma').val();
				if (yaoqingma == "") {
					$('#yaoqingma').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×请填写邀请码</b></font>");return false;
				} else {
					$('#yaoqingma').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});

				}

				//验证码
				var inputCode = $("#inputCode").val();
				if(inputCode == ""){
					$('#inputCode').focus().css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$('#userCue').html("<font color='red'><b>×请填写验证码</b></font>");
					return false;
				}else{
					$('#inputCode').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});
				}

				//协议
				if (!$("#agree").is(":checked")){
					$('#userCue').html("<font color='red'><b>×请同意协议后再注册</b></font>");
					return false;
				}

				/*if(checkPhone()){
					if( checkEmail()){
						if(checkPassword()){
							if(checkYqm()){
								if(checkCode()){
									$('#regUser').submit();
								}
							}
						}
					}
				}*/
				$('#regUser').submit();

			});

		}

	}

}

