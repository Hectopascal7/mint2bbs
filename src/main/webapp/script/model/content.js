layui.use(['layer', 'form', 'laytpl', 'upload', 'fly'], function () {
    //
    var content_fn = {
        report: function () {
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
                    r_reply.addClass("layui-btn-disabled");
                    r_reply.addClass("layui-btn-primary");
                    r_reply.html("已举报");
                    r_reply.attr("type", "reportDisable");
                    r_reply.removeAttr("data-mtype");
                    r_reply.removeAttr("data-otype");
                    r_reply.removeAttr("data-oid");
                },
                error: function (data) {
                    layer.msg("调用【举报】服务失败");
                }
            })
        },
        reportDisable: function () {
            layer.msg("已经反馈给管理员了，请勿重复举报。")
        },
        praise: function () {
            var iid = this.getAttribute("data-iid");
            var itype = this.getAttribute("data-itype");
            var isid = this.getAttribute("data-isid");
            var p_content = $(this);
            $.ajax({
                type: 'post',
                url: '/praise/praise.do',
                data: {
                    'iid': iid,
                    'itype': itype,
                    'isid': isid
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.status = 1) {
                        layer.msg(data.msg);
                        p_content.addClass("layui-btn-primary");
                        p_content.text("取消点赞");
                        p_content.attr("type", "cancelPraise");
                        p_content.removeAttr("data-iid");
                        p_content.removeAttr("data-itype");
                        p_content.removeAttr("data-isid");
                        p_content.attr("data-pid", data.data);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调取服务失败");
                }
            })
        },
        cancelPraise: function () {
            var pid = this.getAttribute("data-pid");
            var cp_content = $(this);
            $.ajax({
                type: 'post',
                url: '/praise/cancelPraise.do',
                data: {
                    'pid': pid
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.status = 1) {
                        layer.msg(data.msg);
                        cp_content.removeClass("layui-btn-primary");
                        cp_content.html("点赞<i class=\"layui-icon layui-icon-praise\" style=\"font-size: 30px; color: black;\"></i>");
                        cp_content.attr("type", "praise");
                        cp_content.attr("data-iid", data.data.iid);
                        cp_content.attr("data-isid", data.data.isid);
                        cp_content.attr("data-itype", data.data.itype);
                        cp_content.removeAttr("data-pid");
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调取服务失败");
                }
            })
        },
        collect: function () {
            var iid = this.getAttribute("data-iid");
            var itype = this.getAttribute("data-itype");
            var isid = this.getAttribute("data-isid");
            var c_content = $(this);
            $.ajax({
                type: 'post',
                url: '/collection/collect.do',
                data: {
                    'iid': iid,
                    'itype': itype,
                    'isid': isid
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.status = 1) {
                        layer.msg(data.msg);
                        c_content.addClass("layui-btn-primary");
                        c_content.text("取消收藏");
                        c_content.attr("type", "cancelCollect");
                        c_content.removeAttr("data-iid");
                        c_content.removeAttr("data-itype");
                        c_content.removeAttr("data-isid");
                        c_content.attr("data-cid", data.data);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调取【收藏】服务失败");
                }
            })
        },
        cancelCollect: function () {
            var cid = this.getAttribute("data-cid");
            var cc_content = $(this);
            $.ajax({
                type: 'post',
                url: '/collection/cancelCollect.do',
                data: {
                    'cid': cid
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.status = 1) {
                        layer.msg(data.msg);
                        cc_content.removeClass("layui-btn-primary");
                        cc_content.html("收藏<i class=\"layui-icon layui-icon-rate\" style=\"font-size: 30px; color: black;\"></i>");
                        cc_content.attr("type", "collect");
                        cc_content.attr("data-iid", data.data.iid);
                        cc_content.attr("data-isid", data.data.isid);
                        cc_content.attr("data-itype", data.data.itype);
                        cc_content.removeAttr("data-cid");
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调取服务失败");
                }
            })
        }
    };

    $('.content-fn').on('click', function () {
        var othis = $(this);
        var type = othis.attr('type');
        content_fn[type].call(this, othis);
    });

});

