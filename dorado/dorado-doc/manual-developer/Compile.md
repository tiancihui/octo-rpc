
## Dorado 源码编译说明

### 1.下载工程

先通过git下载工程到本地IDE    

```
git clone ${url} dorado
```

### 2.构建Jar包

Dorado 依赖maven作为构建工具

环境要求：

1.Java version >= 1.7    
2.Maven version >= 3.0    

编译命令如下所示，编译成功后生成jar包 dorado-${verion}.jar

````
cd dorado-build 
mvn clean install 
````

### 4.自定义Jar包

如果需要自定义生成jar包含的目录内容，可以修改dorado-build目录里的pom文件，更新include包含的模块同时增加该模块的依赖

````
<build>
  <plugins>
    <plugin>
      <artifactId>maven-shade-plugin</artifactId>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <createSourcesJar>true</createSourcesJar>
            <promoteTransitiveDependencies>false</promoteTransitiveDependencies>
            <createDependencyReducedPom>true</createDependencyReducedPom>
            <artifactSet>
              <includes>
                <include>com.meituan:dorado-common</include>
                <include>com.meituan:dorado-core</include>
                <include>com.meituan:dorado-protocol-octo</include>
                <include>com.meituan:dorado-core-default</include>
                <include>com.meituan:dorado-transport-netty</include>
                <include>com.meituan:dorado-transport-httpnetty</include>
                <include>com.meituan:dorado-registry-zookeeper</include>
                <include>com.meituan:dorado-registry-mns</include>
              </includes>
            </artifactSet>
            <transformers>
              <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
            </transformers>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
````