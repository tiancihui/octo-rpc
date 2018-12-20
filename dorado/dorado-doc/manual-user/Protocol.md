
## Dorado 通信协议

配置字段: ***protocol***, 指Body协议，默认thrift

### 1. OCTO协议 + Thrift协议
美团内部服务间使用OCTO私有协议进行通信，OCTO协议具备良好的扩展性，如下是协议格式：

| 1B | 1B | 1B | 1B | 4B | 2B | header length | totoal length - 2B - header length(4B) | 4B(可选) |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | 
| 0xAB | 0xBA | version | protocol | total length | header length | header | body | checksum |
| 协议头 | | | | | | 消息头 | 消息体 | 校验码 |

OCTO协议比较灵活的是消息头和消息体，消息头主要是透传一些服务参数信息（比如：TraceInfo），还可以扩展其他数据，为丰富服务治理功能带来了极大便利；消息体是编码后的请求数据，任何编解码协议都可以在Body中进行传输，目前Dorado默认以Thrift作为Body协议，使用者也可以扩展其他编解码协议进行扩展。

### 2. Thrift协议
 
 Dorado同时支持原生Thrift协议，且可以和OCTO协议混合使用，调用端在服务发现的时候会根据节点注册信息来判断使用什么协议；服务端则可以在解码时判断来源请求协议。
 所以Dorado可以和任何原生Thrift服务互通，无论是调用端还是服务端，不管你使用什么语言只要是Thrift协议都可以和Dorado通信。

---------------
###说明
* 若服务端支持OCTO协议，则发送OCTO协议
* 若使用直连访问，为了更好的兼容默认是发送Thrift协议，需要发送OCTO协议则配置***remoteOctoProtocol***为true
 


