# 一、Spring Boot 入门

## 1、Spring Boot 简介

> 简化Spring应用开发的一个框架
>
> 整个Spring技术栈的一个大整合
>
> J2EE开发的一站式解决方案

## 2、 微服务

2014，martin fowler  

微服务：架构风格（服务微化）

一个应用应该是一组小型服务；可以通过HTTP的方式进行互通

单体应用：ALL IN ONE，简单点说就是所有的东西全部放在一个war包当中发布到tomcat里面运行

微服务：每一个功能元素最终是一个可独立替换和独立升级的软件单元

## 3、环境准备

### 环境约束

-jdk1.8：java version "1.8.0_211"

-maven：Apache Maven 3.6.0

-IntelliJIDEA2018

-SpringBoot 1.5.9.RELEASE：1.5.9

### Maven设置

给Maven的settings.xml文件中的profiles标签添加

```xml
<profile>
      <id>jdk-1.8</id>
      <activation>
		<activeByDefault>true</activeByDefault>
		<jdk>1.8</jdk>
      </activation>

      <properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
      </properties>
    </profile>
```

### IDEA设置

**打开设置**  

![打开设置][1]   

**配置配置文件以及仓库**

![配置][2]  

## 4、编写一个HelloWorld的SpringBoot简单应用程序

### 功能  

请求hello，返回一个hello world的html界面

### 步骤

#### 1、常见一个jar的maven工程

#### 2、导入Spring Boot的相关依赖

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

#### 3、编写主类

```java
/**
 * 用来标注这个是一个springboot程序
 */
@SpringBootApplication
public class HelloWorld {
    public static void main(String[] args) {
        //以HelloWorld为主类，args当参数启动
        SpringApplication.run(HelloWorld.class,args);
    }
}
```

#### 4、编写controller层

```java
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloSpring(){
        return "Hello world!";
    }
}
```

#### 5、测试

访问  

> localhost:8080/hello

待出现此页面证明一切ok，你已经学会了SpringBoot。  

哈哈哈哈哈哈哈  

![测试][3]  

#### 6、打包部署

现在Maven配置文件中添加一个插件  

```xml
    <!-- 这个插件，可以将应用打包成一个可执行的jar包 -->  
     <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

点击IDEA右侧的maven中的package打包，在左侧目录中会出现一个target目录，在IDEA中下方的Terminal中进入目录，使用**java -jar**命令，观察到下图的情况即算运行成功。

![运行及结果图][4]  

当然你还要去  

> localhost:8080/hello 

看看你是否可以请求到你的路

## 5、Hello World探究

### pom文件

#### 父项目

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
</parent>
```

这个父项目的父maven项目又是

```xml
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-dependencies</artifactId>
		<version>1.5.9.RELEASE</version>
		<relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

**spring-boot-dependencies**是Spring Boot的版本仲裁中心  

我们在创建Spring Boot时导入的默认依赖是不用写版本的，没有在这里面的需要写版本号

#### 启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**spring-boot-starter-web：**  

spring-boot-starter时Spring Boot中的启动器，它帮我们导入了web模块正常运行的所有依赖组建  

Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），只需要在项目里面引入这些starters，相关场景的所有依赖都会导入进来，要什么功能就导入什么场景的启动器  

### 主程序类，主入口

```java
/**
 * 用来标注这个是一个springboot程序
 */
@SpringBootApplication
public class HelloWorld {
    public static void main(String[] args) {
        //以HelloWorld为主类，args当参数启动
        SpringApplication.run(HelloWorld.class,args);
    }
}

```

**@SpringBootApplication：**Spring Boot应用标注在某个类上，说明这个类是SpringBoot的主配置类，SpringBoot就应该运行这个类的main方法来启动SpeingBoot应用  

打开该注释，注释中所存在的有以下的：  

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {

```

- **@SpringBootConfiguration**：Spring Boot的配置类：一般标注在某个类上面，表示这个是一个Spring Boot的配置类  

  在SpringBootConfiguration中还有**@Configuration**注解  

  一般来说，配置类就是用来代替配置文件的  

- **@EnableAutoConfiguration：**开启自动配置功能

  以前我们需要配置的东西，SpringBoot自动帮我们配置它告诉SpringBoot开启自动配置功能，这样子自动配置才能生效  

  ```java
  @AutoConfigurationPackage
  @Import({EnableAutoConfigurationImportSelector.class})
  public @interface EnableAutoConfiguration {
  
  ```

  **@AutoConfigurationPackage：**自动配置包

  **@Import({EnableAutoConfigurationImportSelector.class})：**Spring的底层注解@Import，给容器中导入一个组件  

  **EnableAutoConfigurationImportSelector：**导入哪些组建的选择器  

  将所有需要导入的组建以全类名的方式返回，这些组件就会被添加到容器中  ，一般会给每个场景配置相应的组建，有了自动配置类，就免去了我们手动编写配置注入功能等工作。  

  Spring Boot 再启动的时候会从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的指，将这些值作为自动配置类导入容器中，自动配置类就会生效，帮助我们进行自动配置工作  

  J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar中 

## 6、使用Spring Initializer快速创建Spring Boot项目

### 在IDEA中使用Spring Initializer快速创建项目

![打开创建项目栏][5]  

![填写项目相关信息][6]  

![打开创建项目栏][7]  

点击Finish，就可以创建一个出一个新的Spring Boot项目，打开右侧的目录，目录结构如下：  

![右侧文件结构][8]    

这时候默认生成的Spring Boot项目

- 它的主程序已经生成好了，我们只需要我们自己的逻辑即可  
- resources文件夹中目录结构  
  - static：保存所有的静态资源，例如：js、css、images
  - templates：保存所有的模板页面，例如：freemarket、thymeleaf等
  - 配置文件：用于Spring Boot应用的配置文件，可以修改一些默认值

# 二、配置文件

## 1、配置文件

SpringBoot使用一个全局的配置文件，配置文件名是固定的；

+ application.properties

+ application.yml



配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；



### YAML（YAML Ain't Markup Language）

	YAML  A Markup Language：是一个标记语言
	
	YAML   isn't Markup Language：不是一个标记语言；

标记语言：

	以前的配置文件；大多都使用的是  **xxxx.xml**文件；
	
	YAML：**以数据为中心**，比json、xml等更适合做配置文件；
	
	YAML：配置例子

```yaml
server:
  port: 8081
```

	XML：

```xml
<server>
	<port>8081</port>
</server>
```



## 2、YAML语法：

### 基本语法

k:(空格)v：表示一对键值对（空格必须有）；

以**空格**的缩进来控制层级关系；只要是左对齐的一列数据，都是同一个层级的

```yaml
server:
    port: 8081
    path: /hello
```

属性和值也是大小写敏感；



### 值的写法

#### 字面量：普通的值（数字，字符串，布尔）

	k: v：字面直接来写；
	
		字符串默认不用加上单引号或者双引号；
	
		""：双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思
	
				name:   "zhangsan \n lisi"：输出；zhangsan 换行  lisi
	
		''：单引号；会转义特殊字符，特殊字符最终只是一个普通的字符串数据
	
				name:   ‘zhangsan \n lisi’：输出；zhangsan \n  lisi



#### 对象、Map（属性和值）（键值对）：

	k: v：在下一行来写对象的属性和值的关系；注意缩进
	
		对象还是k: v的方式

```yaml
friends:
		lastName: zhangsan
		age: 20
```

行内写法：

```yaml
friends: {lastName: zhangsan,age: 18}
```



#### 数组（List、Set）：

用- 值表示数组中的一个元素

```yaml
pets:
 - cat
 - dog
 - pig
```

行内写法

```yaml
pets: [cat,dog,pig]
```



## 3、配置文件值注入

#### 使用yaml注入

**配置文件**

```yaml
person:
    lastName: hello
    age: 18
    boss: false
    birth: 2017/12/12
    maps: {k1: v1,k2: 12}
    lists:
      - lisi
      - zhaoliu
    dog:
      name: 小狗
      age: 12
```

**javaBean**

```java
/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
 *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 *
 * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
 *
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

```

我们可以导入配置文件处理器，以后编写配置就有提示了

```xml
<!--导入配置文件处理器，配置文件进行绑定就会有提示-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
```

#### 使用Properties注入

**配置文件**

```properties
person.lastName=zhangsan 
```

**JavaBean**

```java
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private int age;
    private boolean boss;
    private Date birth;
}
```

#### YAML与Properties在使用时，@Value与@ConfigurationProperties的比较

|                      | @ConfigurationProperties | @Value         |
| -------------------- | ------------------------ | -------------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定注入 |
| 松散绑定（松散语法） | 支持                     | 不支持         |
| SpEL                 | 不支持                   | 支持           |
| JSP303数据校验       | 支持                     | 不支持         |
| 复杂类型封装         | 支持                     | 不支持         |

**PS：**

+ 松散绑定：就是在变量解析的过程中驼峰命名法、下划线、短横线等价。

  ```java
  Person{
      private String firstName;
  }
  //标准方式
  person.firstName
  //驼峰
  person.firstName
  //下划线
  person.first_name
  //短横线
  person.first-name
  ```

+ SpEL语言：就是可以使用#{}与${}的那种语言（你懂的~），${key}从环境变量中取值，#{key}从配置文件中取值

+ JSP303：就是你在给类中的属性传数据的时候，SpringBoot会对你的数据内容进行校验，如果符合规范就注入，不符合规范就报错会抛出异常

配置文件不管是yml还是properties他们都能获取到值：  

如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value  

如果说，我们专门编写了一个JavaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties  

#### 配置文件注入值数据校验

```java
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    //必须是邮箱格式
    @Email
    private String lastName;
    private int age;
    private boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<String> lists;

    private Dog dog;
```

如上，校验必须为email格式，如果不是，控制台会报错

#### PropertySource&@ImportResource&@Bean

**@PropertySource**:  加载指定的配置文件

```java
@Component
@ConfigurationProperties(prefix = "person")
//指定要配置的配置文件路径即可
@PropertySource(value = {"classpath:person.properties"})
    public class Person {
    private String lastName;
    private int age;
    private boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<String> lists;

    private Dog dog;
}
```

**@ImportResource:**导入Spring的配置文件，让配置文件里面的内容生效  

Spring Boot中没有Spring的xml配置文件，如果我们要自己编写配置文件，也不能被自动识别，先让Spring的配置文件生效，加载进来，需要将@**ImportResource**标注在一个配置类上面  

```java
@ImportResource(value = {"classpath:springApplication.xml"})
```

在SpringBoot中，推荐给容器添加组件的方式为全注解方式  

1. 配置类@**Configuration**配置Spring配置文件
2. 使用@Bean给容器中添加组件

```java
**
 * 指明当前类为一个配置类，就是用来代替之前的spring配置文件的
 */
@Configuration
public class config {
    //将方法的返回值添加到容器里面，容器中的默认id就是方法名
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
```

## 4、配置文件占位符

### 随机数

```yaml
${random.value}、${random.int}、${random.long}
${random.int(10)}、${random.int[1024,65536]}
```

### 占位符获取之前配置值，如果没有可以使用：指定默认值

```properties
person.last-name=张三${random.uuid}
person.age=${random.int}
person.birth=2017/12/15
person.boss=false
person.maps.k1=v1
person.maps.k2=14
person.lists=a,b,c
person.dog.name=${person.hello:hello}_dog
person.dog.age=15
```

## 5、Profile

### 多Profile文件

我们在主配置文件编写的时候，文件名可以是application-{profile}.properties/yml  

默认使用application.properties的配置  

### yml支持多文档块的方式

```yml
server:
  port: 8081
spring:
  profiles:
    active: prod

---
server:
  port: 8083
spring:
  profiles: dev


---

server:
  port: 8084
spring:
  profiles: prod  #指定属于哪个环境
```

### 激活指定profile

1. 在配置文件中指定 spring.profiles.active=dev

2. 命令行：  

   java -jar spring-boot.jar --spring.profiles.active=dev

3. 虚拟机参数

   -Dspring.profiles.active=dev

## 6、配置文件加载的地方

Spring Boot启动会扫描以下位置的application.properties或者application.yml文件作为Spring Boot的默认配置文件  

> -file: ./config/  
>
> -file: ./  
>
> -classpath: /config/
>
> -classpath: /

优先级由高到低，高优先级的配置会覆盖低优先级的配置  

SpringBoot会从这四个位置全部加载主配置文件，**互补配置**  

我们还可以通过Spring.config.location来改变默认的配置文件位置  

**项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来制定配置文件的新位置，制定配置文件和默认加载的这些配置文件共同起作用形成互补配置**  

> java -jar spring-boot.jar --spring.config.location=C://admin/application.propertise  

## 7、外部配置加载顺序

SpringBoot也可以从以下位置加载配置，优先级从高到低，高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置  

+ 命令行参数

  所有配置都可以在命令行上进行指定  

  > java -jar spring-boot.jar --server.port=8087 --server.context-path=/abc 
  >

   多个配置用空格分开； --配置项=值

+ 来自java:comp/env的JNDI属性

+ Java系统属性(System.getProperties())

+ 操作系统环境变量

+ RandomValuePropertySource配置的random.*属性值  

  **由jar包外向jar包内进行寻找**

  **优先加载带profile**

+ jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件

+ jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件

  **再加载不带profile**

+ jar包外部的application-{profile}.properties或application.yml(不带spring.profile)配置文件

+ jar包内部的application-{profile}.properties或application.yml(不带spring.profile)配置文件

+ @Configuration注解类上的@PropertySource
+ 通过SpringApplication.setDefaultProperties指定的默认属性

## 8、自动配置原理

1. SpringBoot启动会加载大量的自动配置类
2. 我们看我们需要的功能有没有SpringBoot默认写好的自动配置类
3. 我们再来看这个自动配置类中到底配置了那些组件
4. 给容器中自动配置类添加组件的时候，会从properties类中获取某些属性，我们就可以再配置文件中指定这些属性的值

**@Conditional派生注解**  

作用：必须是@Conditional指定的条件成立，才给容器中添加组件，配置配里面的所有内容才生效

| @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                 |
| ------------------------------- | ------------------------------------------------ |
| @ConditionalOnJava              | 系统的java版本是否符合要求                       |
| @ConditionalOnBean              | 容器中存在指定Bean；                             |
| @ConditionalOnMissingBean       | 容器中不存在指定Bean；                           |
| @ConditionalOnExpression        | 满足SpEL表达式指定                               |
| @ConditionalOnClass             | 系统中有指定的类                                 |
| @ConditionalOnMissingClass      | 系统中没有指定的类                               |
| @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
| @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
| @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
| @ConditionalOnWebApplication    | 当前是web环境                                    |
| @ConditionalOnNotWebApplication | 当前不是web环境                                  |
| @ConditionalOnJndi              | JNDI存在指定项                                   |

自动配置类必须在一定的条件下才能生效  

我们可以通过启用  

>  debug=true

属性；来让控制台打印自动配置报告，这样我们可以很方便的指导哪些自动配置类生效。







[1] : [https://qiniuyun.ningdali.com/blog/1971springboot.png]  

[2] : [https://qiniuyun.ningdali.com/blog/1971springboot1.png]  

[3] : [https://qiniuyun.ningdali.com/blog/1971springboot2.png]

[4] : [https://qiniuyun.ningdali.com/blog/1971springboot3.png]

[5] : [https://qiniuyun.ningdali.com/blog/1971springboot4.png]

[6] : [https://qiniuyun.ningdali.com/blog/1971springboot5.png]

[7] : [https://qiniuyun.ningdali.com/blog/1971springboot6.png]

[8] : [https://qiniuyun.ningdali.com/blog/1971springboot7.png]

[9] : [https://qiniuyun.ningdali.com/blog/1971springboot8.png]

