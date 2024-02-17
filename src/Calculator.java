import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner input = new Scanner(System.in);
        Main result = new Main();
        String task = input.nextLine();
        String answer = result.cals(task);
        System.out.println("Output:\n" + answer);

    }
}
class Main {
    public String cals(String input) {
        String[] space = {" "};
        String[] inputSplit = input.split(Arrays.toString(space));
        Main romanExamination = new Main();
        Main arabToRome = new Main();
        boolean romanOrArab = false;
        int result;

        if (inputSplit.length != 3) {
            throw new RuntimeException("Используется только 3 символа");}

        int firstNumber = 0;
        int secondNumber = 0;

        try {
            firstNumber = Integer.parseInt(inputSplit[0]);
            secondNumber = Integer.parseInt(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                firstNumber = romanExamination.romanToArab(inputSplit[0]);
                secondNumber = romanExamination.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ignored) {
            }
        }

        if ((firstNumber > 10) || (firstNumber < 1) || (secondNumber > 10) || (secondNumber < 1)){
             throw new RuntimeException("Неправильный ввод символов");
        }

         String operator = inputSplit[1];
         switch (operator){
             case "+" -> result = firstNumber + secondNumber;
             case "-" -> result = firstNumber - secondNumber;
             case "*" -> result = firstNumber * secondNumber;
             case "/" -> result = firstNumber / secondNumber;
             default -> throw new RuntimeException("Неправильный ввод знака");
         }
         String output;
         if (romanOrArab){
             if(result < 1){
                 throw new  NumberFormatException("Римское число не может быть меньше 0");
             } else {
                 output = arabToRome.arabToRome(result);
             }
         }else {
             output = Integer.toString(result);
         }
         return output;
    }

    Integer romanToArab(String romanInput) {
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }

    String arabToRome(int arabInput) {
        String result = "";
        int value;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++) {
            value = arabInput / arab[i];
            for (int a = 0; a < value; a++) {
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}