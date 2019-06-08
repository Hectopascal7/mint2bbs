/**

 @Name: 回复功能板块

 */

layui.define('fly', function (exports) {

    var $ = layui.jquery;
    var layer = layui.layer;
    var util = layui.util;
    var laytpl = layui.laytpl;
    var form = layui.form;
    var fly = layui.fly;

    var gather = {}, dom = {
        jieda: $('#jieda')
        , content: $('#content')
        , jiedaCount: $('#jiedaCount')
    };

    //监听专栏选择
    form.on('select(column)', function (obj) {
        var value = obj.value
            , elemQuiz = $('#LAY_quiz')
            , tips = {
            tips: 1
            , maxWidth: 250
            , time: 10000
        };
        elemQuiz.addClass('layui-hide');
        if (value === '0') {
            layer.tips('下面的信息将便于您获得更好的答案', obj.othis, tips);
            elemQuiz.removeClass('layui-hide');
        } else if (value === '99') {
            layer.tips('系统会对【分享】类型的帖子予以飞吻奖励，但我们需要审核，通过后方可展示', obj.othis, tips);
        }
    });

    //提交回答
    fly.form['/jie/reply/'] = function (data, required) {
        var tpl = '<li>\
      <div class="detail-about detail-about-reply">\
        <a class="fly-avatar" href="/u/{{ layui.cache.user.uid }}" target="_blank">\
          <img src="{{= d.user.avatar}}" alt="{{= d.user.username}}">\
        </a>\
        <div class="fly-detail-user">\
          <a href="/u/{{ layui.cache.user.uid }}" target="_blank" class="fly-link">\
            <cite>{{d.user.username}}</cite>\
          </a>\
        </div>\
        <div class="detail-hits">\
          <span>刚刚</span>\
        </div>\
      </div>\
      <div class="detail-body reply-body photos">\
        {{ d.content}}\
      </div>\
    </li>'
        data.content = fly.content(data.content);
        laytpl(tpl).render($.extend(data, {
            user: layui.cache.user
        }), function (html) {
            required[0].value = '';
            dom.jieda.find('.fly-none').remove();
            dom.jieda.append(html);

            var count = dom.jiedaCount.text() | 0;
            dom.jiedaCount.html(++count);
        });
    };

    // 右-恢复操作 举报
    gather.jieAdmin = {
        //删求解
        // del: function (div) {
        //     layer.confirm('确认删除该求解么？', function (index) {
        //         layer.close(index);
        //         fly.json('/api/jie-delete/', {
        //             id: div.data('id')
        //         }, function (res) {
        //             if (res.status === 0) {
        //                 location.href = '/jie/';
        //             } else {
        //                 layer.msg(res.msg);
        //             }
        //         });
        //     });
        // }

        //设置置顶、状态
        // , set: function (div) {
        //     var othis = $(this);
        //     fly.json('/api/jie-set/', {
        //         id: div.data('id')
        //         , rank: othis.attr('rank')
        //         , field: othis.attr('field')
        //     }, function (res) {
        //         if (res.status === 0) {
        //             location.reload();
        //         }
        //     });
        // }

        //收藏
        // , collect: function (div) {
        //     var othis = $(this), type = othis.data('type');
        //     fly.json('/collection/' + type + '/', {
        //         cid: div.data('id')
        //     }, function (res) {
        //         if (type === 'add') {
        //             othis.data('type', 'remove').html('取消收藏').addClass('layui-btn-danger');
        //         } else if (type === 'remove') {
        //             othis.data('type', 'add').html('收藏').removeClass('layui-btn-danger');
        //         }
        //     });
        // }
        reply: function (li) { //回复
            var val = dom.content.val();
            var aite = '@' + li.find('.fly-detail-user cite').text().replace(/\s/g, '');
            var rid = li.find('a').data("id");
            $("#rrid").val(rid);
            dom.content.focus()
            if (val.indexOf(aite) !== -1) return;
            dom.content.val(aite + ' ' + val);
        },
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
                    r_reply.attr("type", "reportDisable");
                },
                error: function (data) {
                    layer.msg("调用【举报】服务失败");
                }
            })
        }
        ,
        reportDisable: function () {
            layer.msg("已经反馈给管理员了，请勿重复举报。")
        }

    }
    ;

    $('body').on('click', '.right-op span', function () {
        var othis = $(this), type = othis.attr('type');
        if (type == "reply") {
            gather.jieAdmin[type] && gather.jieAdmin[type].call(this, othis.parents('li'));
        } else {
            gather.jieAdmin[type] && gather.jieAdmin[type].call(this, othis.parent());
        }
    });

    //异步渲染
    // var asyncRender = function () {
    //     var div = $('.fly-admin-box'), jieAdmin = $('#LAY_jieAdmin');
    //     //查询帖子是否收藏
    //     if (jieAdmin[0] && layui.cache.user.uid != -1) {
    //         fly.json('/collection/find/', {
    //             cid: div.data('id')
    //         }, function (res) {
    //             jieAdmin.append('<span class="layui-btn layui-btn-xs jie-admin ' + (res.data.collection ? 'layui-btn-danger' : '') + '" type="collect" data-type="' + (res.data.collection ? 'remove' : 'add') + '">' + (res.data.collection ? '取消收藏' : '收藏') + '</span>');
    //         });
    //     }
    // }();

    //左-回复操作 点赞、取消点赞
    reply.operation = {
        // zan: function (li) { //赞
        //     var othis = $(this), ok = othis.hasClass('praise-ok');
        //     fly.json('/api/reply-praise/', {
        //         ok: ok
        //         , id: li.data('id')
        //     }, function (res) {
        //         if (res.status === 0) {
        //             var zans = othis.find('em').html() | 0;
        //             othis[ok ? 'removeClass' : 'addClass']('praise-ok');
        //             othis.find('em').html(ok ? (--zans) : (++zans));
        //         } else {
        //             layer.msg(res.msg);
        //         }
        //     });
        // }
        // 点赞回复
        praise: function () {
            var iid = this.getAttribute("data-iid");
            var itype = this.getAttribute("data-itype");
            var p_reply = $(this);
            $.ajax({
                type: 'post',
                url: '/praise/praise.do',
                data: {
                    iid: iid,
                    itype: itype,
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    layer.msg(data.msg);
                    p_reply.addClass("praise-ok");
                    p_reply.attr("data-pid", data.data);
                    p_reply.attr("type", "cancelPraise");
                    p_reply.removeAttr("data-iid");
                    p_reply.removeAttr("data-itype");
                    p_reply.find("em:first").text(Number(p_reply.find("em:first").text()) + 1);
                },
                error: function (data) {
                    layer.msg("调用【点赞】服务失败");
                }
            })
        },
        // 取消点赞回复
        cancelPraise: function () {
            var pid = this.getAttribute("data-pid");
            var cp_reply = $(this);
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
                        cp_reply.removeClass("praise-ok");
                        cp_reply.attr("data-iid", data.data.iid);
                        cp_reply.attr("data-itype", data.data.itype);
                        cp_reply.removeAttr("data-pid");
                        cp_reply.attr("type", "praise");
                        cp_reply.find("em:first").text(Number(cp_reply.find("em:first").text()) - 1);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function (data) {
                    layer.msg("调用【取消点赞】服务失败");
                }
            })
        }
        // , accept: function (li) { //采纳
        //     var othis = $(this);
        //     layer.confirm('是否采纳该回答为最佳答案？', function (index) {
        //         layer.close(index);
        //         fly.json('/api/jieda-accept/', {
        //             id: li.data('id')
        //         }, function (res) {
        //             if (res.status === 0) {
        //                 $('.jieda-accept').remove();
        //                 li.addClass('jieda-daan');
        //                 li.find('.detail-about').append('<i class="iconfont icon-caina" title="最佳答案"></i>');
        //             } else {
        //                 layer.msg(res.msg);
        //             }
        //         });
        //     });
        // }
        // , edit: function (li) { //编辑
        //     fly.json('/jie/getDa/', {
        //         id: li.data('id')
        //     }, function (res) {
        //         var data = res.rows;
        //         layer.prompt({
        //             formType: 2
        //             , value: data.content
        //             , maxlength: 100000
        //             , title: '编辑回帖'
        //             , area: ['728px', '300px']
        //             , success: function (layero) {
        //                 fly.layEditor({
        //                     elem: layero.find('textarea')
        //                 });
        //             }
        //         }, function (value, index) {
        //             fly.json('/jie/updateDa/', {
        //                 id: li.data('id')
        //                 , content: value
        //             }, function (res) {
        //                 layer.close(index);
        //                 li.find('.detail-body').html(fly.content(value));
        //             });
        //         });
        //     });
        // },
        // del: function (li) { //删除
        //     layer.confirm('确认删除该回答么？', function (index) {
        //         layer.close(index);
        //         fly.json('/api/jieda-delete/', {
        //             id: li.data('id')
        //         }, function (res) {
        //             if (res.status === 0) {
        //                 var count = dom.jiedaCount.text() | 0;
        //                 dom.jiedaCount.html(--count);
        //                 li.remove();
        //                 //如果删除了最佳答案
        //                 if (li.hasClass('jieda-daan')) {
        //                     $('.jie-status').removeClass('jie-status-ok').text('求解中');
        //                 }
        //             } else {
        //                 layer.msg(res.msg);
        //             }
        //         });
        //     });
        // }

    };

    $('.left-op span').on('click', function () {
        var othis = $(this), type = othis.attr('type');
        reply.operation[type].call(this, othis);
    });

    // //定位分页
    // if(/\/page\//.test(location.href) && !location.hash){
    //   var replyTop = $('#flyReply').offset().top - 80;
    //   $('html,body').scrollTop(replyTop);
    // }

    exports('reply', null);
});