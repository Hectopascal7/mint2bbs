layui.use(['layer', 'form', 'laytpl', 'upload', 'fly'], function () {
    var add_fn = {
        collect: function () {
            var method = this.getAttribute("data-method");
            var id = this.getAttribute("data-id");
            if (method == "collect") {
                $.ajax({
                    type: 'post',
                    url: '/collection/collect.do',
                    data: {
                        'iid': id,
                        'itype': 50
                    },
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.status = 1) {
                            layer.msg(data.msg);
                            $(".collect").text("取消收藏");
                            $(".collect").addClass("layui-btn-primary");
                            $(".collect").attr("data-method", "cancelCollect");
                            $(".collect").attr("data-id", data.data);
                        } else {
                            layer.msg(data.msg);
                        }
                    },
                    error: function (data) {
                        layer.msg("调取服务失败");
                    }
                })
            } else {
                $.ajax({
                    type: 'post',
                    url: '/collection/cancelCollect.do',
                    data: {
                        "cid": id
                    },
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.status = 1) {
                            layer.msg(data.msg);
                            $(".collect").html("收藏<i class=\"layui-icon layui-icon-praise\" style=\"font-size: 30px; color: black;\"></i>");
                            $(".collect").removeClass("layui-btn-primary");
                            $(".collect").attr("data-method", "collect");
                            $(".collect").attr("data-id", data.data);
                        } else {
                            layer.msg(data.msg);
                        }
                    },
                    error: function (data) {
                        layer.msg("调取服务失败");
                    }
                })
            }
        },
        praise: function () {
            var method = this.getAttribute("data-method");
            var id = this.getAttribute("data-id");
            if (method == "praise") {
                $.ajax({
                    type: 'post',
                    url: '/praise/praise.do',
                    data: {
                        'iid': id,
                        'itype': 50
                    },
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.status = 1) {
                            layer.msg(data.msg);
                            $(".praise").text("取消点赞");
                            $(".praise").addClass("layui-btn-primary");
                            $(".praise").attr("data-method", "cancelPraise");
                            $(".praise").attr("data-id", data.data);
                        } else {
                            layer.msg(data.msg);
                        }
                    },
                    error: function (data) {
                        layer.msg("调取服务失败");
                    }
                })
            } else {
                $.ajax({
                    type: 'post',
                    url: '/praise/cancelPraise.do',
                    data: {
                        'pid': id,
                        'itype': 50
                    },
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.status = 1) {
                            layer.msg(data.msg);
                            $(".praise").html("点赞<i class=\"layui-icon layui-icon-rate\" style=\"font-size: 30px; color: black;\"></i>");
                            $(".praise").removeClass("layui-btn-primary");
                            $(".praise").attr("data-method", "praise");
                            $(".praise").attr("data-id", data.data);
                        } else {
                            layer.msg(data.msg);
                        }
                    },
                    error: function (data) {
                        layer.msg("调取服务失败");
                    }
                })
            }
        },

    };

    $('body').on('click', '.fn-btn', function () {
        var othis = $(this);
        var type = othis.data('type');
        add_fn[type] && add_fn[type].call(this);
    });

});

