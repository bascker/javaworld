package com.bascker.implement.juc;

/**
 * Block Queue: 自实现阻塞队列
 *
 * @author bascker
 */
public interface BlockQueue<T> {

    /**
     * 阻塞入队
     * @param t
     */
    void put (final T t);

    /**
     * 阻塞出队
     * @return
     */
    T take ();

}
