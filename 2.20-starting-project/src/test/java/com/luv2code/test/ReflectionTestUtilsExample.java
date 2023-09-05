package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsExample {
    @Autowired
    CollegeStudent student1;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @MockBean
    ApplicationDao applicationDao;

    @Autowired
    ApplicationService applicationService;

    @BeforeEach
    public void beforeEach() {
        student1.setFirstname("Eric");
        student1.setLastname("Roby");
        student1.setEmailAddress("eric.roby@luv2code_school.com");

        // Use ReflectionTestUtils to set private fields
        ReflectionTestUtils.setField(student1, "id", 1);
        ReflectionTestUtils.setField(student1, "studentGrades", new StudentGrades(
                new ArrayList<>(Arrays.asList(100.00, 85.00, 76.50, 91.75))
        ));
    }

    @Test
    public void getPrivateIdField(){
        Assertions.assertEquals(1, ReflectionTestUtils.getField(student1,"id"));
    }

    //test private method
    @Test
    public void testFirstNameAndId(){
        Assertions.assertEquals("Eric 1", ReflectionTestUtils.invokeMethod(
                student1,"getFirstNameAndId"
        ));
    }
}
