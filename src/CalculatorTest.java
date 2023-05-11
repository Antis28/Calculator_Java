import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    @DisplayName("Из римской XV в арабскую 15.")
    void given15Arabic_WhenConvertingToRoman_ThenReturnXV() {
        String roman15 = "XV";
        int result = RomanConverter.romanToArabic(roman15);

        assertEquals(15, result);
    }

    @Test
    @DisplayName("Если разные форматы, то исключение")
    void givenDifferentFormatNumber_WhenCalculate_ThenThrowException() {

        String num1 = "V";
        String num2 = "1";
        String mod = "+";
        String expected = "Цифры в неправильных форматах!";

        Exception exception = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num1, num2));
        Exception exceptionInvert = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num2, num1));
        assertAll(
                () -> assertEquals(expected, exception.getMessage()),
                () -> assertEquals(expected, exceptionInvert.getMessage())
        );
    }

    @Test
    @DisplayName("Если одно из чисел не число, то исключение")
    void givenNotNumber_WhenCalculate_ThenThrowException() {

        String num1 = "j";
        String num2 = "V";
        String mod = "+";
        String expected = "Цифры не римские или арабские!";

        Exception exception = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num1, num2));
        Exception exceptionInvert = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num2, num1));
        assertAll(
                () -> assertEquals(expected, exception.getMessage()),
                () -> assertEquals(expected, exceptionInvert.getMessage())
        );
    }

    @Test
    @DisplayName("Переданные числа больше 10")
    void givenNumberBigest10_WhenCalculate_ThenThrowException() {

        String num1 = "1";
        String num2 = "11";
        String num3 = "I";
        String num4 = "XI";
        String mod = "+";
        String expected = "Одно из чисел больше 10!";

        Exception exceptionArabic = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num1, num2));
        Exception exceptionRoman = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num3, num4));

        assertAll(
                () -> assertEquals(expected, exceptionArabic.getMessage()),
                () -> assertEquals(expected, exceptionRoman.getMessage())
        );
    }

    @Test
    @DisplayName("Переданные числа десятичные")
    void givenNumberFloat_WhenCalculate_ThenThrowException() {

        String num1 = "1.1";
        String num2 = "10";
        String num3 = "1";
        String num4 = "4.4";
        String mod = "+";
        String expected = "Цифры в неправильных форматах!";

        Exception exceptionArabic = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num1, num2));
        Exception exceptionRoman = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInTwoFormat(mod, num3, num4));

        assertAll(
                () -> assertEquals(expected, exceptionArabic.getMessage()),
                () -> assertEquals(expected, exceptionRoman.getMessage())
        );
    }

    @Test
    @DisplayName("При делении результат целое число")
    void given3Div2_WhenCalculate_ThenReturnInteger1() throws IOException {

        String num1 = "3";
        String num2 = "2";
        String mod = "/";
        int expected = 1;

        assertEquals(expected, Calculator.calculateInTwoFormat(mod, num1, num2));

    }

    @Test
    @DisplayName("Все операции правильные.")
    void givenValidOperation_WhenCalculate_ThenReturnInteger1() {

        String num1 = "10";
        String num2 = "2";
        String mod1 = "/";
        String mod2 = "+";
        String mod3 = "-";
        String mod4 = "*";
        int expected1 = 5;
        int expected2 = 12;
        int expected3 = 8;
        int expected4 = 20;

        assertAll(
                () -> assertEquals(expected1, Calculator.calculateInTwoFormat(mod1, num1, num2)),
                () -> assertEquals(expected2, Calculator.calculateInTwoFormat(mod2, num1, num2)),
                () -> assertEquals(expected3, Calculator.calculateInTwoFormat(mod3, num1, num2)),
                () -> assertEquals(expected4, Calculator.calculateInTwoFormat(mod4, num1, num2))
        );

    }

    @Test
    @DisplayName("Все операции неправильные.")
    void givenNotValidOperation_WhenCalculate_ThenReturnInteger1() {

        String num1 = "10";
        String num2 = "2";
        String mod1 = "%";
        String mod2 = "^";
        String mod3 = ":";
        String mod4 = "&";
        String expected1 = mod1 + " - формат операции не верен!";
        String expected2 = mod2 + " - формат операции не верен!";
        String expected3 = mod3 + " - формат операции не верен!";
        String expected4 = mod4 + " - формат операции не верен!";


        Exception exception1 = assertThrows(java.lang.IllegalArgumentException.class, () ->
                Calculator.calculateInTwoFormat(mod1, num1, num2));
        Exception exception2 = assertThrows(java.lang.IllegalArgumentException.class, () ->
                Calculator.calculateInTwoFormat(mod2, num1, num2));
        Exception exception3 = assertThrows(java.lang.IllegalArgumentException.class, () ->
                Calculator.calculateInTwoFormat(mod3, num1, num2));
        Exception exception4 = assertThrows(java.lang.IllegalArgumentException.class, () ->
                Calculator.calculateInTwoFormat(mod4, num1, num2));

        assertAll(
                () -> assertEquals(expected1, exception1.getMessage()),
                () -> assertEquals(expected2, exception2.getMessage()),
                () -> assertEquals(expected3, exception3.getMessage()),
                () -> assertEquals(expected4, exception4.getMessage())
        );
    }
    @Test
    @DisplayName("Арабские числа могут быть отрицательные.")
    void givenArabicNum_ThenReturnIntegerLessZero() throws IOException {
        String num1 = "1";
        String num2 = "2";
        String mod = "-";
        int expected = -1;

        assertEquals(expected, Calculator.calculateInTwoFormat(mod, num1, num2));
    }

    @Test
    @DisplayName("Арабские числа могут быть нулем.")
    void givenArabicNum_ThenReturnZero() throws IOException {

        String num1 = "1";
        String num2 = "1";
        String mod = "-";
        int expected = 0;

        assertEquals(expected, Calculator.calculateInTwoFormat(mod, num1, num2));

    }

}