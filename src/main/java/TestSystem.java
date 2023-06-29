import java.util.Scanner;

public class TestSystem {
    public static void main(String[] args) {

        String[][] questions = {
                {"В каком году была основана Java?",
                        "1990",
                        "1991",
                        "1992"},
                {"Кто является создателем языка Java?",
                        "Билл Гейтс",
                        "Джеймс Гослинг",
                        "Ларри Эллисон"},
                {"Какой тип данных используется для хранения логических значений в Java?",
                        "boolean",
                        "int",
                        "float"}
        };

        String[] answers = {"2", "2", "1"}; // массив правильных ответов

        Scanner scanner = new Scanner(System.in);

        int correctAnswers = 0;
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i][0]);
            for (int j = 1; j < questions[i].length; j++) {
                System.out.println(j + ") " + questions[i][j]);
            }
            String input = scanner.nextLine();
            if (input.equals(answers[i])) {
                correctAnswers++;
            }
        }

        System.out.println("Тест завершен. Количество правильных ответов: " + correctAnswers + "/" + questions.length);

        scanner.close();
    }
}
