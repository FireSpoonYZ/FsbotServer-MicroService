package org.firespoon.fsbotserver.utils;

abstract public class CalculateUtils {
    public static Boolean isOperator(Character c) {
        return "+-*/()$".indexOf(c) >= 0;
    }

    public static Integer getOperatorPriority(Character c) {
        if (c == '(' || c == ')') {
            return 1;
        } else if (c == '+' || c == '-') {
            return 2;
        } else if (c == '*' || c == '/') {
            return 3;
        }
        return -1;
    }

    public static Integer calculate(Integer num1, Character op, Integer num2) {
        if (op == '+') {
            return num1 + num2;
        } else if (op == '-') {
            return num1 - num2;
        } else if (op == '*') {
            return num1 * num2;
        } else if (op == '/') {
            return num1 / num2;
        }
        throw new RuntimeException("表达式错误");
    }
}
