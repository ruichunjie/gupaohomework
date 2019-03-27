###  IOC
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IOC 创建对象的控制权反转, 一般说法是定位，加载， 注册三部曲。Spring 依据xml, yml... 文件确定需要被创建对象的位置，创建对象，并注册到IOC容器中。在此过程中，Spring并未对Bean进行特殊处理，Bean的数目，作用域取决于它自身。 
### DI
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;依赖注入/依赖查找，Spring初始化利用对象的类型,名称和IOC容器中的对象进行匹配和赋值。
### MVC
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Spring 用HandlerMapper的集合对象保存url，method，参数等的映射信息，利用反射调用方法