import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class RomanConverter {
    // из римских чисел в арабские
    public static int romanToArabic(String input) {
        if (input.length() == 0) {
            throw new IllegalArgumentException("Получена пустая строка!");
        }
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


    public static int normalizeNumber(String inputNum, boolean isRoman) {
        if (isRoman) {
            return romanToArabic(inputNum);
        } else {
            return Integer.parseInt(inputNum);
        }
    }

    public static boolean isRomanFormat(String inputNum1, String inputNum2) throws IOException {
        // Определяем формат арабский или римский
        boolean allNumeric = isNumeric(inputNum1) && isNumeric(inputNum2);
        boolean allRoman = !isNumeric(inputNum1) && !isNumeric(inputNum2);

        // Все арабские числа?
        if (allNumeric) {
            return false;
            // Числа не арабские, а римские ли?
        } else if (allRoman) {
            // Можно ли римские цифры преобразовать в арабсие?
            try {
                romanToArabic(inputNum1);
                romanToArabic(inputNum2);
                return true;
            } catch (IllegalArgumentException ex) {
                throw new IOException("Цифры не римские или арабские!");
            }
        }
        throw new IOException("Цифры в неправильных форматах!");
    }

    private static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
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
}
