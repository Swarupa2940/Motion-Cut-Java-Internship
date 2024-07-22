import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class QuizQuestion {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public QuizQuestion(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect(int answer) {
        return answer == correctAnswer;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int userAnswer = getUserInput(scanner, options.length);
            if (question.isCorrect(userAnswer - 1)) {
                score++;
            }
        }

        displayScore();
    }

    private int getUserInput(Scanner scanner, int numOptions) {
        int userAnswer = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter your answer (1-" + numOptions + "): ");
            if (scanner.hasNextInt()) {
                userAnswer = scanner.nextInt();
                if (userAnswer >= 1 && userAnswer <= numOptions) {
                    validInput = true;
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and " + numOptions + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                  scanner.next(); // Clear the invalid input
            }
        }
        return userAnswer;
    }

    private void displayScore() {
        System.out.println("Your score: " + score + "/" + questions.size());
    }
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        quiz.addQuestion(new QuizQuestion(
                "What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"},
                2
        ));
        quiz.addQuestion(new QuizQuestion(
            "What is 2 + 2?",
            new String[]{"3", "4", "5", "6"},
            1
    ));

    quiz.addQuestion(new QuizQuestion(
            "Which planet is known as the Red Planet?",
            new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
            1
    ));

    quiz.start();
    }
}