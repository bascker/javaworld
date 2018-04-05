# CGLIB
*CGLIB* 全称 *Code Generation Library*，是一个高性能的代码生成类库，可在运行期动态生成字节码。*CGLIB* 底层使用 *ASM*(一个 *Java* 字
节码操纵框架) 来操作字节码生成新的类。*CGLib* 比 *Java* 的 `java.lang.reflect.Proxy` 类更强的在于它不仅可以接管接口类的方法，还可以
接管普通类的方法。


## 项目结构
*CGLIB* 的项目结构如下
~~~
cglib
|- beans        # bean 操作类
|- core         # 核心代码
|- proxy        
|- reflect
|- transform
|- util
~~~


## 附录
CGLIB 的相关资料
* [源码](https://github.com/cglib/cglib)
* [JavaDoc 文档](http://cglib.sourceforge.net/apidocs/index.html)
* [ASM](http://tool.oschina.net/apidocs/apidoc?api=asm)




