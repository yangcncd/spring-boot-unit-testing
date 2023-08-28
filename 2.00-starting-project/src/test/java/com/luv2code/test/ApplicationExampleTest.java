package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {

    private static int counter = 0;

    @Value("${info.school.name}")
    private String schoolName;

    @Value("${info.app.name}")
    private String appName;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEachRun() {
        counter++;
        System.out.println("App info is:" + appName + "\n"
                + "App description is:" + appDescription + "\n"
                + "App version is:" + appVersion + "\n"
                + "School name is:" + schoolName + "\n"
                + "Testing runs " + counter + " times");

        student.setFirstname("Mike");
        student.setLastname("Commic");
        student.setEmailAddress("mike.cc@ggki.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(99.33, 49.55, 100.00, 56.88)));
        student.setStudentGrades(studentGrades);
        System.out.println(student.toString());
    }

    @Test
    @DisplayName("Add grades to student grades")
    public void addGradeResultsForStudentGrades() {
        Assertions.assertEquals(305.76, studentGrades.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults()
        ));
    }

    @Test
    @DisplayName("Add grades to student grades not equal testing")
    public void addGradeResultsForStudentGradesNotEqual() {
        Assertions.assertNotEquals(30.76, studentGrades.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults()
        ));
    }

    @Test
    @DisplayName("Create new student without grades")
    public void createStudentWithoutGradeInit() {
        // we use autowired application context here to get new student
        CollegeStudent stud = context.getBean("collegeStudent", CollegeStudent.class);
        stud.setFirstname("Bobi");
        stud.setLastname("Münchenberger");
        stud.setEmailAddress("bobi.mm@ski.com");
        Assertions.assertNotNull(stud.getEmailAddress());
        Assertions.assertNull(stud.getStudentGrades());
    }

    @Test
    @DisplayName("Test stud is prototype") // scope prototype means every time Bean is new created Instance
    public void verifyStudPrototype() {
        // we use autowired application context here to get new student
        CollegeStudent stud = context.getBean("collegeStudent", CollegeStudent.class);
        Assertions.assertNotEquals(this.student, stud);
    }

    @Test
    @DisplayName("Find grade point average") // scope prototype means every time Bean is new created Instance
    public void findGradePointAverage() {
        Assertions.assertAll("Testing all assert Equals",
                () -> Assertions.assertEquals(305.76, studentGrades.addGradeResultsForSingleClass(
                        studentGrades.getMathGradeResults()
                )),
                () -> Assertions.assertEquals(76.44, studentGrades.findGradePointAverage(studentGrades.getMathGradeResults()
                ))
        );
    }
}
