1、思考：为什么JDK动态代理中要求目标类实现的接口数量不能超过65535个？

​		jvm. 限制 class类文件的结构 是8位字节，一个字节占8位，以字节为基础单位的二进制流

​		其中表示接口的是u2 对应2的16次方 所以最多能表示65535个不同的值