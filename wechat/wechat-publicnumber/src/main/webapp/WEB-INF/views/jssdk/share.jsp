<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    final String path = request.getContextPath();
    final String title = "JSSDK--分享";
    final String desc = "哈哈哈，就是个测试~";
    final String imgUrl = "http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg";
%>
<html>
    <head>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <title><%= title %></title>
        <link href="<%= path %>/assert/lib/wechat/weui/weui.css" rel="stylesheet"/>
        <script src="<%= path %>/assert/lib/wechat/weui/zepto.min.js"></script>
        <script src="<%= path%>/assert/lib/wechat/jssdk/jweixin-1.2.0.js"></script>
        <script>
            wx.config({
                debug: true,                        // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: '${appId}',                  // 必填，公众号的唯一标识
                timestamp: '${ timestamp}',         // 必填，生成签名的时间戳
                nonceStr: '${ nonceStr}',           // 必填，生成签名的随机串
                signature: '${ signature}',         // 必填，签名，见附录1
                jsApiList: [                        // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    'onMenuShareTimeline',          // 分享到朋友圈
                    'onMenuShareAppMessage',        // 分享给朋友
                    'onMenuShareQQ',                // 分享到QQ
                    'onMenuShareWeibo',             // 分享到微博
                    'onMenuShareQZone'              // 分享到 QQ 空间
                ]
            });

            wx.ready(function(){
                /*
                 * 以按钮 onclick 进行事件注册
                 * 测试方法：
                 * 1.点击按钮进行事件注册：若不注册，则 title,desc 等都是默认值，而不会显示自定义内容
                 * 2.点击 WeChat 对应功能（分享到朋友...）
                 */
                // 1.1 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
                document.querySelector("#onMenuShareTimeline").onclick = function () {
                    wx.onMenuShareTimeline({
                        title: '<%= title%>到朋友圈',
                        desc: '<%= desc%>',
                        link: '${ url }',
                        imgUrl: '<%= imgUrl%>',
                        trigger: function (res) {
                            alert("用户点击分享到朋友圈");
                        },
                        success: function () {      // 用户确认分享后执行的回调函数
                            alert('已分享');
                        },
                        cancel: function () {       // 用户取消分享后执行的回调函数
                            alert('已取消');
                        },
                        fail: function (res) {
                            alert(JSON.stringify(res));
                        }
                    });
                    alert('已注册获取“分享到朋友圈”状态事件');
                };

                // 1.2 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
                document.querySelector("#onMenuShareAppMessage").onclick = function () {
                    wx.onMenuShareAppMessage({
                        title: '<%= title%>给朋友',  // 分享标题
                        desc: '<%= desc%>',
                        link: '${ url }',         // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                        imgUrl: '<%= imgUrl%>',     // 分享图标
                        type: '',                   // 分享类型,music、video或link，不填默认为link
                        dataUrl: '',                // 如果type是music或video，则要提供数据链接，默认为空
                        trigger: function () {      // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
                            alert("用户点击分享给朋友");
                        },
                        success: function () {
                            alert("已分享");
                        },
                        cancel: function () {
                            alert("已取消");
                        },
                        fail : function (res) {
                            alert(JSON.stringify(res));
                        }
                    });
                    alert('已注册获取“分享给朋友”状态事件');
                };

                // 1.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
                document.querySelector('#onMenuShareQQ').onclick = function () {
                    wx.onMenuShareQQ({
                        title: '<%= title%>到QQ',
                        desc: '<%= desc%>',
                        link: '${ url }',
                        imgUrl: '<%= imgUrl%>',
                        type: '',                   // 分享类型,music、video或link，不填默认为link
                        dataUrl: '',                // 如果type是music或video，则要提供数据链接，默认为空
                        trigger: function () {
                            alert("用户点击分享到QQ");
                        },
                        success: function () {      // 用户确认分享后执行的回调函数
                            alert('已分享');
                        },
                        cancel: function () {       // 用户取消分享后执行的回调函数
                            alert('已取消');
                        },
                        fail: function (res) {
                            alert(JSON.stringify(res));
                        }
                    });
                    alert('已注册获取“分享到QQ”状态事件');
                };

                // 1.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
                document.querySelector('#onMenuShareWeibo').onclick = function () {
                    wx.onMenuShareWeibo({
                        title: '<%= title%>到微博',
                        desc: '<%= desc%>',
                        link: '${ url }',
                        imgUrl: '<%= imgUrl%>',
                        type: '',                   // 分享类型,music、video或link，不填默认为link
                        dataUrl: '',                // 如果type是music或video，则要提供数据链接，默认为空
                        trigger: function () {
                            alert("用户点击分享到微博");
                        },
                        success: function () {      // 用户确认分享后执行的回调函数
                            alert('已分享');
                        },
                        cancel: function () {       // 用户取消分享后执行的回调函数
                            alert('已取消');
                        },
                        fail: function (res) {
                            alert(JSON.stringify(res));
                        }
                    });
                    alert('已注册获取“分享到微博”状态事件');
                };

                // 1.5 监听“分享到QZone”按钮点击、自定义分享内容及分享接口
                document.querySelector('#onMenuShareQZone').onclick = function () {
                    wx.onMenuShareWeibo({
                        title: '<%= title%>到QQ空间',
                        desc: '<%= desc%>',
                        link: '${ url }',
                        imgUrl: '<%= imgUrl%>',
                        type: '',                   // 分享类型,music、video或link，不填默认为link
                        dataUrl: '',                // 如果type是music或video，则要提供数据链接，默认为空
                        trigger: function (res) {
                            alert("用户点击分享到QQ空间");
                        },
                        success: function () {      // 用户确认分享后执行的回调函数
                            alert('已分享');
                        },
                        cancel: function () {       // 用户取消分享后执行的回调函数
                            alert('已取消');
                        },
                        fail: function (res) {
                            alert(JSON.stringify(res));
                        }
                    });
                    alert('已注册获取“分享到QZone”状态事件');
                };
            });

            wx.error(function (res) {
                alert(res.errMsg);
            });
        </script>
    </head>
    <body>
        <div class="weui-panel weui-panel__hd">
            <div class="weui-btn-area">
                <div id="onMenuShareTimeline" class="weui-btn weui-btn_primary">分享到朋友圈</div>
                <div id="onMenuShareAppMessage" class="weui-btn weui-btn_primary">分享给朋友</div>
                <div id="onMenuShareQQ" class="weui-btn weui-btn_primary">分享到QQ</div>
                <div id="onMenuShareWeibo" class="weui-btn weui-btn_primary">分享到微博</div>
                <div id="onMenuShareQZone" class="weui-btn weui-btn_primary">分享到QZone</div>
            </div>
        </div>
    </body>
</html>
