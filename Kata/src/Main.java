import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        calk(expression);
    }
    public static void calk(String expression) throws Exception {
        String[] data;
        char action;
        if (expression.contains(" + ")) {
            data = expression.split(" \\+ ");
            action='+';
        }
        else if (expression.contains(" - ")) {
            data = expression.split(" - ");
            action='-';
        }
        else if (expression.contains(" * ")) {
            data = expression.split(" \\* ");
            action='*';
        }
        else if (expression.contains(" / ")) {
            data = expression.split(" / ");
            action = '/';
        }
        else {
            throw new Exception("Некорректный знак действия");
        }
        if (data[0].length()>12 || data[1].length()>12) {
            throw new Exception("строка должна содержать не более 10 символов");
        }

        if (action == '*' || action == '/') {
            if (data[1].contains("\"")) throw new Exception("Строчку можно делить или умножать только на число");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        if (action == '+') {
            printInQuotes(data[0] + data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            if (multiplier > 10 || multiplier <= 0) {
                throw new Exception("Число должно быть от 1 до 10 включительно");
            }
            String result = "";
            for (int i = 0; i < multiplier; i++) {
                result += data[0];
            }
            printInQuotes(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
                printInQuotes(result);
            }
        } else {
            int newLen = data[0].length() / Integer.parseInt(data[1]);
            String result = data[0].substring(0, newLen);
            printInQuotes(result);
        }
    }


    static void printInQuotes(String result) {
        if (result.length() > 40) {
            System.out.println("\"" + result.substring(0, 40) + "..." + "\"");
        }
        else {
            System.out.println("\"" + result + "\"");
        }
    }

}