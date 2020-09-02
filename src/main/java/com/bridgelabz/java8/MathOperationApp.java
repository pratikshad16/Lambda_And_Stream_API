package com.bridgelabz.java8;

@FunctionalInterface
interface IMathFunction {
    int calculate (int a, int b);
    static void printResult(int a,int b,String function,IMathFunction fobj) {
        System.out.println("Result of" +function +" is " +fobj.calculate(a,b));
    }
}
public class MathOperationApp {
    public static void main(String[] args) {
        // using method reference instead of lambda expression
        //this expression implements "IMathFunction"  interface
        IMathFunction add = Integer::sum;

        IMathFunction multiply = (a,b) -> a*b;
        IMathFunction divide = (a,b) -> {
            if(b == 0) return 0;
            return a/b;
        };

        //add,multiply,divide of two numbers using lambda expressions
        System.out.println("Addition is:" +add.calculate(2,3));
        System.out.println("Multiplication is" +multiply.calculate(2,3));
        System.out.println("Division is :" +divide.calculate(6,3));

        //passing lambda as a function parameter to print result using static function
        IMathFunction.printResult(6,3," addition",add);
        IMathFunction.printResult(6,3," multiplication",multiply);
        IMathFunction.printResult(6,0," Division",divide);
    }
}
