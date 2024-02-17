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
        String exception = "throws Exception";
        Main romanExamination = new Main();
        Main arabToRome = new Main();
        boolean romanOrArab = false;
        int result;
        if (inputSplit.length != 3) {
            return exception;
        }
        int firstnumber;
        int secondNumber;

        try {
            firstnumber = Integer.parseInt(inputSplit[0]);
            secondNumber = Integer.parseInt(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                firstnumber = romanExamination.romanToArab(inputSplit[0]);
                secondNumber = romanExamination.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException e1) {
                return exception;
            }
        }
         if ((firstnumber > 10) || (firstnumber < 1) || (secondNumber > 10)){
             return exception;
         }
         String operator = inputSplit[1];
         switch (operator){
             case "+" -> result = firstnumber + secondNumber;
             case "-" -> result = firstnumber - secondNumber;
             case "*" -> result = firstnumber * secondNumber;
             case "/" -> result = firstnumber / secondNumber;
             default -> {
                 return exception;
             }
         }
         String output;
         if (romanOrArab){
             if(result < 1){
                 return  exception;
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


