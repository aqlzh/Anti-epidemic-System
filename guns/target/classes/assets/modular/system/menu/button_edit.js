/**
 * 编辑菜单按钮
 */
layui.use(['form', 'admin', 'HttpRequest'], function () {
    var form = layui.form;
    var admin = layui.admin;
    var HttpRequest = layui.HttpRequest;

    // 获取菜单按钮详情
    var request = new HttpRequest(Feng.ctxPath + "/sysMenuButton/detail?buttonId=" + Feng.getUrlParam("buttonId"), 'get');
    var result = request.start();

    form.val('menuButtonForm', result.data);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var request = new HttpRequest(Feng.ctxPath + "/sysMenuButton/edit", 'post', function (data) {
            Feng.success("修改成功!");
            admin.putTempData('formOk', true);
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败!" + data.message);
            admin.closeThisDialog();
        });
        request.set(data.field);
        request.start(true);
    });

});
