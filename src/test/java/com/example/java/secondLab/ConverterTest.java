package com.example.java.secondLab;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConverterTest {

    @Nested
    class Positive_Tests {
        @Test
        void computeWithSpace() {
            Converter converter = new Converter("   3 + 7 *   3    ");

            assertEquals(24, converter.compute());
        }

        @Test
        void computeCorrect() {
            Converter converter = new Converter("5+6");

            assertEquals(11, converter.compute());
        }

        @Test
        void checkBracketsOrder() {
            Converter converter = new Converter("5-(6+1)");

            assertDoesNotThrow(converter::compute);
        }

        @Test
        void checkLogicOfString() {
            Converter converter = new Converter("5+6");

            assertDoesNotThrow(converter::compute);
        }
    }

    @Nested
    class Negative_Tests {

        @Test
        void checkBracketsOrder() {
            Converter converter = new Converter("5-())(6+1)");
            RuntimeException rex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("Bracket order is incorrect", rex.getMessage());
        }

        @Test
        void checkLogicOfStringStart() {
            Converter converter = new Converter("-(5+6)");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("String must start with a number, variable or bracket", ex.getMessage());
        }

        @Test
        void checkLogicOfStringEnd() {
            Converter converter = new Converter("(5+6)+");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("String must not end with a sign", ex.getMessage());
        }

        @Test
        void checkVariableName() {
            Converter converter = new Converter("5 + ab + 3");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("Variable must be represented by a single letter", ex.getMessage());
        }

        @Test
        void checkSignAfterVariable() {
            Converter converter = new Converter("a + b7");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("After a variable, there must be a sign", ex.getMessage());
        }

        @Test
        void checkAfterNumberSymbol() {
            Converter converter = new Converter("7(5+3)");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("After a number, there must be a sign or another number", ex.getMessage());
        }

        @Test
        void checkAfterOpenBracketSymbol() {
            Converter converter = new Converter("(*3 + 3)");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("After an open bracket, there must be another bracket or a number/variable", ex.getMessage());
        }

        @Test
        void checkAfterClosedBracketSymbol() {
            Converter converter = new Converter("(3 + 3)3");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("After a closing bracket, there must be another bracket or a sign", ex.getMessage());
        }

        @Test
        void checkTwoSignsInARow() {
            Converter converter = new Converter("3 ++ 3");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("There must not be two signs in a row", ex.getMessage());
        }

        @Test
        void checkEmptyBrackets() {
            Converter converter = new Converter("3 + () + 4");
            Exception ex = assertThrows(RuntimeException.class, converter::compute);

            assertEquals("There must not be empty brackets", ex.getMessage());
        }
    }
}
