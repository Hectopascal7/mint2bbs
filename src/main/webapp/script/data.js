function getQueryParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

var uid = getQueryParameter("user");

layui.use('layer', function () {

    // ajax交互，JS中以定义的layer无法获取（猜测），手动定义一个
    // 定义layer，用于打印提示信息
    var layer = layui.layer;

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
            layer.msg("获取用户中心数据失败！");
        }
    })

    // 用户资料数据
    var user_info;
    // ajax-用户资料中心数据获取
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
            layer.msg("获取用户中心数据失败！");
        }
    })

    // 模板引擎渲染
    layui.use('laytpl', function () {
        // 定义一个模板引擎
        laytpl = layui.laytpl;

        // 用户中心渲染
        var center_model = document.getElementById('m_center').innerHTML;
        var center_view = document.getElementById('center');
        laytpl(center_model).render(user_info, function (html) {
            center_view.innerHTML = html;
        });

        // 用户资料渲染
        var user_info_model = document.getElementById('m_user_info').innerHTML;
        var user_info_view = document.getElementById('user_info');
        laytpl(user_info_model).render(user_info, function (html) {
            user_info_view.innerHTML = html;
        });

        // 用户头像渲染
        var profile_model = document.getElementById('m_profile').innerHTML;
        var profile_view = document.getElementById('profile');
        laytpl(profile_model).render(user_info, function (html) {
            profile_view.innerHTML = html;
        });

    });

})