# Ehcache
*Ehcache* 是一个支持分布式存储的缓存组件，主要用于应用内缓存，其数据结构较为单一，只支持 *key-value* 形式的数据存储。其缓存数据存放
方式有 4 种:

* on-heap: Java 堆内存
* off-heap: 堆外内存(非 Java 堆)
* disk: 磁盘
* cluster: 集群存储