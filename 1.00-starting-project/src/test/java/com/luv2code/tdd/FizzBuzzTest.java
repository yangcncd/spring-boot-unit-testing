package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {

    // 3 Fizz
    // 5 Buzz
    // 3 and 5 FizzBuzz
    // otherwise print out the given number

    @Test
    @DisplayName("Divisible by three")
    @Order(1)
    void testForDivisibleByThree() {
        String expected = "Fizz";
        Assertions.assertEquals(expected,FizzBuzz.compute(3),"should return Fizz");
    }

    @Test
    @DisplayName("Divisible by five")
    @Order(2)
    void testForDivisibleByFive() {
        String expected = "Buzz";
        Assertions.assertEquals(expected,FizzBuzz.compute(5),"should return Buzz");
    }

    @Test
    @DisplayName("Divisible by three and five")
    @Order(3)
    void testForDivisibleByThreeAndFive() {
        String expected = "FizzBuzz";
        Assertions.assertEquals(expected,FizzBuzz.compute(15),"should return FizzBuzz");
    }

    @Test
    @DisplayName("Not Divisible by three or five")
    @Order(4)
    void testForNotDivisibleByThreeOrFive() {
        String expected = "11";
        Assertions.assertEquals(expected,FizzBuzz.compute(11),"should return 11");
    }


    //instead @Test Annotation
    @DisplayName("Testing with my data csv file")
    @Order(5)
    @ParameterizedTest(name = "Actual Value = {0}, Expected value = {1}") //0 and 1 index
    @CsvFileSource(resources = "/myCsvTestFile.csv")
    void testForMyCsvTestSource(int value, String expect) {
        Assertions.assertEquals(expect,FizzBuzz.compute(value),"should return 11");
    }

    @DisplayName("Testing with large data csv file")
    @Order(5)
    @ParameterizedTest(name = "Actual Value = {0}, Expected value = {1}") //0 and 1 index
    @CsvFileSource(resources = "/large-test-data.csv")
    void testForLargeCsvTestSource(int value, String expect) {
        Assertions.assertEquals(expect,FizzBuzz.compute(value),"should return 11");
    }


}
