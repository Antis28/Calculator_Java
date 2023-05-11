import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello and welcome!");

        // Получаем числа
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пример для решения римскими или арабскими числами: ");

        String inputNum1 = in.next();
        String mod = in.next();
        String inputNum2 = in.next();

        System.out.println("Вы ввели - " + inputNum1 + mod + inputNum2);

        Calculator calculator = new Calculator();
        calculator.calculate(mod, inputNum1,inputNum2);
    }


}