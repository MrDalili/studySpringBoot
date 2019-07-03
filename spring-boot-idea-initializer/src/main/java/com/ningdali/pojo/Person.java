package com.ningdali.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties(prefix = "person")
 * 告诉SpringBoot将本类中的所有属性和配置文件中相关的属性进行绑定
 * prefix = "person" 是将说明将哪一个配置文件和该类的属性进行配置
 *
 * 只有这个组建是容器中的组件，才能提供这个注解的功能@ConfigurationProerties
 * */
@Component
@ConfigurationProperties(prefix = "person")
//指定要配置的配置文件路径
@PropertySource(value = {"classpath:person.properties"})
    public class Person {
    private String lastName;
    private int age;
    private boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<String> lists;

    private Dog dog;

    public Person() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
