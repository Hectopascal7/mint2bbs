layui.define(['laypage', 'fly', 'laytpl'], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var laytpl = layui.laytpl;
    var form = layui.form;
    var laypage = layui.laypage;
    var upload = layui.upload;
    var fly = layui.fly;
    var device = layui.device();
    // 发布商品
    // var active = {
    //
    //     //点赞
    //     praise: function (othis) {
    //         var li = othis.parents('li')
    //             , PRIMARY = 'layui-btn-primary'
    //             , unpraise = !othis.hasClass(PRIMARY)
    //             , numElem = li.find('.fly-case-nums')
    //
    //         fly.json('/case/praise/', {
    //             id: li.data('id')
    //             , unpraise: unpraise ? true : null
    //         }, function (res) {
    //             numElem.html(res.praise);
    //             if (unpraise) {
    //                 othis.addClass(PRIMARY).html('点赞');
    //                 layer.tips('少了个赞囖', numElem, {
    //                     tips: 1
    //                 });
    //             } else {
    //                 othis.removeClass(PRIMARY).html('已赞');
    //                 layer.tips('成功获得个赞', numElem, {
    //                     tips: [1, '#FF5722']
    //                 });
    //             }
    //         });
    //     }
    // };

    exports('market', {});
});