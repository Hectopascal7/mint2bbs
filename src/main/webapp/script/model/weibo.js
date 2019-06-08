// 当前微博数据页数
var page = 1;

// 刷新微博列表函数方法
function refresh() {
    // 请求数据
    $.ajax({
        type: 'post',
        url: '/weibo/getWeiboWithPage.do',
        dataType: 'json',
        data: {
            page: page
        },
        async: false,
        success: function (data) {
            weibo = data.data;
            lastCount = data.data.length;
        },
        error: function (data) {
            layer.msg("获取微博列表失败！");
        }
    })
    // 渲染数据
    layui.use('laytpl', function () {
        laytpl = layui.laytpl;
        // 帖子列表渲染
        // 微博列表渲染
        var weibo_model = document.getElementById('m_weibo').innerHTML;
        var weibo_view = document.getElementById('weibo');
        laytpl(weibo_model).render(weibo, function (html) {
            // 当前只写了获取之前当前页所有数据的方法，是否使用追加有待商讨
            // 更新innerHTML，获取数据量越来越大，需要获取当前页之前所有数据，适用于实时更新
            weibo_view.innerHTML = html;
            // 追加innerHTML，获取数据量小，只用获取新页面的数据，适用于不实时更新
            // weibo_view.innerHTML += html;
        });

    });
}

// 上一次获取的微博条数
var lastCount = 0;

// 获取更多微博函数方法
function getMore() {
    $.ajax({
        type: 'post',
        url: '/weibo/getWeiboWithPage.do',
        dataType: 'json',
        data: {
            page: ++page
        },
        async: false,
        success: function (data) {
            weibo = data.data;
        },
        error: function (data) {
            layer.msg("获取微博列表失败！");
        }
    })
    alert(lastCount);
    if (lastCount == weibo.length) {
        layer.msg("没有更多了...");
        return;
    } else {
        lastCount = weibo.length;
    }
    layui.use('laytpl', function () {
        laytpl = layui.laytpl;
        // 帖子列表渲染
        // 微博列表渲染
        var weibo_model = document.getElementById('m_weibo').innerHTML;
        var weibo_view = document.getElementById('weibo');
        laytpl(weibo_model).render(weibo, function (html) {
            // 当前只写了获取之前当前页所有数据的方法，是否使用追加有待商讨
            // 更新innerHTML，获取数据量越来越大，需要获取当前页之前所有数据，适用于实时更新
            weibo_view.innerHTML = html;
            // 追加innerHTML，获取数据量小，只用获取新页面的数据，适用于不实时更新
            // weibo_view.innerHTML += html;
        });

    });
}

function isToday(date) {
    var today = moment().format("YYYY年MM月DD日");
    var d = moment(date).format('YYYY年MM月DD日');
    return d == today;
}

function isYesterday(date) {
    var yesterday = moment().add(-1, 'days').format('YYYY年MM月DD日');
    var d = moment(date).format('YYYY年MM月DD日');
    return d == yesterday;
}

function point(days) {
    if (days > 0 && days < 5) {
        return 5;
    } else if (days >= 5 && days < 15) {
        return 10;
    } else if (days >= 15 && days < 30) {
        return 15;
    } else {
        return 20;
    }
}

layui.use(['layer', 'form', 'laytpl'], function () {

    // 定义layer，用于打印提示信息
    var layer = layui.layer;
    // 监听表单提交，发布微博
    var form = layui.form;

    // 用户活跃榜数据
    var active_user;
    // ajax-用户活跃榜数据获取
    $.ajax({
        type: 'post',
        url: '/count/getActiveUser.do',
        dataType: 'json',
        async: false,
        success: function (data) {
            active_user = data.data;
        },
        error: function (data) {
            layer.msg("获取用户活跃榜失败！");
        }
    })

    // 帖子列表数据
    var checkin;
    // ajax-获取帖子列表
    $.ajax({
        type: 'post',
        url: '/checkin/getUserCheckinInfo.do',
        dataType: 'json',
        async: false,
        success: function (data) {
            checkin = data.data;
        },
        error: function (data) {
            layer.msg("调取【获取签到信息】服务失败！");
        }
    })
    // 模板引擎渲染
    layui.use(['laytpl'], function () {
        // 定义一个模板引擎
        laytpl = layui.laytpl;
        // 签到模块渲染
        if (checkin !== null) {
            var checkin_model = document.getElementById('m_checkin').innerHTML;
            var checkin_view = document.getElementById('checkin');
            laytpl(checkin_model).render(checkin, function (html) {
                checkin_view.innerHTML = html;
            });
        }
    });

    // 用户中心数据
    var user_info;
    // ajax-用户数据中心数据获取
    $.ajax({
        type: 'post',
        url: '/user/userCenter.do',
        data: {},
        dataType: 'json',
        async: false,
        success: function (data) {
            user_info = data.data;
        },
        error: function (data) {
            layer.msg("获取用户中心失败！");
        }
    })

    // 热门帖数据
    var hot_post;
    // ajax-获取热门帖数据
    $.ajax({
        type: 'post',
        url: '/post/getHotPost.do',
        dataType: 'json',
        async: false,
        success: function (data) {
            hot_post = data.data;
        },
        error: function (data) {
            layer.msg("获取热门帖失败！");
        }
    })

    // 微博列表数据
    var weibo;
    // ajax-获取微博列表
    $.ajax({
        type: 'post',
        url: '/weibo/getWeiboWithPage.do',
        dataType: 'json',
        data: {
            page: page
        },
        async: false,
        success: function (data) {
            weibo = data.data;
        },
        error: function (data) {
            layer.msg("获取微博数据失败！");
        }
    })

    // 微博内容校验
    form.verify({
        weibo: function (value) { //value：表单的值、item：表单的DOM对象
            if (value.trim().length == 0) {
                return '输入内容才能提交哦~';
            }
            if (value.trim().length > 48) {
                return '微博内容最多48字~';
            }
        }
    });

    // 发布微博事件按钮监听
    form.on('submit(weibo)', function (data) {
        if (!$('#agree').is(':checked')) {
            return layer.tips('您需要同意管理条例才能发布微博', $('#agree').next(), {tips: 1});
        }
        $.ajax({
            type: 'post',
            url: '/weibo/publishAWeibo.do',
            data: data.field,
            dataType: 'json',
            async: false,
            success: function (data) {
                layer.msg(data.msg);
                refresh();
                $("#content").val("");
            },
            error: function (data) {
                layer.msg("收藏失败！");
            }
        })
        return false;
    });

    // 使用模板引擎渲染
    laytpl = layui.laytpl;

    // 用户中心渲染
    var center_model = document.getElementById('m_center').innerHTML;
    var center_view = document.getElementById('center');
    laytpl(center_model).render(user_info, function (html) {
        center_view.innerHTML = html;
    });

    // 热门帖子渲染
    var hot_post_model = document.getElementById('m_hot_post').innerHTML;
    var hot_post_view = document.getElementById('hot_post');
    laytpl(hot_post_model).render(hot_post, function (html) {
        hot_post_view.innerHTML = html;
    });

    // 微博列表渲染
    var weibo_model = document.getElementById('m_weibo').innerHTML;
    var weibo_view = document.getElementById('weibo');
    laytpl(weibo_model).render(weibo, function (html) {
        // 当前只写了获取之前当前页所有数据的方法，是否使用追加有待商讨
        // 更新innerHTML，获取数据量越来越大，需要获取当前页之前所有数据，适用于实时更新
        weibo_view.innerHTML = html;
        lastCount = weibo.length;
        // 追加innerHTML，获取数据量小，只用获取新页面的数据，适用于不实时更新
        // weibo_view.innerHTML += html;
    });

    // 活跃用户榜渲染
    var active_user_model = document.getElementById('m_active_user').innerHTML;
    var active_user_view = document.getElementById('active_user');
    laytpl(active_user_model).render(active_user, function (html) {
        active_user_view.innerHTML = html;
    });

    // 社区工具栏车牌号查询事件
    var active = {
        offset: function (othis) {
            var type = othis.data('type');
            var text = '<input type="text" id="license" lay-verify="title" autocomplete="off" placeholder="请输入车牌号..." class="layui-input">';
            layer.open({
                type: 1
                , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + type //防止重复弹出
                , content: '<div style="padding: 20px 100px;">' + text + '</div>'
                , btn: '查询'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
                , yes: function () {
                    var license = $("#license").val();
                    $.ajax({
                        type: 'post',
                        url: '/user/getUserByLicense.do',
                        dataType: 'json',
                        data: {
                            license: license
                        },
                        success: function (data) {
                            layer.alert(data.data.name + data.data.phone);
                        },
                        error: function (data) {
                            layer.msg("查询失败！");
                        }
                    })
                    // layer.closeAll();
                }
            });
        }
    };

    $('.tool').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

    layui.cache.page = '';
    layui.cache.user = {
        username: '游客'
        , uid: -1
        , avatar: '/image/avatar/00.jpg'
        , experience: 83
        , sex: '男'
    };
    layui.config({
        version: "3.0.0"
        , base: '/script/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');

})