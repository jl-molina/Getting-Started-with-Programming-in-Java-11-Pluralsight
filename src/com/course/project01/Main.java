package com.course.project01;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double[] leftVals = {100.0d, 25.0d, 225.0d, 11.0d};
        double[] rightVals = {100.0d, 25.0d, 225.0d, 11.0d};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        if (args.length == 0) {
            for (int i = 0; i < opCodes.length; i++) {
                results[i] = execute(opCodes[i], leftVals[i], rightVals[i]);
            }
            for (double currentResult : results) {
                System.out.println(currentResult);
            }
        } else if (args.length == 1 && args[0].equals("interactive")) {
            executeInteractively();
        } else if (args.length == 3) {
            handleCommandLine(args);
        } else {
            System.out.println("Please provide an operation code and 2 numeric values");
        }
    }

    private static double execute(char op, double num1, double num2) {
        double result = 0.0d;
        switch (op) {
            case 'd':
                result = num1 / num2;
                break;
            case 'a':
                result = num1 + num2;
                break;
            case 's':
                result = num1 - num2;
                break;
            case 'm':
                result = num1 * num2;
                break;
        }

        return result;
    }

    private static void handleCommandLine(String[] args) {
        char opCode = args[0].charAt(0);
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(opCode, leftVal, rightVal);
        System.out.println("Command Line Result: " + result);
    }

    private static void executeInteractively() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an operation and two numbers: ");
        String userInput = scanner.nextLine();
        String[] parts = userInput.split(" ");
        performOperation(parts);

    }

    private static void performOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
        if (opCode == 'w') {
            handleWhen(parts);
        } else {
        double leftVal = valueFromWord(parts[1]);
        double rightVal = valueFromWord(parts[2]);
        double result = execute(opCode, leftVal, rightVal);
        displayResult(opCode, leftVal, rightVal, result);
        }

    }

    private static void handleWhen(String[] parts) {
        LocalDate startDate = LocalDate.parse(parts[1]);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);

        String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);

        System.out.println(output);
    }

    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
        /*
        StringBuilder builder = new StringBuilder();
        builder.append(leftVal);
        builder.append(" ");
        builder.append(symbol);
        builder.append(" ");
        builder.append(rightVal);
        builder.append(" = ");
        builder.append(result);
        String output = builder.toString();
        */

        String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);

        System.out.println(output);
    }

    private static char opCodeFromString(String opName) {
        return opName.charAt(0);
    }

    private static char symbolFromOpCode(char opCode) {
        char[] opCodes = {'d', 'a', 's', 'm'};
        char[] symbols = {'/', '+', '-', '*'};
        char symbol = ' ';

        for (int i = 0; i < opCodes.length; i++) {
            if (opCodes[i] == opCode) {
                symbol = symbols[i];
                break;
            }
        }

        return symbol;
    }

    private static double valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"
        };

        double value = -1d;

        for(int i = 0; i < numberWords.length; i++) {
            if (word.equals(numberWords[i])) {
                value = i;
                break;
            }
        }

        if (value == -1d) {
            value = Double.parseDouble(word);
        }

        return value;
    }

}
