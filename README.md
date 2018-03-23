# javaworld
Java 学习中的点点滴滴

## 目录结构
javaworld 以 maven 来进行管理，各个子模块有自己的分工，具体如下：
* advance: 进阶知识
* base: 基础知识
* bsutil: 通用工具, 包括 Log 日志
* design-pattern: 设计模式
* general: 常用知识
* implement: 利用所学/源码解析自实现的类, 如自实现栅栏
* library: 三方库的使用
* sample: 存放经典案例, 如哲学家就餐问题, 生产者-消费者问题


## 规范
为便于代码管理与阅读，进行如下约束：
* 所有内部代码走`main()`函数运行的，实现`com.bascker.bsutil.Sample`接口，并且在不影响阅读时，类名尽量以`Sample`结尾
* `src/main`目录下的代码中使用 `@Test` 注解来对某一知识点进行测试的，类名以`Cases`结尾
* 其他正常 Java 类的测试, 还是按照 maven 项目结构来，走`src/test`目录，单元测试类名以`Test`结尾