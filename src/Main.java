import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        boolean isRoman = false;

        // Получаем числа
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пример для решения римскими или арабскими числами: ");
        String num1 = in.next();
        String mod = in.next();
        String num2 = in.next();

        CheckRomanFormat(num1, num2);


        PrintInFormat((n1.getValue() / n2.getValue()));
        System.out.println(mod);
        System.out.println(num2);


    }

    private static boolean CheckRomanFormat(String num1, String num2) throws IOException {
        boolean isRoman = false;
        // Определяем формат арабский или римский
        if (!isNumeric(num1)) {
            // Вторая цифра тоже римская?
            if (!isNumeric(num2)) {
               isRoman = true;
            } else {
                throw new IOException("Цифры в разных форматах!");
            }
        }
        return  isRoman;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void PrintInFormat(RomanNumbers num, boolean isRomanFormat) {
        if (isRomanFormat) {
            System.out.println(num.name());
        }
        System.out.println(num.getValue());
    }

    public static String calc(String input) {
        return input;
    }

    public static RomanNumbers ConvertToRomanNumbers(String s) {
        return switch (s) {
            case "I", "1" -> RomanNumbers.I;
            case "II", "2" -> RomanNumbers.II;
            case "III", "3" -> RomanNumbers.III;
            case "IV", "4" -> RomanNumbers.IV;
            case "V", "5" -> RomanNumbers.V;
            case "VI", "6" -> RomanNumbers.VI;
            case "VII", "7" -> RomanNumbers.VII;
            case "VIII", "8" -> RomanNumbers.VIII;
            case "IX", "9" -> RomanNumbers.IX;
            case "X", "10" -> RomanNumbers.X;
            default -> throw new IllegalArgumentException("Строка не цифра из допустимого диапазона");
        };
    }

    enum RomanNumbers {
        I(1), II(2), III(3), IV(4), V(5), VI(6),
        VII(7), VIII(8), IX(9), X(10);

        private int value;

        private RomanNumbers(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}