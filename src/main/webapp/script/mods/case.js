layui.define(['laypage', 'fly'], function (exports) {

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
    var active = {
        push: function (div) {
            layer.open({
                type: 1
                , id: 'GoodPublishTab'
                , title: '发布商品'
                , area: (device.ios || device.android) ? ($(window).width() + 'px') : '660px'
                , content: ['<ul class="layui-form" style="margin: 30px;">'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label" style="text-align: left">商品名称</label>'
                    , '<div class="layui-input-block">'
                    , '<input required name="title" lay-verify="required" placeholder="商品发布名称" value="" class="layui-input">'
                    , '</div>'
                    , '</li>'
                    // , '<li class="layui-form-item">'
                    // , '<label class="layui-form-label" style="text-align: left">简要描述</label>'
                    // , '<div class="layui-input-block">'
                    // , '<input required name="content" lay-verify="required" placeholder="简要描述" value="" class="layui-input">'
                    // , '</div>'
                    // , '</li>'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label" style="text-align: left">商品类别</label>'
                    , '<div class="layui-input-block" id="isused">'
                    , '<input type="radio" name="isused" lay-filter="isused" value="0" title="崭新">'
                    , '<input type="radio" name="isused" lay-filter="isused" value="1" title="二手">'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item " id="ndegree">'
                    , '<label class="layui-form-label" style="text-align: left">新旧程度</label>'
                    , '<div class="layui-input-block">'
                    , '<select name="ndegree" lay-filter="ndegree">'
                    , '<option value="9">九成新</option>'
                    , '<option value="8">八成新</option>'
                    , '<option value="7">七成新</option>'
                    , '<option value="6">六成新</option>'
                    , '<option value="5">五成新</option>'
                    , '</select>'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label" style="text-align: left">理想价格</label>'
                    , '<div class="layui-input-block">'
                    , '<input required name="price" lay-verify="required" placeholder="理想价格" value="" class="layui-input">'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item layui-form-text">'
                    , '<label class="layui-form-label" style="text-align: left">详细描述</label>'
                    , '<div class="layui-input-block layui-form-text">'
                    , '<textarea required name="content" lay-verify="required" autocomplete="off" placeholder="详细介绍" class="layui-textarea"></textarea>'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label" style="text-align: left">商品封面</label>'
                    , '<div class="layui-input-inline" style="width:auto;">'
                    , '<input type="hidden" name="cover" lay-verify="" id="cover" class="layui-input fly-case-image cover">'
                    , '<button type="button" class="layui-btn layui-btn-primary" id="cover_upload">'
                    , '<i class="layui-icon">&#xe67c;</i>上传图片'
                    , '</button>'
                    // , '<div class="layui-upload-list">'
                    // , '<img class="layui-upload-img" id="demo1">'
                    // , '<p id="demoText"></p>'
                    // , '</div>'
                    , '</div>'
                    , '<div class="layui-form-mid layui-word-aux" id="preview">推荐尺寸：478*300，大小不能超过 20MB</div>'
                    , '</li>'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label" style="text-align: left">商品图片</label>'
                    , '<div class="layui-input-inline" style="width:auto;">'
                    , '<input type="hidden" name="picture" lay-verify="" id="picture" class="layui-input fly-case-image picture">'
                    , '<button type="button" class="layui-btn layui-btn-primary" id="pic_upload">'
                    , '<i class="layui-icon">&#xe67c;</i>上传图片'
                    , '</button>'
                    , '</div>'
                    , '<div class="layui-form-mid layui-word-aux" id="preview">可上传多张图片</div>'
                    , '</li>'
                    , '<li class="layui-form-item">'
                    , '<label class="layui-form-label"> </label>'
                    , '<div class="layui-input-block">'
                    , '<input type="checkbox" name="agree" id="agree" title="我已查看且同意薄荷市场管理条例" lay-skin="primary">'
                    , '</div>'
                    , '</li>'
                    , '<li class="layui-form-item">'
                    , '<div class="layui-input-block">'
                    , '<button type="button" lay-submit lay-filter="submitGood" class="layui-btn">提交案例</button>'
                    , '</div>'
                    , '</li>'
                    , '</ul>'
                ].join('')
                , success:
                    function (layero, index) {
                        var covimg = layero.find('.cover');
                        var picimg = layero.find('.picture');
                        var preview = $('#preview');

                        upload.render({
                            url: '/good/uploadGoodPic.do'
                            , elem: '#cover_upload'
                            //预读本地文件示例，不支持ie8
                            // , before: function (obj) {
                            //     //预读本地文件示例，不支持ie8
                            //     obj.preview(function (index, file, result) {
                            //         $('#demo1').attr('src', result); //图片链接（base64）
                            //     });
                            // }
                            , field: "goodPic"
                            , done: function (data) {
                                if (data.status == 0) {
                                    layer.msg(data.msg);
                                    covimg.val(data.data);
                                    preview.html('<a href="' + data.data + '" target="_blank" style="color: #5FB878;">封面已上传，点击可预览</a>');
                                } else {
                                    layer.msg(data.msg, {icon: 5});
                                }
                            }
                        });

                        upload.render({
                            url: '/good/uploadGoodPic.do'
                            , elem: '#pic_upload'
                            //预读本地文件示例，不支持ie8
                            // , before: function (obj) {
                            //     //预读本地文件示例，不支持ie8
                            //     obj.preview(function (index, file, result) {
                            //         $('#demo1').attr('src', result); //图片链接（base64）
                            //     });
                            // }
                            , multiple: true
                            , field: "goodPic"
                            , done: function (data) {
                                if (data.status == 0) {
                                    layer.msg(data.msg);
                                    if (picimg.val() == "") {
                                        picimg.val(data.data);
                                    } else {
                                        picimg.val(picimg.val() + ";" + data.data);
                                    }
                                    // preview.html('<a href="' + data.data + '" target="_blank" style="color: #5FB878;">封面已上传，点击可预览</a>');
                                } else {
                                    layer.msg(data.msg, {icon: 5});
                                }
                            }
                        });

                        // upload.render({
                        //     url: '/good/uploadGoodCover.do'
                        //     , elem: '#picture_upload'
                        //     //预读本地文件示例，不支持ie8
                        //     // , before: function (obj) {
                        //     //     //预读本地文件示例，不支持ie8
                        //     //     obj.preview(function (index, file, result) {
                        //     //         $('#demo1').attr('src', result); //图片链接（base64）
                        //     //     });
                        //     // }
                        //     , field: "cover"
                        //     , multiple: true
                        //     , done: function (data) {
                        //         if (data.status == 0) {
                        //             layer.msg(data.msg);
                        //             image.val(data.data);
                        //             preview.html('<a href="' + data.data + '" target="_blank" style="color: #5FB878;">封面已上传，点击可预览</a>');
                        //         } else {
                        //             layer.msg(data.msg, {icon: 5});
                        //         }
                        //     }
                        // });

                        form.render('radio');
                        form.render('select');


                        form.on('radio(isused)', function (data) {
                            if ($('#isused input[name="isused"]:checked ').val() != "0") {
                                $("#ndegree").show();
                            } else {
                                $("#ndegree").hide();
                            }
                            form.render();
                        });

                        form.render('checkbox').on('submit(submitGood)', function (data) {
                            if (!data.field.agree) {
                                return layer.tips('您需要统一管理条例才能发布商品', $('#agree').next(), {tips: 1});
                            }
                            fly.json('/good/publishAGood.do', data.field, function (res) {
                                layer.close(index);
                                layer.alert(res.msg, {
                                    icon: 1
                                })
                            });
                        });
                    }
            })
            ;
        }

        //点赞
        , praise: function (othis) {
            var li = othis.parents('li')
                , PRIMARY = 'layui-btn-primary'
                , unpraise = !othis.hasClass(PRIMARY)
                , numElem = li.find('.fly-case-nums')

            fly.json('/case/praise/', {
                id: li.data('id')
                , unpraise: unpraise ? true : null
            }, function (res) {
                numElem.html(res.praise);
                if (unpraise) {
                    othis.addClass(PRIMARY).html('点赞');
                    layer.tips('少了个赞囖', numElem, {
                        tips: 1
                    });
                } else {
                    othis.removeClass(PRIMARY).html('已赞');
                    layer.tips('成功获得个赞', numElem, {
                        tips: [1, '#FF5722']
                    });
                }
            });
        }

        //查看点赞用户
        , showPraise: function (othis) {
            var li = othis.parents('li');
            if (othis.html() == 0) return layer.tips('该项目还没有收到赞', othis, {
                tips: 1
            });
            fly.json('/case/praise_user/', {
                id: li.data('id')
            }, function (res) {
                var html = '';
                layer.open({
                    type: 1
                    , title: '项目【' + res.title + '】获得的赞'
                    , id: 'LAY_showPraise'
                    , shade: 0.8
                    , shadeClose: true
                    , area: '305px'
                    , skin: 'layer-ext-case'
                    , content: function () {
                        layui.each(res.data, function (_, item) {
                            html += '<li><a href="/u/' + 168 * item.id + '/" target="_blank" title="' + item.username + '"><img src="' + item.avatar + '"></a></li>'
                        });
                        return '<ul class="layer-ext-ul">' + html + '</ul>';
                    }()
                })
            });
        }
    };

    $('body').on('click', '.fly-case-active', function () {
        var othis = $(this), type = othis.data('type');
        active[type] && active[type].call(this, othis);
    });

    exports('case', {});
});