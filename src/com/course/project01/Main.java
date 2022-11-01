package com.course.project01;

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

}
