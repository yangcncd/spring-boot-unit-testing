package com.luv2code.tdd;

public class FizzBuzz {

    public static String compute(int num) {
//        if (num % 3 == 0 && num % 5 == 0) {
//            return "FizzBuzz";
//        } else if (num % 3 == 0) {
//            return "Fizz";
//        } else if (num % 5 == 0) {
//            return "Buzz";
//        } else {
//            //return String.valueOf(num);
//            return Integer.toString(num);
//        }

        // After refactoring:
        StringBuilder result = new StringBuilder();
        if(num % 3 == 0){
            result.append("Fizz");
        }
        if(num % 5 == 0){
            result.append("Buzz");
        }
        if(result.isEmpty()){
            result.append(num);
        }
        return result.toString();
    }
}
