//import jdk.incubator.vector.VectorOperators;

import java.util.Scanner;

public class TestSystem2 {


    public static void main(String[] args) {
        Test test = new Test();
        test.passTest();
    }
}

class Test {
    private Question[] questions;
    private int correctAnswers;

    public Test(){
        questions = new Question[]{
                new Question("В каком году была основана Java?",
                        new Answer[]{
                                new Answer("1990"),
                                new Answer("1991"),
                                new Answer("1992")
                        },
                        2),
                new Question("Кто является создателем языка Java?",
                        new Answer[]{
                                new Answer("Билл Гейтс"),
                                new Answer("Джеймс Гослинг"),
                                new Answer("Ларри Эллисон")
                        },
                        2),
                new Question("Какой тип данных используется для хранения логических значений в Java?",
                        new Answer[]{
                                new Answer("boolean"),
                                new Answer("int"),
                                new Answer("float")
                        },
                        1)
        };
    }
    public void passTest(){
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            int userAnswer = scanner.nextInt();

            if (question.checkAnswer(userAnswer)) {
                correctAnswers++;
            }
        }
        System.out.println("Тест завершен. Количество правильных ответов: " + correctAnswers + "/" + questions.length);
        scanner.close();
    }

}

class Question {
    private String questionText;
    private Answer[] answers;
    private int correctAnswerIndex;

    public Question(String questionText, Answer[] answers, int correctAnswerIndex){
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }
    public void displayQuestion() {
        System.out.println(questionText);

        for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ") " + answers[i].getText());
        }
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
}

class Answer {
private  String text;
public Answer(String text){
    this.text=text;
}
public String getText(){
    return text;
}
}