package Calculator;


import java.util.Scanner;

public class CalculatorArabian {
    public static void main(String[] args) {
        // 2+3=5
        // V-VII =II
        Converter converter = new Converter();
        String[] actions = {"+","-","/","*"};
        String[] regexActions = {"\\+","-","/","\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp=scn.nextLine();
        // определяем арифметическое действие
        int actionIndex=-1;
        for(int i=0;i<actions.length;i++){
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
                // если не нашли арифметического действия, завершаем программу
            }
            if (actionIndex==-1){
                    System.out.println("Некорректное выражение");
                    return;
                }
                //"2; 4".split(";")-> {"2", "4"} делим строчку по найденному арифметическому знаку
                String[] data = exp.split(regexActions[actionIndex]);
                // определяем,находятся ли числа в одном формате( оба римские или оба арабские)
                if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                    int a, b;
                    // определяем, римские ли это числа
                    boolean isRoman = converter.isRoman(data[0]);
                    if (isRoman) {
                        // если римские, то конвертируем их в арабские
                        // Х+5
                        // Х -10
                        // V+5
                        a = converter.romanToInt(data[0]);
                        b = converter.romanToInt(data[1]);
                    } else {
                        //конвертируем арабские числа из строки в число
                        a = Integer.parseInt(data[0]);
                        b = Integer.parseInt(data[1]);
                    }
                    //выполняем арифметическое действие с числами
                    int result;
                    switch (actions[actionIndex]) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        default:
                            result = a / b;
                            break;
                    }
                    if(isRoman) {
                        // если числа были арабские - возвращаем результат в арабском числе
                        System.out.println(converter.intToRoman(result));
                    } else {
                        System.out.println(result);
                    }
                } else {
                    System.out.println("Числа должны быть в одном формате");

                }
            }
        }
    }
