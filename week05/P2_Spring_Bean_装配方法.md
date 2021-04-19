### 2. (必做) 写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）

#### 以下三种方式

#### 1. 基于XML的Bean装配

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">

    <bean class="com.lyp.spring.example.User" id="user">
        <constructor-arg ref="myArticle"/>
    </bean>

    <bean class="com.lyp.spring.example.MyArticle" id="myArticle"></bean>

</beans>
```

---

#### 2. 隐式的Bean发现机制和自动装配

spring从两个角度实现自动化装配：组件扫描和自动装配
##### 组建扫描

当对一个类标注@Component注解时，表明该类会作为组件类，spring将为这个类创建bean。当在应用文中引用这个bean，spring会自动扫描事先指定的包查找这个 bean。但spring默认是不启用组件扫描的，可以在XML中配置加上<context:component-scan base-package="xx"/>。还有一种方法：在新建一个配置类，类中可以什么不用写，在配置类上加上@ComponentScan注解，spring会自动扫描改配置类所在的包。


```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

```java
@Component("fooFormatter")
    public class FooFormatter implements Formatter {
        public String format() {
            return "foo";
        }
    }
```

##### 自动装配

@Autowired 按类型装配 Spring Bean。
如果容器中有多个相同类型的 bean，则框架将抛出 NoUniqueBeanDefinitionException

通过将 @Qualifier 注解与我们想要使用的特定 Spring bean 的名称一起进行装配，Spring 框架就能从多个相同类型并满足装配要求的 bean 中找到我们想要的，避免让Spring脑裂。我们需要做的是@Component或者@Bean注解中声明的value属性以确定名称。

```java
@Component
public class FooService {
    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;
}
```

---

#### 3. 在Java中进行装配

同样我们可以再Spring的Java配置类中对SpringBean进行配置
使用 @Bean 注解将方法返回的实例对象添加到上下文中
在@Bean返回的实例对象中可以通过构造器注入传入相关依赖

```java
@Configuration
@ComponentScan("com.lyp")
public class WebConfig {

    @Bean
    public User user() {
        return new User(myArticle());
    }

    @Bean
    public MyArticle myArticle() {
        return new MyArticle();
    }

}
```

