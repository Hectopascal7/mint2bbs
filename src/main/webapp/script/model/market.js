layui.cache.page = 'market';
layui.cache.user = {
    username: '游客'
    , uid: -1
    , avatar: '../../../image/avatar/00.jpg'
    , experience: 83
    , sex: '男'
};
layui.config({
    version: "3.0.0"
    , base: '/script/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
    fly: 'index'
}).use('fly');

// 初始化数据
// 当前查看页码
var page = 1;
// 当前排序顺序
var order = "ptime";
var isused = 1;

// 执行初始化商品列表
getGoodList();

// 按发布时间显示商品列表
function getGoodListByPtime() {
    $("#getGoodListByPtime").addClass("layui-this");
    $("#getGoodListByPrice").removeClass("layui-this");
    order = "ptime";
    page = 1;
    getGoodList()
}

// 按商品价格显示商品列表
function getGoodListByPrice() {
    $("#getGoodListByPrice").addClass("layui-this");
    $("#getGoodListByPtime").removeClass("layui-this");
    order = "price";
    page = 1;
    getGoodList()
}

// 显示二手商品
function getOldGoodList() {
    $("#getOldGoodList").addClass("tab-this");
    $("#getNewGoodList").removeClass("tab-this");
    isused = 1;
    page = 1;
    getGoodList()
}

// 显示崭新商品
function getNewGoodList() {
    $("#getNewGoodList").addClass("tab-this");
    $("#getOldGoodList").removeClass("tab-this");
    isused = 0;
    page = 1;
    getGoodList();

}

// 获取商品列表
function getGoodList() {
    layui.use('layer', function () {
        // ajax交互，JS中以定义的layer无法获取（猜测），手动定义一个
        // 定义layer，用于打印提示信息
        var layer = layui.layer;
        // 商品列表数据
        var good;
        // ajax-获取帖子列表
        $.ajax({
            type: 'post',
            url: '/good/getGoodListWithPage.do',
            dataType: 'json',
            data: {
                page: page,
                isused: isused,
                order: order
            },
            async: false,
            success: function (data) {
                good = data.data;
            },
            error: function (data) {
                layer.msg("获取商品列表失败！");
            }
        })
        // 模板引擎渲染
        layui.use('laytpl', function () {
            // 定义一个模板引擎
            laytpl = layui.laytpl;
            // 帖子列表渲染
            var good_list_model = document.getElementById('m_good_list').innerHTML;
            var good_list_view = document.getElementById('good_list');
            laytpl(good_list_model).render(good, function (html) {
                // 当前只写了获取之前当前页所有数据的方法，是否使用追加有待商讨
                // 更新innerHTML，获取数据量越来越大，需要获取当前页之前所有数据，适用于实时更新
                good_list_view.innerHTML = html;
                // 追加innerHTML，获取数据量小，只用获取新页面的数据，适用于不实时更新
                // post_view.innerHTML += html;
            });
        });
    })
}

// 获取更多商品
function getMore() {
    page += 1;
    // 商品列表数据
    var good;
    // ajax-获取帖子列表
    $.ajax({
        type: 'post',
        url: '/good/getGoodListWithPage.do',
        dataType: 'json',
        data: {
            page: page,
            isused: isused,
            order: order,
            limit: 1
        },
        async: false,
        success: function (data) {
            good = data.data;
        },
        error: function (data) {
            layer.msg("获取商品列表失败！");
        }
    })

    // 模板引擎渲染
    layui.use('laytpl', function () {
        // 定义一个模板引擎
        laytpl = layui.laytpl;

        // 商品列表渲染
        var good_list_model = document.getElementById('m_good_list').innerHTML;
        var good_list_view = document.getElementById('good_list');
        laytpl(good_list_model).render(good, function (html) {
            // 当前只写了获取之前当前页所有数据的方法，是否使用追加有待商讨
            // 更新innerHTML，获取数据量越来越大，需要获取当前页之前所有数据，适用于实时更新
            good_list_view.innerHTML = html;
            // 追加innerHTML，获取数据量小，只用获取新页面的数据，适用于不实时更新
            // post_view.innerHTML += html;
        });
    });
}

// 修改商品价格
function updatePrice(gid) {
    var text = '<div class="layui-form-item" style="padding-top: 10px">\n' +
        '<label class="layui-form-label">请输入价格</label>\n' +
        '<div class="layui-input-inline">\n' +
        '<input type="text" id="price" placeholder="（元）" style="text-align: center"  autocomplete="off" class="layui-input">\n' +
        '</div>\n' +
        '</div>';
    layer.open({
        type: 1
        , id: 'updatePrice' //防止重复弹出
        , title: '修改价格'
        , content: text
        , btn: '查询'
        , btnAlign: 'c' //按钮居中
        , shade: 0.8 //不显示遮罩
        , yes: function (index, layero) {
            var price = $("#price").val();
            if (!/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/.test(price)) {
                layer.msg("请输入大于0的数字");
                return;
            }
            $.ajax({
                type: 'post',
                url: '/good/updateGoodPrice.do',
                data: {
                    gid: gid,
                    price: price
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    layer.msg(data.msg);
                },
                error: function (data) {
                    layer.msg("调用服务失败！");
                }
            })
            layer.close(index);
            reloadGoodList();
        }
    });

}

// 重载我的商品
function reloadGoodList() {
    $.ajax({
        type: 'post',
        url: '/good/getMyGoodList.do',
        dataType: 'json',
        async: false,
        success: function (data) {
            good = data.data;
            layui.use('laytpl', function () {
                laytpl = layui.laytpl;
                document.getElementById('GoodList').innerHTML = laytpl((document.getElementById('reload_good_list').innerHTML)).render(good);
            });
        },
        error: function (data) {
            layer.msg("获取您的商品列表失败！");
        }
    })
}

// 显示我的商品
function showMyGoods() {
    var list_html = '';
    // 商品列表数据
    var good;
    // ajax-获取商品列表
    $.ajax({
        type: 'post',
        url: '/good/getMyGoodList.do',
        dataType: 'json',
        data: {
            page: 1,
            isused: 0,
            order: 'ptime',
            limit: 1
        },
        async: false,
        success: function (data) {
            good = data.data;
            layui.use('laytpl', function () {
                laytpl = layui.laytpl;
                laytpl((document.getElementById('my_good_list').innerHTML)).render(good, function (html) {
                    list_html = html;
                });
            });
        },
        error: function (data) {
            layer.msg("获取您的商品列表失败！");
        }
    })
    layer.open({
        type: 1,
        title: '我的商品',
        id: 'myGoodList',
        shade: 0.8,
        shadeClose: true,
        area: ['1130px', '600px'],
        skin: 'layer-ext-case',
        content: list_html
    })
}

// function openGoodDetail(gid) {
//     layer.open({
//         type: 2,
//         title: '我的商品',
//         id: 'myGoodList',
//         shade: 0.8,
//         shadeClose: true,
//         area: ['900px', '600px'],
//         skin: 'layer-ext-case',
//         content: '/page/portal/market/detail2.html?gid=' + gid
//     })
// }

// 设置商品已售出
function setGoodIsSaled(gid) {
    layer.confirm('设置商品已售出?', {icon: 3, title: '提示'}, function () {
        $.ajax({
            type: 'post',
            url: '/good/setGoodIsSaled.do',
            dataType: 'json',
            data: {
                gid: gid
            },
            async: false,
            success: function (data) {
                layer.msg(data.msg);
                reloadGoodList();
            },
            error: function (data) {
                layer.msg("调取服务失败！");
            }
        })
    });
}


layui.use(['layer', 'form', 'laytpl', 'upload', 'fly'], function () {

    // 定义layer，用于打印提示信息
    var layer = layui.layer;
    var form = layui.form;
    var upload = layui.upload;
    var device = layui.device();
    var $ = layui.jquery;
    var laytpl = layui.laytpl;
    var fly = layui.fly;

    // 用户中心数据
    var user_info;
    // ajax-用户数据中心数据获取
    $.ajax({
        type: 'post',
        url: '/user/userCenter.do',
        dataType: 'json',
        async: false,
        success: function (data) {
            user_info = data.data;
        },
        error: function (data) {
            layer.msg("获取用户中心数据失败！");
        }
    })

    // 用户中心渲染
    var center_model = document.getElementById('m_center').innerHTML;
    var center_view = document.getElementById('center');
    laytpl(center_model).render(user_info, function (html) {
        center_view.innerHTML = html;
    });

    // 商品图片是否上传校验
    form.verify({
        // 封面必填校验
        cover: function (value) { //value：表单的值、item：表单的DOM对象
            if (value.trim().length == 0) {
                return '还未上传商品封面！';
            }
        },
        // 图片必填校验
        picture: function (value) { //value：表单的值、item：表单的DOM对象
            if (value.trim().length == 0) {
                return '还未上传商品图片！';
            }
        },
    });

    var active = {
        publishGood: function (othis) {
            layer.open({
                type: 1,
                id: 'GoodPublishTab',
                title: '发布商品',
                area: (device.ios || device.android) ? ($(window).width() + 'px') : '660px',
                content: $('#publishGood').text(),
                success: function (layero, index) {

                    var covimg = layero.find('.cover');
                    var picimg = layero.find('.picture');
                    var previewCover = $('#previewCover');
                    var previewPic = $('#previewPic');

                    // 商品封面上传
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
                                previewCover.html('<a href="' + data.data + '" target="_blank" style="color: #5FB878;">封面已上传，点击可预览。</a>');
                            } else {
                                layer.msg(data.msg, {icon: 5});
                            }
                        }
                    });

                    // 商品图片上传
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
                                previewPic.html('<a target="_blank" style="color: #5FB878;">图片已上传，可继续上传。</a>');
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

                    // 需要格外渲染单选按钮
                    form.render('radio');

                    // 需要格外渲染下拉列表
                    form.render('select');

                    // 根据商品类别显示/隐藏商品新旧程度选项
                    form.on('radio(isused)', function (data) {
                        if ($('#isused input[name="isused"]:checked ').val() != "0") {
                            $("#ndegree").show();
                        } else {
                            $("#ndegree").hide();
                        }
                        form.render();
                    });


                    form.on('submit(demo1)', function (data) {
                        console.log(data.field)
                        return false;
                    });

                    // 提交商品信息
                    form.render('checkbox').on('submit(submitGood)', function (data) {
                        if (!data.field.isused) {
                            layer.alert('请选择商品类别');
                            return;
                        }
                        if (!data.field.agree) {
                            return layer.tips('您需要同意管理条例才能发布商品', $('#agree').next(), {tips: 1});
                        }
                        fly.json('/good/publishAGood.do', data.field, function (res) {
                            layer.close(index);
                            layer.alert(res.msg, {
                                icon: 1
                            })
                            getGoodList();
                        });
                    });
                }
            });
        }
    };

    $('body').on('click', '.fn-button', function () {
        var othis = $(this), type = othis.data('type');
        active[type] && active[type].call(this, othis);
    });


})