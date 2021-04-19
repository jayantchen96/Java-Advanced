package com.demo.starter.pojo;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Data
public class School implements ISchool {
    
    @Autowired(required = true) //primary
    Klass class1;

    @Resource(name = "student1")
    Student student1;
    
    @Override
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student1);
    }
    
}
