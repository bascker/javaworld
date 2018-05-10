<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    final String path = request.getContextPath();
%>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>WeChat 网页授权</title>
    <link href="<%= path %>/assert/lib/wechat/weui/weui.css" rel="stylesheet" />
</head>
<body>
<div class="weui-panel">
    <div class="weui-cells__title"><h3>用户信息</h3></div>
    <div class="weui-cells weui-cells_form">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">ID</label></div>
            <div class="weui-cell__bd">${user.openid}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">昵称</label></div>
            <div class="weui-cell__bd">${user.nickname}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">头像</label></div>
            <div class="weui-cell__bd"><img style="width: 40px;height: 40px;" src="${user.headimgurl}"></div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">性别</label></div>
            <div class="weui-cell__bd">${user.sex}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">国家</label></div>
            <div class="weui-cell__bd">${user.country}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">省份</label></div>
            <div class="weui-cell__bd">${user.province}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">城市</label></div>
            <div class="weui-cell__bd">${user.city}</div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">特权</label></div>
            <div class="weui-cell__bd">${user.privilege}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">UnionID</label></div>
            <div class="weui-cell__bd">${user.unionid}</div>
        </div>
    </div>
</div>
</body>
</html>