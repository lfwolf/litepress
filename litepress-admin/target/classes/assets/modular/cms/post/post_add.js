/**
 * 详情对话框
 */
var PostInfoDlg = {
    data: {
        name: "",
        content: "",
        cover: "",
        media: "",
        source: "ienglish",
        url: "",
        status: "",
        description: "",
        createTime: "",
        updateTime: "",
        createUser: "",
        updateUser: ""
    }
};

layui.use(['form', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/cms/post/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + "/cms/post" ;
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    //返回按钮
    $("#backupPage").click(function () {
        window.location.href = Feng.ctxPath + "/cms/post" ;
    });

});