import java.util.Scanner;


public class Main {
    public static void main(String[] args)  {
        System.out.println("Hello and welcome!");

        // Получаем числа
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пример для решения римскими или арабскими числами: ");
        String fullString;

        try {
            String inputNum1 = in.next();
            if (inputNum1.equals("exit")) {
                System.out.println("exit");
            }

            String mod = in.next();
            String inputNum2 = in.next();

            // Определяем формат арабский или римский
            boolean isRoman = RomanConverter.isRomanFormat(inputNum1, inputNum2);

            fullString = inputNum1 + mod + inputNum2;
            System.out.println("Вы ввели - " + fullString);

            int answer;

            answer = Calculator.calculateInTwoFormat(mod, inputNum1, inputNum2);
            //Печатаем в нужном формате
            printInFormat(answer, isRoman);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    private static void printInFormat(int num, boolean isRomanFormat) {
        String output;
        if (isRomanFormat) {
            output = RomanConverter.arabicToRoman(num);
        } else {
            output = String.valueOf(num);
        }
        System.out.println(output);
    }


}