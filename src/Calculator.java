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

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {
    public void calculate(String mod, String inputNum1, String inputNum2) throws IOException {
        // Определяем формат арабский или римский
        boolean isRoman = isRomanFormat(inputNum1, inputNum2);

        // Приводим к арабским числам
        int num1 = normalizeNumber(inputNum1, isRoman);
        int num2 = normalizeNumber(inputNum2, isRoman);

        // Вычисляем ответ
        int answer = calc(mod, num1, num2);

        //Печатаем в нужном формате
        printInFormat(answer, isRoman);
    }

    private boolean isNumeric(String strNum) {
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

    private int normalizeNumber(String inputNum, boolean isRoman) {
        if (isRoman) {
            return romanToArabic(inputNum);
        } else {
            return Integer.parseInt(inputNum);
        }
    }

    private void printInFormat(int num, boolean isRomanFormat) {
        String output ;
        if (isRomanFormat) {
            output = arabicToRoman(num);
        } else {
            output = String.valueOf(num);
        }
        System.out.println(output);
    }

    private boolean isRomanFormat(String inputNum1, String inputNum2) throws IOException {
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

    // из римских чисел в арабские
    private int romanToArabic(String input) {
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
    private String arabicToRoman(int number) {
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

    private enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private final int value;

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

    private int calc(String mod, int num1, int num2) {
        return switch (mod) {
            case "+" -> num1 + num1;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException(mod + " - формат операции не верен!");
        };
    }
}
