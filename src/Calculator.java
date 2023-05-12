//Требования:
//        Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами:
//        a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)!
//        Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
//
//      +  Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
//
//      +  Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.
//        На выходе числа не ограничиваются по величине и могут быть любыми.
//
//      +  Калькулятор умеет работать только с целыми числами.
//      +  Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,
//        при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
//
//      +  При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно,
//      +  при вводе арабских - ответ ожидается арабскими.
//
//      +  При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
//
//      +  При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
//        приложение выбрасывает исключение и завершает свою работу.
//
//      +  Результатом операции деления является целое число, остаток отбрасывается.
//
//      +  Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
//
//      +  Результатом работы калькулятора с римскими числами могут быть только положительные числа,
//        если результат работы меньше единицы, выбрасывается исключение

import java.io.IOException;
import java.util.Objects;


public class Calculator {
    // Калькулятор для 2-х форматов
    public static int calculateInTwoFormat(String mod, String inputNum1, String inputNum2) throws IOException {

        if (Objects.equals(mod, "") || Objects.equals(inputNum1, "") || Objects.equals(inputNum2, "")) {
            throw new IOException("Не введены все числа или знак.");
        }

        // Определяем формат арабский или римский
        boolean isRoman = RomanConverter.isRomanFormat(inputNum1, inputNum2);

        // Приводим к арабским числам
        int num1 = RomanConverter.normalizeNumber(inputNum1, isRoman);
        int num2 = RomanConverter.normalizeNumber(inputNum2, isRoman);

        if (num1 > 10 || num2 > 10)
            throw new IOException("Одно из чисел больше 10!");

        int answer = calculateArabic(mod, num1, num2);
        if (answer < 0 && isRoman)
            throw new IOException("Римское число меньше 1!");
        return answer;
    }

    // Калькулятор только для арабских цифр
    public static int calculateArabic(String mod, int inputNum1, int inputNum2) {
        return switch (mod) {
            case "+" -> inputNum1 + inputNum2;
            case "-" -> inputNum1 - inputNum2;
            case "*" -> inputNum1 * inputNum2;
            case "/" -> inputNum1 / inputNum2;
            default -> throw new IllegalArgumentException(mod + " - формат операции не верен!");
        };
    }
}
