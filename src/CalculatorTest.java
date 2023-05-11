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
    void givenDiferentFormatNumber_WhenCalculete_ThenThrowExeption() {

        String num1 = "V";
        String num2 = "1";
        String mod = "+";
        String expected = "цифры в разных форматах!";

        Exception exception = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num1, num2));
        Exception exceptionInvert = assertThrows(java.io.IOException.class, () ->
                Calculator.calculateInFormat(mod, num2, num1));

//        assertEquals(expected, exception.getMessage());
        assertAll(
                () -> assertEquals(expected, exception.getMessage()),
                () -> assertEquals(expected, exceptionInvert.getMessage())
        );


    }
}