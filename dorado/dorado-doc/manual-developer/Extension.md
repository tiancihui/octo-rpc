
## Dorado 扩展点

Dorado通过SPI机制提供多项扩展功能，开发者可以通过自定义实现接口，同时在resources的**META-INF.services**目录下对应的接口文件中中添加
具体的实现类；Dorado的所有的SPI接口通过@SPI注解来识别。

Dorado目前提供的扩展点如下表所示


| SPI接口 | 含义 | 默认实现 |
| --- | --- | --- |
| com.meituan.dorado.cluster.Cluster | 请求容错策略 | FailoverCluster、FailbackCluster、FailOverCluster |
| com.meituan.dorado.cluster.LoadBalance | 负载均衡策略 | RandomLoadBalance、RoundRobinLoadBalance |
| com.meituan.dorado.cluster.Router | 路由策略 | NoneRouter |
| com.meituan.dorado.codec.Codec | 编解码方式 | OctoCodec |
| com.meituan.dorado.rpc.handler.filter.Filter | 过滤器实现 | DoradoInvokerTraceFilter、DoradoProviderTraceFilter |
| com.meituan.dorado.rpc.handler.InvokeHandler | 请求处理类 | DoradoInvokerInvokeHandler、DoradoProviderInvokeHandler |
| com.meituan.dorado.trace.InvokeTrace | 数据埋点 | CatInvokeTrace(集成开源组件Cat) |
| com.meituan.dorado.rpc.proxy.ProxyFactory | 代理类 | JdkProxyFactory |
| com.meituan.dorado.registry.RegistryFactory | 注册中心 | MnsRegistryFactory(集成开源组件MNS)、ZookeeperRegistryFactory、MockRegistryFactory |
| com.meituan.dorado.transport.ClientFactory | 调用端 | NettyClientFactory |
| com.meituan.dorado.transport.ServerFactory | 服务端 | NettyServerFactory |
| com.meituan.dorado.transport.http.HttpServerFactory | Http服务 | NettyHttpServerFactory |
| com.meituan.dorado.check.http.HttpCheckHandler | 服务自检 | DoradoHttpCheckHandler |
| com.meituan.dorado.rpc.handler.HandlerFactory | 获取InvokeHandler | DoradoHandlerFactory |
| com.meituan.dorado.rpc.handler.http.HttpInvokeHandler | http接口测试 | DoradoHttpInvokeHandler |
| com.meituan.dorado.rpc.handler.HeartBeatInvokeHandler | 心跳处理 | ScannerHeartBeatInvokeHandler （OCTO-Scanner心跳处理）|
| com.meituan.dorado.codec.Codec | 编解码 | OctoCodec |
| com.meituan.dorado.transport.LengthDecoder | 协议长度解码 | DoradoLengthDecoder |
     
                                                                          

