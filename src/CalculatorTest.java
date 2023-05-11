import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    @DisplayName("Из римской XV в арабскую 15.")
    void given1999Arabic_WhenConvertingToRoman_ThenReturnMCMXCIX() {
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
        String expected = "цифры в разных форматах!";

        Exception exception = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num1, num2));
        Exception exceptionInvert = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num2, num1));
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
        String expected = "цифры не римские или арабские!";

        Exception exception = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num1, num2));
        Exception exceptionInvert = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num2, num1));
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
                Calculator.calculateInFormat(mod, num1, num2));
        Exception exceptionRoman = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num3, num4));

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
        String expected = "Одно из чисел больше 10!";

        Exception exceptionArabic = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num1, num2));
        Exception exceptionRoman = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num3, num4));

        assertAll(
                () -> assertEquals(expected, exceptionArabic.getMessage()),
                () -> assertEquals(expected, exceptionRoman.getMessage())
        );
    }
}