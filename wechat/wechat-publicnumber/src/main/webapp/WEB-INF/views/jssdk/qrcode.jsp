<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    final String path = request.getContextPath();
    final String title = "JSSDK--微信扫一扫";
%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <title><%= title%></title>
        <link href="<%= path %>/assert/lib/wechat/weui/weui.css" rel="stylesheet"/>
        <script src="<%= path %>/assert/lib/wechat/weui/zepto.min.js"></script>
        <script src="<%= path%>/assert/lib/wechat/jssdk/jweixin-1.2.0.js"></script>

        <script type="text/javascript">
            /**
             * 1.通过 config 接口注入权限验证配置：
             *  1.1 所有需要使用JS-SDK的页面必须先注入配置信息，否则将无法调用
             *  1.2 config信息验证后会执行ready方法, 所有接口调用都必须在config接口获得结果之后
             */
            wx.config({
                debug: true,                        // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: '${appId}',                  // 必填，公众号的唯一标识
                timestamp: '${ timestamp}',         // 必填，生成签名的时间戳
                nonceStr: '${ nonceStr}',           // 必填，生成签名的随机串
                signature: '${ signature}',         // 必填，签名，见附录1
                jsApiList: [                        // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    'scanQRCode'                    // 微信扫一扫接口
                ]
            });

            /**
             * 2.通过ready接口处理成功验证：
             *  2.1 config是一个客户端的异步操作，若需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行
             *  2.2 对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
             */
            wx.ready(function(){
                document.querySelector('#btn1').onclick = function () {
                    wx.scanQRCode();
                };

                document.querySelector('#btn2').onclick = function () {
                    wx.scanQRCode({
                        needResult: 1,                       // 默认为0，扫描结果由微信处理，1则直接返回扫描结果
                        desc: 'scanQRCode desc',
                        success: function (res) {            // 接口调用成功时执行的回调函数
                            var url = res.resultStr;
                            // 扫一扫商品条形码，取","后面的
                            if (url.indexOf(",") >= 0) {
                                var tempArray = url.split(','),
                                    tempNum = tempArray[1];
                                alert(tempNum);
                            } else {
                                alert(url);
                            }
                        }
                    });
                };
            });

            /**
             * 3.通过error接口处理失败验证
             *  3.1 config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
             */
            wx.error(function(res){
                alert(res);
            });
        </script>
    </head>
    <body>
        <div class="weui-panel">
            <div class="weui-panel__hd">
                <h1><%= title%></h1>
            </div>
            <div class="weui-panel__hd">
                <div class="weui-btn-area">
                    <button id="btn1" class="weui-btn weui-btn_primary">scanQRCode(微信处理结果)</button>
                    <button id="btn2" class="weui-btn weui-btn_primary">scanQRCode(直接返回结果)</button>
                </div>
            </div>
        </div>
    </body>
</html>