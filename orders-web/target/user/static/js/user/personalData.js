$(function(){
    selectpersonalData();
});
function selectpersonalData() {
    $.ajax({
        url: getRootPath() + "/user/selectPersonalData",
        type: "GET",
        dataType: "json",
        async: false,
        success: function(data) {
            var sysUser = data.data.sysUser;
            $("#personal_form").form('load', {
                personal_userId: sysUser.id,
                personal_loginName: sysUser.loginName,
                personal_zhName: sysUser.zhName,
                personal_phone: sysUser.phone,
                personal_email: sysUser.email
            });

            var sysUnit=data.data.sysUnit;
            $('#personal_unit_name').textbox('setValue',sysUnit.unitName);
            $('#personal_industry').textbox('setValue',sysUnit.baseIndustryName);
            $('#personal_unit_city').textbox('setValue',sysUnit.unitCityName);
            $('#personal_unit_county').textbox('setValue',sysUnit.unitCountryName);
            $('#personal_unit_address').textbox('setValue',sysUnit.unitAddress);

        }
    });
}

function updatePersonalData() {
    var id = $('#personal_edit_dialog input[id="personal_userId"]').val();
    var loginName = $('#personal_edit_dialog input[id="personal_loginName"]').val();
    var zhName = $('#personal_edit_dialog input[id="personal_zhName"]').val();
    var email = $('#personal_edit_dialog input[id="personal_email"]').val();
    var phone = $('#personal_edit_dialog input[id="personal_phone"]').val();
    $.ajax({
        data: {
            id:id,
            loginName:loginName,
            zhName:zhName,
            email:email,
            phone:phone
        },
        traditional: true,
        method: 'post',
        url: getRootPath() + "/user/updatePersonalData",
        async: false,
        dataType: 'json',
        success: function (result) {
            console.log(result);
            if (result.code == 10000) {
                layer.msg(result.msg,{time: 1000},function(){
                    parent.layer.closeAll();
                });
                return false;
            }
            else {
                common_tool.messager_show(result.msg);
            }
        },
    });
}