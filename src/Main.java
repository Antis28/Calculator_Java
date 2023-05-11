import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//Требования:
//        Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами:
//        a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)!
//        Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
//
//        Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
//
//        Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.
//        На выходе числа не ограничиваются по величине и могут быть любыми.
//
//        Калькулятор умеет работать только с целыми числами.
//        Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,
//        при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
//
//        При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно,
//        при вводе арабских - ответ ожидается арабскими.
//
//        При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
//
//        При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
//        приложение выбрасывает исключение и завершает свою работу.
//
//        Результатом операции деления является целое число, остаток отбрасывается.
//
//        Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
//
//        Результатом работы калькулятора с римскими числами могут быть только положительные числа,
//        если результат работы меньше единицы, выбрасывается исключение
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        boolean isRoman = false;

        // Получаем числа
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пример для решения римскими или арабскими числами: ");
        String inputNum1 = in.next();
        String mod = in.next();
        String inputNum2 = in.next();

        // Определяем формат арабский или римский
        isRoman = isRomanFormat(inputNum1, inputNum2);

        if (isRoman) {
            RomanNumeral n1 = ConvertToRomanNumbers(num1);
            RomanNumeral n2 = ConvertToRomanNumbers(num2);
        }

        PrintInFormat((n1.getValue() / n2.getValue()));
        System.out.println(mod);
        System.out.println(num2);


    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            throw new IllegalArgumentException();
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void PrintInFormat(RomanNumeral num, boolean isRomanFormat) {
        if (isRomanFormat) {
            System.out.println(num.name());
        }
        System.out.println(num.getValue());
    }

    public static boolean isRomanFormat(String inputNum1, String inputNum2) throws IOException {
        // Определяем формат арабский или римский
        if (!isNumeric(inputNum1)) {
            // Вторая цифра тоже римская?
            if (!isNumeric(inputNum2)) {
                return true;
            } else {
                throw new IOException(inputNum1 + " и " + inputNum2 + " - цифры в разных форматах!");
            }
        }
        return false;
    }

    public static String calc(String mod, int num1,int num2) {
        switch (mod) {
            case "+": return String.valueOf (num1 + num2);
            case "-": return String.valueOf (num1- num2);
            case "*": return String.valueOf (num1 * num2);
            case "/": return String.valueOf (num1 / num2);
            default: throw
        }
    }

    // из римских чисел в арабские
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    // из арабских чисел в римские
    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }
}