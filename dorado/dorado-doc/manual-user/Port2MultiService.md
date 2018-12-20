
## Dorado 单端口多服务

单端口多服务主要应用于的场景:

一个appkey下存在大量的服务，如果每个服务都各自占用一个端口，造成端口资源浪费

下面给出使用单端口多服务的xml文件配置，主要通过serviceConfigList参数配置多个serviceConfig，每个serviceConfig代表一个服务，同时可以针对每个服务配置相关的参数，例如线程池，超时设置等。

### 1.配置示例

````
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloServiceProcessor" class="com.meituan.dorado.test.thrift.api.HelloServiceImpl"></bean>
    <bean id="echoServiceProcessor" class="com.meituan.dorado.test.thrift.api.EchoImpl"></bean>

    <bean id="serverPublisher" class="com.meituan.dorado.config.service.spring.ServiceBean" destroy-method="destroy">
        <property name="appkey" value="com.meituan.octo.dorado.server"/>
        <property name="port" value="9001"/>
        <property name="registry" value="mock"/>
        <property name="serviceConfigList">
            <list>
                <ref bean="helloServiceConfig"/>
                <ref bean="echoServiceConfig"/>
            </list>
        </property>
    </bean>

    <bean id="helloServiceConfig" class="com.meituan.dorado.config.service.ServiceConfig">
        <property name="serviceInterface" value="com.meituan.dorado.test.thrift.api.HelloService"/>
        <property name="serviceImpl" ref="helloServiceProcessor"/>
        <property name="bizMaxWorkerThreadCount" value="256"/>
    </bean>

    <bean id="echoServiceConfig" class="com.meituan.dorado.config.service.ServiceConfig">
        <property name="serviceInterface" value="com.meituan.dorado.test.thrift.api.Echo"/>
        <property name="serviceImpl" ref="echoServiceProcessor"/>
        <property name="bizMaxWorkerThreadCount" value="256"/>
    </bean>
</beans>
````