
## Dorado 直连

为了方便业务进行服务测试和问题定位，Dorado 支持指定地址进行调用的场景。使用方式如下，设置直连地址即可

````
<bean id="clientProxy" class="com.meituan.dorado.config.service.spring.ReferenceBean" destroy-method="destroy">
  <property name="serviceInterface" value="com.meituan.dorado.test.thrift.api.Echo"/>
  <property name="directConnAddress" value="127.0.0.1:9001"/>   <!--  配置服务端的直连地址  -->
  <property name="appkey" value="com.meituan.octo.dorado.client"/>
  <property name="remoteAppkey" value="com.meituan.octo.dorado.server"/>
</bean>
````
