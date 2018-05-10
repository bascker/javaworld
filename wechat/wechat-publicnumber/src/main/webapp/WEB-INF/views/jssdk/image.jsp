<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    final String path = request.getContextPath();
    final String title = "JSSDK--图像接口";
%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
        <title><%= title%></title>
        <link href="<%= path %>/assert/lib/wechat/weui/weui.css" rel="stylesheet" />
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
                    'chooseImage',
                    'previewImage',
                    'uploadImage',
                    'downloadImage',
                    'getLocalImgData'
                ]
            });

            wx.ready(function () {
                var imgs = {
                    localId: [],
                    serverId: []
                };

                // 1.1 拍照、本地选图
                document.querySelector("#chooseImage").onclick = function () {
                    wx.chooseImage({
                        count: 1, // 默认9
                        sizeType: ['original', 'compressed'],   // 可以指定是原图还是压缩图，默认二者都有
                        sourceType: ['album', 'camera'],        // 可以指定来源是相册还是相机，默认二者都有
                        success: function (res) {
                            imgs.localId = res.localIds;        // 返回选定照片的本地 ID 列表，localId 可作为 img 标签的 src 属性显示图片
                            var $imgBox = $("#img-box");
                            $imgBox.append('<h3>已选择 ' + imgs.localId.length + ' 张图片</h3>');
                            for (var i in imgs.localId) {
                                alert(imgs.localId[i]);
                                $imgBox.append('<img src="' + imgs.localId[i] + '" style="width: 100%;"/>');
                            }
                        }
                    });
                };

                // 1.2 图片预览
                document.querySelector("#previewImage").onclick = function () {
                    wx.previewImage({
                        current: 'weixin://resourceid/0811a0cc4f8e0b0d0bbb656c5c4fb4c5',    // 当前显示图片的 http 链接
                        urls: [                                                             // 需要预览的图片http 链接列表
                            'weixin://resourceid/0811a0cc4f8e0b0d0bbb656c5c4fb4c5',
                            'weixin://resourceid/445f4ac3525a5aa9984db2e91784531e',
                            'weixin://resourceid/0811a0cc4f8e0b0d0bbb656c5c4fb4c5'
                        ]
                    });
                };

                // 1.3 上传图片
                document.querySelector("#uploadImage").onclick = function () {
                    if (imgs.localId.length == 0) {
                        alert('请先使用 chooseImage 接口选择图片');
                        return;
                    }
                    var i = 0,
                        length = imgs.localId.length;
                    imgs.serverId = [];
                    function upload() {
                        wx.uploadImage({
                            localId: imgs.localId[i],           // 需要上传的图片的本地 ID，由 chooseImage 接口获得
                            isShowProgressTips: 1,              // 默认为 1，显示进度提示
                            success: function (res) {
                                i ++;
                                var serverId = res.serverId;    // 返回图片的服务器端 ID
                                imgs.serverId.push(serverId);
                                if (i < length) {
                                    upload();
                                }
                            },
                            fail: function (res) {
                                alert(JSON.stringify(res));
                            }
                        });
                    }

                    upload();
                };

                // 1.4 下载图片
                document.querySelector("#downloadImage").onclick = function () {
                    if (imgs.serverId.length === 0) {
                        alert('请先使用 uploadImage 上传图片');
                        return;
                    }
                    var i = 0,
                        length = imgs.serverId.length;
                    imgs.localId = [];
                    function download() {
                        wx.downloadImage({
                            serverId: imgs.serverId[i],
                            isShowProgressTips: 1,              // 默认为1，显示进度提示
                            success: function (res) {
                                i ++;
                                alert('已下载：' + i + '/' + length);
                                imgs.localId.push(res.localId);
                                if (i < length) {
                                    download();
                                }
                            }
                        });
                    }
                    download();
                };

                // 1.5 此接口仅在 iOS WKWebview 下提供
                document.querySelector("#getLocalImgData").onclick = function () {
                    wx.getLocalImgData({
                        localId: imgs.localId,                  // 图片的 localID
                        success: function (res) {
                            var localData = res.localData;      // localData 是图片的 base64 数据，可以用 img 标签显示
                            alert(localData);
                        }
                    });
                };
            });

            wx.error(function (res) {
                alert(res.errMsg);
            });
        </script>
    </head>
    <body>
        <div class="weui-panel">
            <div class="weui-btn-area">
                <div id="chooseImage" class="weui-btn weui-btn_primary">拍照或相册选图</div>
                <div id="previewImage" class="weui-btn weui-btn_primary">预览图片</div>
                <div id="uploadImage" class="weui-btn weui-btn_primary">上传图片</div>
                <div id="downloadImage" class="weui-btn weui-btn_primary">下载图片</div>
                <div id="getLocalImgData" class="weui-btn weui-btn_primary">本地图片</div>
            </div>
        </div>

        <div class="weui-panel weui-panel__hd">
            <div id="img-box" style="width: 100%;"></div>
        </div>
    </body>
</html>