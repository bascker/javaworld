# CGLIB
*CGLIB* 全称 *Code Generation Library*，是一个高性能的代码生成类库，可在运行期动态生成字节码。*CGLIB* 底层使用 *ASM*(一个 *Java* 字
节码操纵框架) 来操作字节码生成新的类。*CGLib* 比 *Java* 的 `java.lang.reflect.Proxy` 类更强的在于它不仅可以接管接口类的方法，还可以
接管普通类的方法。

*CGLIB VS. Java* 动态代理
 
* 代理对象: *Java* 动态代理只能够对接口进行代理，不能对普通的类进行代理(因为所有生成的代理类的父类为 *Proxy*, *Java* 类继承机制不
允许多重继承),而 *CGLIB* 能够代理普通类
* 效率: *Java* 动态代理使用 *Java* 原生的反射 *API* 进行操作，在生成类上比较高效; *CGLIB* 使用 *ASM* 框架直接对字节码进行操作，
在类的执行过程中比较高效


## 项目结构
*CGLIB* 的项目结构如下
~~~
cglib
|- beans        # JavaBean工具类
|- core         # 核心代码, 底层字节码处理类
|- proxy        # 用于创建代理和方法拦截
|- reflect      # 用于快速反射，并提供了C#风格的委托
|- transform    # 用于class文件运行时转换或编译时转换
|- util         # 集合排序工具类
~~~

## 附录
CGLIB 的相关资料
* [源码](https://github.com/cglib/cglib)
* [JavaDoc 文档](http://cglib.sourceforge.net/apidocs/index.html)
* [ASM](http://tool.oschina.net/apidocs/apidoc?api=asm)
* [CGLIB 详解](https://blog.csdn.net/danchu/article/details/70238002)



