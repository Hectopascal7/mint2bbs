layui.use(['layer', 'form', 'laytpl', 'upload', 'fly'], function () {
    //
    var content_fn = {
        report: function () {
            alert(1);
            var mtype = this.getAttribute("data-mtype");
            var otype = this.getAttribute("data-otype");
            var oid = this.getAttribute("data-oid");
            var r_reply = $(this);
            $.ajax({
                type: 'post',
                url: '/message/report.do',
                data: {
                    mtype: mtype,
                    otype: otype,
                    oid: oid
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    layer.msg(data.msg);
                    r_reply.attr("type", "reportDisable");
                    r_reply.addClass("layui-btn-disabled");
                    r_reply.removeAttr("data-mtype");
                    r_reply.removeAttr("data-otype");
                    r_reply.removeAttr("data-oid");
                    r_reply.html("已举报<i class=\"layui-icon layui-icon-face-cry\" style=\"font-size: 30px; color: black;\"></i>");
                },
                error: function (data) {
                    layer.msg("调用【举报】服务失败");
                }
            })
        },
        reportDisable: function () {
            layer.msg("已经反馈给管理员了，请勿重复举报。")
        }
    };

    $('.content-fn').on('click', function () {
        var othis = $(this);
        var type = othis.attr('type');
        content_fn[type].call(this, othis);
    });

});

