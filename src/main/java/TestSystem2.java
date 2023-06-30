//import jdk.incubator.vector.VectorOperators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/*-- Создание базы данных
CREATE DATABASE test_database;

-- Подключение к базе данных
\c test_database;

-- Создание таблицы test_results
CREATE TABLE test_results (
                              id SERIAL PRIMARY KEY,
                              correct_answers INT
);

-- Вставка начальных данных (необязательно)
INSERT INTO test_results (correct_answers) VALUES
(3), -- Пример результатов теста
(2),
(1);*/
public class TestSystem2 {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";


    public static void main(String[] args) {
        Test test = new Test();

        try(Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD)){
            test.passTest(connection);
        }catch (SQLException e){
            e.printStackTrace();
        }
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
    public void passTest(Connection connection)throws SQLException{
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            int userAnswer = scanner.nextInt();

            if (question.checkAnswer(userAnswer)) {
                correctAnswers++;
            }
        }
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO test_results (correct_answers) VALUES (?)")) {
            statement.setInt(1, correctAnswers);
            statement.executeUpdate();
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