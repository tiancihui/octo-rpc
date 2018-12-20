
## Dorado 负载均衡

dorado提供多种负载均衡算法，业务可以通过SPI定义自己的负载均衡策略

| 算法 | 名称 | 说明 |
| ------ | ------ | ------ |
| RoundRobinLoadBalance | 带权的RoundRobin轮询算法 | 按照节点的权重依次选择每个节点 |
| RandomLoadBalance | 带权的随机轮询算法 | 按照节点的权重随机选择某个节点 |


### 1.配置方式

#### 1.1 xml文件方式

````
<bean id="clientProxy" class="com.meituan.dorado.config.service.spring.ReferenceBean" destroy-method="destroy">
  <!-- ......  -->
  <property name="loadBalancePolicy" value="random"/>
</bean>
````

#### 1.2 API方式

````
 ReferenceConfig<HelloService.Iface> config = new ReferenceConfig<>();
 // ......
 config.setLoadBalancePolicy("random");
````