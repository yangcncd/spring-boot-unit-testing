package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
class DemoUtilsTest {
    DemoUtils demoUtils;
    @BeforeAll
    static void displayBeforeAllMark() {
        System.out.println("Running BeforeAll, tests will start");
    }

    @BeforeEach
    void setUpDemoUtilsObj() {
        System.out.println("Running BeforeEach ");
        this.demoUtils = new DemoUtils();
    }

    @Test
    @Order(-1)
    @DisplayName("Assert Timeout preemptively")
    @Disabled("Won't run because it take so long")
    void testTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            demoUtils.checkTimeout();
        }, "Method should be executed in 3 Seconds");
    }

    @Test
    @Order(1)
    @DisplayName("Testing throw Exception")
    @EnabledOnOs({OS.WINDOWS, OS.LINUX})
    void testThrowExpectedException() {
        assertThrows(Exception.class, () -> {
            demoUtils.throwException(-9);
        }, "throw negative number exception");
        assertDoesNotThrow(() -> {
            demoUtils.throwException(9);
        }, "Won't throw exception");
    }

    @Test
    @Order(2)
    @DisplayName("Assert Array Equation")
    @EnabledOnJre(JRE.JAVA_17) // only test this method for java 17
    void testEquationOfStringArrays() {
        String[] arr1 = {"A", "B", "C"};
        String[] arr2 = {new String("A"), new String("B"), new String("C")};
        assertArrayEquals(arr1, demoUtils.getFirstThreeLettersOfAlphabet(), "both arrays should be equal");
        assertArrayEquals(arr2, demoUtils.getFirstThreeLettersOfAlphabet(), "these two arrays also equal");
    }

    @Test
    @Order(3)
    @DisplayName("Assert Iterable Equation")
    @EnabledForJreRange(min = JRE.JAVA_13)//, max = JRE.JAVA_18
    void testEquationOfIterables() {
        List<String> list1 = List.of("luv", "2", "code");
        List<String> list2 = List.of(new String("luv"), new String("2"), new String("code"));

        assertIterableEquals(list1, demoUtils.getAcademyInList(), "list1 and academy list are equal");
        assertIterableEquals(list2, demoUtils.getAcademyInList(), "list2 and academy list are equal");
    }

    @Test
    @DisplayName("Assert Lines Match")
    void testListOfStringsMatch() {
        List<String> list1 = List.of("luv", "2", "code");
        List<String> list2 = List.of(new String("luv"), new String("2"), new String("code"));

        assertLinesMatch(demoUtils.getAcademyInList(), list1, "academy and list1 are match");
        assertLinesMatch(demoUtils.getAcademyInList(), list2, "academy and list2 are match");
    }

    @Test
    @DisplayName("Same and Not Same")
    void testSameAndNotSame() {
        String testStr = new String("Luv2Code Academy");
        // String testStr = "Luv2Code Academy" assertNotSame wont pass!!!

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "the both objects references same object");
        assertNotSame(testStr, demoUtils.getAcademy(), "These Tow Strings are not same");
    }

    @Test
    @DisplayName("Test True and False")
    void testTrueAndFalseWithNumbers() {
        //assertTrue(demoUtils.isGreater(2, 2)); just for failue test
        assertTrue(demoUtils.isGreater(5, 2));
        assertFalse(demoUtils.isGreater(2, 5));
    }

    @Test
    void testEqualsAndNotEquals() {
        assertEquals(5, demoUtils.add(2, 3), "2 plus 3 should be 5");
        assertNotEquals(8, demoUtils.add(2, 3), "2 plus 3 should not be 8");
    }

    @Test
    @DisplayName("Check String Null And Not Null")
    void testIsNullAndNotNUll() {
        String mystring = null;
        assertNull(demoUtils.checkNull(mystring), "should be null");
        assertNotNull(demoUtils.checkNull("Hello"), "should be not null");
    }

    @AfterEach
    void releaseDemoUtilsObj() {
        System.out.println("Running AfterEach");
        this.demoUtils = null;
    }

    @AfterAll
    static void displayAfterAllMark() {
        System.out.println("All tests finished");
    }
}
