//import jdk.incubator.vector.VectorOperators;

import java.util.Scanner;

public class TestSystem2 {
    private String[][] questions ;
    private  String[] answers; // массив правильных ответов
  private   int correctAnswers ;
    private Scanner scanner;


    public TestSystem2() {
        this.questions = new String[][]{
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
        this.answers = new String[]{"2", "2", "1"}; // массив правильных ответов
        this.scanner = new Scanner(System.in);
        this.correctAnswers = 0;
    }
    public void startTest(){
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

    public static void main(String[] args) {
        TestSystem2 testSystem2 = new TestSystem2();
        testSystem2.startTest();
    }
}
