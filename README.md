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



















[1] : [https://qiniuyun.ningdali.com/blog/1971springboot.png]  

[2] : [https://qiniuyun.ningdali.com/blog/1971springboot1.png]  

[3] : [https://qiniuyun.ningdali.com/blog/1971springboot2.png]

[4] : [https://qiniuyun.ningdali.com/blog/1971springboot3.png]

[5] : [https://qiniuyun.ningdali.com/blog/1971springboot4.png]

[6] : [https://qiniuyun.ningdali.com/blog/1971springboot5.png]

[7] : [https://qiniuyun.ningdali.com/blog/1971springboot6.png]

[8] : [https://qiniuyun.ningdali.com/blog/1971springboot7.png]

[9] : [https://qiniuyun.ningdali.com/blog/1971springboot8.png]

