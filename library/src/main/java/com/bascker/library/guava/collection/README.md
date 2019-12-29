# 集合
1、不可变集合：ImmutableXXX, 如 ImmutableSet
* 安全
* 内存利用率更好：无需考虑变化
* 可作为常量
* 不接受 null 值

2、Multiset：
* 新的集合类型, 允许 Set 中同时存在相同的元素
* SortedMultiSet 是其变种，支持排序

3、Multimap:
* 新的集合类型，允许一个 key 映射到多个 value
