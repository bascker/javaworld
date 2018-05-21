# README
## 一、简介
Restlet 是个轻量级 RESTful Web API 框架，支持所有的 REST 概念, 同时适用于客户端和服务端 Web 应用。

特点：
* 线程安全
* 可插拔：各种支持 JSON/RDF/Atom, POP3/SMTP/FTP 协议
* 多版本，针对各种环境
    * J2SE/EE: 支持 Java 应用编译和运行的运行时类库组成, J2EE 添加了一些 Servlet & JavaMail & JPA & EJB 的 API 
    * GAE: Google App Engine, 针对 Google 云计算环境
    * GWT: Google Web Tookit，一个开源技术，允许以无插件的方式为 web 浏览器开发富客户端应用
    * Android: 针对 Android 系统
    
### 1.1 包结构
~~~
org.restlet             # 根包, 包含最重要的组成, 如体现了 REST 资源统一接口原则与通信无状态特性的 Uniform，核心类 Restlet 等
|- engine               # restlet 引擎
|- ext                  # 第三方扩展，如 Jackson/Spring 支持
|- data                 # 包含用于组合/检查消息 Message 的类, 有些类可方便用于 URI 查询参数及 web 表单管理
|- representation       # 客户端操作资源是通过它们状态的 representations 
|- resource             # 用于创建 Client/ServerResource 的实现，并包含 @Get/Post等常用注解
|- routing              # 与 routing 相关, 如 Filter(请求预处理)/Redirector(重定向)/Router(路由解析)
|- security             # 安全包，如认证与授权
|- service              # 一些方便的服务
|- util                 # 工具类
~~~
    

## 二、REST
REST 是 Representation State Transfer 的缩写, 其概念包括 Resource, Representation, Connector, Component, etc.

* Resource: 通过 internet 暴露出来的任意事物，是 Web 的基石。可通过 Representation 暴露其状态。
* Component：封装了资源的状态、行为等，对 resource 进行高效管理，将 resource 暴露出来。Component 类型多种，可以是用户代理（最常见的
是 web 浏览器）、原始服务器（如 IIS，Tomcat）、网关。
* Connector：让 Component 之间进行通信。类型多种，如 client/server connector(Apache HTTP Client库: 发送请求, Jetty HttP Server
库：接受请求并返回响应), cache, tunnel.

### REST VS. HTTP
区别：本质不同，REST 是一个软件架构的抽象，而 HTTP 是一个具体的通信协议。  
联系：HTTP 体现了 REST 的原则，使用 HTTP 创建 REST 应用十分方便。

> Note: REST 并不依赖 HTTP，HTTP 只是实现 REST 的一种方法，还可以使用 POP3/HTTPS 等来实现。