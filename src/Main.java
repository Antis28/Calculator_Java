import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        // Получаем числа
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пример для решения римскими или арабскими числами в формате 1 + 1: ");

        String input = in.nextLine();

        String answer = calc(input);
        System.out.println(answer);
    }

    public static String calc(String input) throws IOException {
        String[] strings = input.split(" ");
        String leftOperator, sign, rightOperator;

        if (strings.length == 3) {
            leftOperator = strings[0];
            sign = strings[1];
            rightOperator = strings[2];
        } else {
            throw new IllegalArgumentException("Неверный формат ввода");
        }
        // Определяем формат арабский или римский
        boolean isRoman = RomanConverter.isRomanFormat(leftOperator, rightOperator);
        int answer = Calculator.calculateInTwoFormat(sign, leftOperator, rightOperator);

        if (isRoman) {
            return RomanConverter.arabicToRoman(answer);
        }
        return String.valueOf(answer);
    }
}