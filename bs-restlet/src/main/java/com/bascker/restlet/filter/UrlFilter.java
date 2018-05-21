package com.bascker.restlet.filter;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.routing.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Restlet 案例(二) Filter 的使用
 *
 * 1.Filter
 *  1.1 用于请求的预处理: 先预处理请求，然后调用关联的 Restlet, 最后在处理响应
 *  1.2 多种专门的 Filter
 *      1) Extractor: 找类似 cookies 的数据 & 查询参数，将它们作为请求属性保存
 *      2) Validator: 针对正则表达式验证属性值
 *  1.3 三个方法:
 *      1) beforeHandler(): 预处理,返回一个结果标记以指定处理流程是否继续到下一个 Restlet 或跳过下一个 Restlet 或立即停止返回线程栈
 *      2) doHandler(): 若 beforeHandler 返回继续下一 Restlet, 则调用连接到它的下一个 Restlet，若没有，则返回 HTTP status 500
 *      3) afterHandler(): 若 doHandler 返回或 beforeHandler 返回跳过下一 Restlet 标记, 则调用 afterHandler, 处理
 *
 * @author bascker
 */
public class UrlFilter extends Filter {

    private static final Logger LOG = LoggerFactory.getLogger(UrlFilter.class);
    private final Set<String> mBlockedUrl;

    public UrlFilter(final Context context) {
        super(context);
        mBlockedUrl = new CopyOnWriteArraySet<>();
        mBlockedUrl.add("/user");
    }

    @Override
    protected int beforeHandle(final Request request, final Response response) {
        // request.getResourceRef() 会输出全路径, 即 http://ip:port/path
        // getPath() 则只会输出 /path 部分
        final String path = request.getResourceRef().getPath();
        if (getBlockedUrl().contains(path)) {
            response.setStatus(Status.CLIENT_ERROR_FORBIDDEN, "Your url was blocked");

            // 立即停止，返回线程栈
           return STOP;
        }

        // 继续下一个 Restlet
        return CONTINUE;
    }

    public Set<String> getBlockedUrl() {
        return mBlockedUrl;
    }
}
