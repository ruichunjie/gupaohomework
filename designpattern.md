<table>
   <tr>
      <td>设计模式</td>
      <td>类型</td>
      <td>归纳</td>
      <td>举例</td>
   </tr>
   <tr>
      <td>工厂模式</td>
      <td>创建型</td>
      <td>封装过程，只留取对象的接口</td>
      <td>BeanFactory</td>
   </tr>
   <tr>
      <td>单例模式</td>
      <td>创建型</td>
      <td>永远只有一个</td>
      <td>ApplicationContext</td>
   </tr>
   <tr>
      <td>原型模式</td>
      <td>创建型</td>
      <td>复制对象</td>
      <td>ArrayList</td>
   </tr>
    <tr>
       <td>代理模式</td>
       <td>结构型</td>
       <td>找中介</td>
       <td>JdkDynamicAopProxy, CglibAopProxy</td>
    </tr>
    <tr>
       <td>委派模式</td>
       <td>行为型</td>
       <td>领导安排工作</td>
       <td>DispatcherServlet</td>
    </tr>
    <tr>
        <td>策略模式</td>
        <td>行为型</td>
        <td>很多选择中选择一个</td>
        <td>ResourceLoader</td>
    </tr>
    <tr>
        <td>模板模式</td>
        <td>行为型</td>
        <td>我来定义模板，你来实现其中关键</td>
        <td>JdbcTemplate</td>
    </tr>
    <tr>
         <td>适配器模式</td>
         <td>结构型</td>
         <td>兼容</td>
         <td>HandlerAdapter</td>
    </tr>
    <tr>
        <td>装饰器模式</td>
        <td>结构型</td>
        <td>包装扩展</td>
        <td>IO流</td>
    </tr>
    <tr>
        <td>观察者模式</td>
        <td>行为型</td>
        <td>任务完成时通知</td>
        <td>Listener</td>
    </tr>
</table>

列举代码片段

IOC DI 

```java
    @Service
    @Transactional
    @Slf4j
    public class CmBudgetProTypeAreaService {
        @Autowired
        private CmBudgetProtypeAreaMapper cmBudgetProtypEareaMapper;
        @Autowired
        private CmBudgetCostMapper cmBudgetCostMapper;
```

AOP
```xml
 <aop:config proxy-target-class="true">  
       <aop:pointcut id="managers" expression="execution(* com.imooc.service..*.*(..))"/>
       <aop:advisor pointcut-ref="managers" advice-ref="txAdvice"/>
   </aop:config>
```