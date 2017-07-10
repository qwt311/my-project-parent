

//登录时手机号验证
function checkLoginPhone (){

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
        $('#userCue').html("<font color='red'><b>×手机号码不正确</b></font>");
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
                $('#phone').css({
                    border: "1px solid #D7D7D7",
                    boxShadow: "none"
                });
                $('#userCue').html("<font color='green'><b>该手机号码可以登录</b></font>");
                return true;
            }

        },
        error:function(){
            $('#phone').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
            $('#userCue').html("<font color='red'><b>×该用户名不存在</b></font>");
            return false;

        }


    });

}

$(function(){

    project.controller.login();

});

var project = {

    controller : {

        login : function () {

            $("#login").click(function(){

                var phone = $("#phone").val();
                if(phone == ""){
                    $('#phone').focus().css({
                        border: "1px solid red",
                        boxShadow: "0 0 2px red"
                    });
                    $('#userCue').html("<font class='error' color='red'><b>×手机号不能为空</b></font>");
                    return false;
                }else{
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
                    $('#userCue').html("<font class='error' color='red'><b>×手机号码格式不正确</b></font>");
                    return false;

                }else {
                    $('#phone').css({
                        border: "1px solid #D7D7D7",
                        boxShadow: "none"
                    });

                }

                //checkLoginPhone ();

                //验证密码
                var pwdmin = 6;
                if ($('#password').val() == '') {
                    $('#password').focus();
                    $('#userCue').html("<font color='red'><b>×请输入密码</b></font>");
                    return false;
                }

                $('#loginUser').submit();

            });

        }

    }

}