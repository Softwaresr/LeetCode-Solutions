import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

class Solution {
    
    private HashMap<String, HashSet<Integer>> cache;
    
    public int scoreOfStudents(String s, int[] answers) {
        cache = new HashMap<>();
        HashSet<Integer> possibleAnswers = getPossibleAnswers(s);
        int correctAnswer = getCorrectAnswer(s);
        
        int totalScore = 0;
        for (int answer : answers) {
            if (answer == correctAnswer) {
                totalScore += 5; // Correct answer
            } else if (possibleAnswers.contains(answer)) {
                totalScore += 2; // Incorrect order but correct arithmetic
            }
        }
        
        return totalScore;        
    }
    
    private HashSet<Integer> getPossibleAnswers(String s) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        
        HashSet<Integer> possibleAnswers = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '+' || currentChar == '*') {
                HashSet<Integer> leftAnswers = getPossibleAnswers(s.substring(0, i));
                HashSet<Integer> rightAnswers = getPossibleAnswers(s.substring(i + 1));
                
                for (Integer left : leftAnswers) {
                    for (Integer right : rightAnswers) {
                        int result = (currentChar == '+') ? (left + right) : (left * right);
                        if (result <= 1000) { // Only consider results within the valid range
                            possibleAnswers.add(result);
                        }
                    }
                }
            }
        }
        
        // If there are no operators, the expression is a single number
        if (possibleAnswers.isEmpty() && s.length() == 1) {
            possibleAnswers.add(Integer.parseInt(s));
        }

        cache.put(s, possibleAnswers);
        return possibleAnswers;        
    }
    
    private int getCorrectAnswer(String s) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                stack.push(Character.getNumericValue(currentChar));
            } else if (currentChar == '*') {
                int left = stack.pop();
                int right = Character.getNumericValue(s.charAt(i + 1));
                stack.push(left * right);
                i++; // Skip the next character since it's already processed
            }
        }
        
        // Now sum all elements in the stack to get the result for '+' operators
        int totalSum = 0;
        while (!stack.isEmpty()) {
            totalSum += stack.pop();
        }
        
        return totalSum;
    }
}


/* 

Problem Statement

The code is designed to solve a problem where a string s represents a math expression with single-digit numbers, addition symbols +, and multiplication symbols *. The task is to grade the answers submitted by n elementary school students based on the correct answer of the expression and the rules of grading.

Solution Overview

The solution uses a recursive approach with memoization to calculate the possible answers for the given expression. It also uses a stack to calculate the correct answer of the expression.

Key Components

getPossibleAnswers method: This method takes a string s as input and returns a set of possible answers that can be obtained by applying the operators in different orders. It uses memoization to store the results of sub-problems to avoid redundant calculations.
getCorrectAnswer method: This method takes a string s as input and returns the correct answer of the expression by following the order of operations (multiplication first, then addition).
scoreOfStudents method: This method takes a string s and an array of answers as input and returns the sum of the points of the students based on the grading rules.
getPossibleAnswers Method

This method uses a recursive approach to calculate the possible answers for the given expression. Here's a step-by-step explanation:

If the input string s is already in the cache, return the cached result.
Initialize an empty set possibleAnswers to store the possible answers.
Iterate through the input string s. For each character currentChar:
If currentChar is an operator (+ or *):
Recursively call getPossibleAnswers on the left and right substrings of s separated by currentChar.
For each pair of possible answers from the left and right substrings, calculate the result of applying the operator (+ or *) and add it to possibleAnswers if the result is within the valid range (0-1000).
If the input string s is a single digit, add it to possibleAnswers.
Store the result in the cache and return possibleAnswers.
getCorrectAnswer Method

This method uses a stack to calculate the correct answer of the expression. Here's a step-by-step explanation:

Initialize an empty stack stack.
Iterate through the input string s. For each character currentChar:
If currentChar is a digit, push it onto the stack.
If currentChar is an operator (*):
Pop the top element from the stack (left operand).
Multiply the left operand with the next digit in the input string (right operand).
Push the result onto the stack.
Skip the next character in the input string.
After iterating through the entire input string, sum all elements in the stack to get the result for + operators.
Return the final result.
scoreOfStudents Method

This method takes a string s and an array of answers as input and returns the sum of the points of the students based on the grading rules. Here's a step-by-step explanation:

Call getPossibleAnswers to get the set of possible answers for the given expression.
Call getCorrectAnswer to get the correct answer of the expression.
Initialize a variable totalScore to 0.
Iterate through the array of answers. For each answer:
If the answer is equal to the correct answer, add 5 points to totalScore.
If the answer is in the set of possible answers, add 2 points to totalScore.
Return the final totalScore.
Overall, the solution uses a combination of recursion, memoization, and stack-based calculation to efficiently calculate the possible answers and the correct answer of the expression, and then grades the answers based on the grading rules.

*/