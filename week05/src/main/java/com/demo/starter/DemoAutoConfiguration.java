package com.demo.starter;

import com.demo.starter.pojo.Klass;
import com.demo.starter.pojo.School;
import com.demo.starter.pojo.Student;
import com.demo.starter.prop.SpringBootPropertiesConfiguration;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.*;


@Configuration
@ComponentScan("com.demo.starter")
@EnableConfigurationProperties({SpringBootPropertiesConfiguration.class})
@RequiredArgsConstructor
public class DemoAutoConfiguration{

    private final SpringBootPropertiesConfiguration props;

    public ApplicationContext applicationContext;

    @Bean("student1")
    public Student student1() {
        Student student = new Student();
        Properties properties = props.getProps();
        student.setId(Integer.parseInt(properties.getProperty("student1.id")));
        student.setName(properties.getProperty("student1.name"));
        return student;
    }

    @Bean("student2")
    public Student student2() {
        Student student = new Student();
        Properties properties = props.getProps();
        student.setId(Integer.parseInt(properties.getProperty("student2.id")));
        student.setName(properties.getProperty("student2.name"));
        return student;
    }

    @Bean
    public Klass klass() {
        List<Student> students = new ArrayList<>();
        students.add(student1());
        students.add(student2());
        Klass klass = new Klass();
        klass.setStudents(students);
        return klass;
    }

}
