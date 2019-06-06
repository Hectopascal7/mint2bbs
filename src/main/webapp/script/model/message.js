layui.use(['layer', 'form', 'laytpl', 'upload', 'fly'], function () {
    var message = {
        showMessage: function () {
            var url = $(this).data('url');
            var id = $(this).data('id');
            $.ajax({
                type: 'post',
                url: '/message/updateMessageRead.do',
                data: {
                    mid: id
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.status = 1) {
                        location.href = url;
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调取服务失败");
                }
            })
        },
        deleteMessage: function () {
            var id = $(this).data('id');
            var o = $(this);
            $.ajax({
                type: 'post',
                url: '/message/deleteMessage.do',
                data: {
                    mid: id
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.status = 1) {
                        layer.msg(data.msg, function () {
                            reloadMessageList();
                        })
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调取服务失败");
                }
            })
        },
        clearAllMessage: function () {
            var id = $(this).data('id');
            var o = $(this);
            $.ajax({
                type: 'post',
                url: '/message/updateMessageRead.do',
                data: {
                    mid: id
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    layer.msg(data.msg, function () {
                        reloadMessageList();
                    })
                },
                error: function (data) {
                    layer.msg("调取服务失败");
                }
            })
        }
    };

    $('body').on('click', '.message-fn', function () {
        var othis = $(this);
        var method = othis.data('method');
        message[method] && message[method].call(this);
    });

    function reloadMessageList() {
        layui.use('layer', function () {
            // ajax交互，JS中以定义的layer无法获取（猜测），手动定义一个
            // 定义layer，用于打印提示信息
            var layer = layui.layer;
            // 消息数据
            var message;
            // ajax-用户资料中心数据获取
            $.ajax({
                type: 'post',
                url: '/message/getUserMessage.do',
                dataType: 'json',
                async: false,
                success: function (data) {
                    message = data.data;
                },
                error: function (data) {
                    layer.msg("调取【获取未读消息】服务失败！");
                }
            })

            // 模板引擎渲染
            layui.use('laytpl', function () {
                // 定义一个模板引擎
                laytpl = layui.laytpl;
                // 用户消息渲染
                var message_model = document.getElementById('m_message').innerHTML;
                var message_view = document.getElementById('message');
                laytpl(message_model).render(message, function (html) {
                    message_view.innerHTML = html;
                });
            });
        })
    }
});

