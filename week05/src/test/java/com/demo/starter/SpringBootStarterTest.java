package com.demo.starter;



import com.demo.starter.pojo.Klass;
import com.demo.starter.pojo.School;
import com.demo.starter.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStarterTest.class)
@SpringBootApplication
public class SpringBootStarterTest {

    @Autowired
    @Qualifier("student1")
    private Student student1;

    @Autowired
    @Qualifier("student2")
    private Student student2;

    @Autowired
    private Klass klass;

    @Autowired
    private School school;

    @Test
    public void assertStudent() {
        System.out.println(student1.getName());
        System.out.println(student2.getName());

        klass.dong();
        System.out.println(klass.getStudents());
        System.out.println(student1 == klass.getStudents().get(0));

        school.ding();
//        assertThat(dataSource.getDataSourceMap().size(), is(2));
//        assertTrue(dataSource.getDataSourceMap().containsKey("ds_0"));
//        assertTrue(dataSource.getDataSourceMap().containsKey("ds_1"));
    }

}
