package com.example.java.secondLab;

public class ConverterException {

    public static class FormulaStartException extends RuntimeException {
        public FormulaStartException() {
            super("String must start with a number, variable or bracket");
        }
    }

    public static class FormulaEndException extends RuntimeException {
        public FormulaEndException() {
            super("String must not end with a sign");
        }
    }

    public static class VariableMustBeSingleLetterException extends RuntimeException {
        public VariableMustBeSingleLetterException() {
            super("Variable must be represented by a single letter");
        }
    }

    public static class AfterVariableException extends RuntimeException {
        public AfterVariableException() {
            super("After a variable, there must be a sign");
        }
    }

    public static class AfterNumberException extends RuntimeException {
        public AfterNumberException() {
            super("After a number, there must be a sign or another number");
        }
    }

    public static class AfterOpenBracketException extends RuntimeException {
        public AfterOpenBracketException() {
            super("After an open bracket, there must be another bracket or a number/variable");
        }
    }

    public static class AfterClosingBracketException extends RuntimeException{
        public AfterClosingBracketException() {
            super("After a closing bracket, there must be another bracket or a sign");
        }
    }

    public static class TwoSignsInARowException extends RuntimeException {
        public TwoSignsInARowException() {
            super("There must not be two signs in a row");
        }
    }

    public static class EmptyBracketsException extends RuntimeException {
        public EmptyBracketsException() {
            super("There must not be empty brackets");
        }
    }

    public static class BracketOrderException extends RuntimeException {
        public BracketOrderException() {
            super("Bracket order is incorrect");
        }
    }
}
