function sendEmails () {

   var email = $(".enterEmail").val();

   if(email == ""){
      layer.msg("邮箱不能为空");
      return false;
   }

   var checkEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
   if (!checkEmail.test(email)) {
      layer.msg("邮箱格式不正确");
      return false;
   }

   $.ajax({
      url:contextPath+'/email/subscribeEmail',
      type:"POST",
      dataType: 'json',
      data:{"email":email},
      success:function(data){

         if(data.code = 1){
            layer.msg("订阅成功");

            $(".enterEmail").val("");
         }else{
            layer.msg("订阅失败");
         }

      },
      error:function(){
         layer.msg("服务器异常，请稍后重试");
      }


   });

}