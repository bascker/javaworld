package com.bascker.springframework.interceptor;

/**
 * 拦截器
 *
 * @author bascker
 */
public interface Interceptor {

    /**
     * Action 执行前调用: 前置通知
     */
    void before();

    /**
     * Action 执行后调用: 后置通知
     */
    void after();


}
