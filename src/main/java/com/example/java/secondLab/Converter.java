package com.example.java.secondLab;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Scanner;

public class Converter {

    /**Словарь, отвечающий за хранение значений переменных (key - переменная, value - значение)*/
    private final Map<String, Double> variables;

    /**Символьный массив, хранящий формулу посимвольно и без пробелов*/
    private final char[] formula;

    /**Список элементов формулы без пробелов*/
    private final List<String> elements;

    /**Список элементов формулы в обратной польской нотации*/
    private final List<String> postfixElements;


    /**
     * Консктруктор класса, инициализирующий все необходимые поля класса
     * и приводящий строковую формулу к массиву символов без пробелов
     * @param toCompute Исходный вид арифметического выражения для вычисления
     */
    public Converter(final String toCompute) {
        if (toCompute != null) {
            formula = toCompute.replace(" ","").toCharArray();
            elements = new ArrayList<>();
            postfixElements = new ArrayList<>();
            variables = new HashMap<>();
        }
        else {
            throw new RuntimeException("Empty string");
        }
    }


    /**
     * Метод вычисляющий значение арифметического выражения
     * @return Итоговое значение выражения
     */
    public double compute(){
        Deque<Double> numbers = new LinkedList<>();

        ifLogicBrokenThrow();

        formulaToList();
        goToReversePolandNotation();

        for (var elem : postfixElements) {
            if (Character.isDigit(elem.charAt(0))) {
                numbers.push(Double.parseDouble(elem));
            }
            else if (isSign(elem)) {
                var second = numbers.pop();
                var first = numbers.pop();

                switch (elem) {
                    case "+" -> numbers.push(first + second);
                    case "-" -> numbers.push(first - second);
                    case "*" -> numbers.push(first * second);
                    case "/" -> {
                        if (second == 0) {
                            throw new RuntimeException("Divide by zero");
                        }
                        numbers.push(first / second);
                    }
                    case "^" -> numbers.push(Math.pow(first, second));
                }
            }
            else {
                if (!variables.containsKey(elem)) {
                    variables.put(elem, getVarValue(elem));
                }
                numbers.push(variables.get(elem));
            }
        }

        return numbers.pop();
    }


    /**
     * Метод преобразующий выражение из массива символов
     * в строковый список его элементов
     */
    private void formulaToList() {
        StringBuilder number = new StringBuilder();
        for (Character elem : formula) {
            if (!Character.isDigit(elem)) {
                if (!number.toString().isEmpty()) {
                    elements.add(number.toString());
                    number = new StringBuilder();
                }

                elements.add(elem.toString());
            } else {
                number.append(elem);
            }
        }

        if (!number.toString().isEmpty()) {
            elements.add(number.toString());
        }
    }


    /**
     * Метод приводящий выражение в постфиксный вид
     * и записывающий его в постфиксный список элементов
     */
    private void goToReversePolandNotation() {
        Deque<String> operatorsOrder = new LinkedList<>();

        for (var elem : elements) {
            switch (elem) {
                case "(":
                    operatorsOrder.push(elem);
                    break;
                case ")":
                    while (!operatorsOrder.isEmpty() && !operatorsOrder.peek().equals("(")) {
                        postfixElements.add(operatorsOrder.pop());
                    }
                    operatorsOrder.pop();
                    break;
                default:
                    if (isSign(elem)) {
                        while (!operatorsOrder.isEmpty() && getPriority(operatorsOrder.peek()) >= getPriority(elem)) {
                            postfixElements.add(operatorsOrder.pop());
                        }
                        operatorsOrder.push(elem);
                    } else {
                        postfixElements.add(elem);
                    }
                    break;
            }
        }

        postfixElements.addAll(operatorsOrder);
    }


    /**
     * Метод проверяющий расстановку скобок в выражении
     * @return результат проверки: true - корректно, false - некорректно
     */
    private boolean isCorrectBracketsOrder() {
        int balance = 0;

        for (Character elem : formula) {
            if (elem == '(') {
                balance++;
            } else if (elem == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }


    /**
     * Метод проверяющий выражение на корректность для вычисления (без учета порядка скобок)
     * @return сообщение об ошибке, в случае проблем; пустую строку - в случае корректности
     */
    @NotNull
    private String getErrorMessageIfLogicBroken() {
        String availableSigns = "+-*/^";

        if (availableSigns.indexOf(formula[0]) != -1) {
            return "String must be started with number/variables or bracket";
        }

        if (availableSigns.indexOf(formula[formula.length - 1]) != -1) {
            return "String must not ended on sign";
        }


        return getMessageIfNeighboringCharactersNotComparable(availableSigns);
    }


    /**
     * Метод проверяющий выражение на корректность для вычисления два соседних элемента
     * @return если есть нарушения - сообщение об ошибке, иначе - пустую строку
     */
    private String getMessageIfNeighboringCharactersNotComparable (String availableSigns) {
        for (int i = 0; i < formula.length - 1; i++) {
            char currentChar = formula[i];
            char nextChar = formula[i + 1];

            if (Character.isLetter(currentChar) && Character.isLetter(nextChar)) {
                return "Variable must be represented by a single letter";
            }

            if (Character.isLetter(currentChar) && !availableSigns.contains(String.valueOf(nextChar)) && nextChar != ')') {
                return "After a variable, there must be a sign";
            }

            if (Character.isDigit(currentChar) && !Character.isDigit(nextChar) && !availableSigns.contains(String.valueOf(nextChar)) && nextChar != ')') {
                return "After a number, there must be a sign or another number";
            }

            if (currentChar == '(' && !(nextChar == '(' || Character.isDigit(nextChar) || Character.isLetter(nextChar))) {
                return "After an open bracket, there must be another bracket or a number/variable";
            }

            if (currentChar == ')' && !(nextChar == '(' || nextChar == ')' || availableSigns.contains(String.valueOf(nextChar)))) {
                return "After a closing bracket, there must be another bracket or a sign";
            }

            if (availableSigns.contains(String.valueOf(currentChar)) && availableSigns.contains(String.valueOf(nextChar))) {
                return "There must not be two signs in a row";
            }

            if (currentChar == '(' && nextChar == ')') {
                return "There must not be empty brackets";
            }

            if (availableSigns.contains(String.valueOf(currentChar)) && nextChar == ')') {
                return "After a sign, there must be a number/variable or an open bracket";
            }
        }

        return "";
    }


    /**
     * Метод проверяющий логику выражения
     * в случае нарушений бросает RuntimeException с соответствующим сообщением
     */
    private void ifLogicBrokenThrow() {
        if (!isCorrectBracketsOrder()) {
            throw new RuntimeException("Bracket order error");
        }

        String msg = getErrorMessageIfLogicBroken();
        if (!msg.isEmpty()) {
            throw new RuntimeException(msg);
        }
    }


    /**
     * Метод, проверяющий является ли данная строка одним из символов допустимых операций
     * @param str Строка для проверки
     * @return Результат проверки: true - знак, false - не знак
     */
    private boolean isSign(String str) {
        return (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("^"));
    }


    /**
     * Метод запрашивающий у пользователя значение для текущей переменной
     * @param variable Переменная
     * @return Введённое пользователем значение
     */
    private double getVarValue(String variable) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Enter value for variable: " + variable);

        return sc.nextDouble();
    }


    /**
     * Метод возвращающий приоритет операции для переданного оператора
     * @param operator полученный оператор
     */
    private int getPriority(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}
