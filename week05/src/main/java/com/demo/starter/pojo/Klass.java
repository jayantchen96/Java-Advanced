package com.demo.starter.pojo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


@Data
public class Klass {
    
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
