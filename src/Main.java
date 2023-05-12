import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // Получаем числа
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пример для решения римскими или арабскими числами в формате 1 + 1: ");

        try {
            String input = in.nextLine();
            String[] strings = input.split(" ");
            String inputNum1, mod, inputNum2;

            if (strings.length == 3) {
                inputNum1 = strings[0];
                mod = strings[1];
                inputNum2 = strings[2];
            }else {
                throw new IllegalArgumentException("Неверный формат ввода");
            }


            // Определяем формат арабский или римский
            boolean isRoman = RomanConverter.isRomanFormat(inputNum1, inputNum2);
            int answer = Calculator.calculateInTwoFormat(mod, inputNum1, inputNum2);

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