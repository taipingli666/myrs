/**
 * 修改状态
 * @param data
 */
function save() {
    var data={};
    data.id = $("#id").val();
    data.registerStatus = $("#registerStatus").val();
    data.feedback = $("#feedback").val();

    $.ajax({
        url:globalVar.base +"/register/changeRegisterInfo",
        dataType:"json",
        contentType:"application/json",
        type:"post",
        data:JSON.stringify(data),
        success:function(data){
            if(data.resultCode == 0){
                layer.msg(data.errorMsg);
                layer_close();
            }else{
                layer.msg(data.errorMsg);
            }
        },
        error:function(){
            initFlag = false;
            layer.msg("网络连接异常");
        }
    });
}