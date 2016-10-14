package com.example.rohanrodrigues.basicmathquiz;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    int problemCount = 0, problemWrong = 0;
    int tempAnswer = 0;
    Button add, sub, mult, div;
    Button trueButton, falseButton;
    TextView totalProblems, questionsWrong, mainProblem, answerResult;
    int number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.additionButton);
        sub = (Button) findViewById(R.id.subtractButton);
        div = (Button) findViewById(R.id.divisButton);
        mult = (Button) findViewById(R.id.multButton);
        totalProblems = (TextView) findViewById(R.id.problem_attempt);
        questionsWrong = (TextView) findViewById(R.id.wrongQuestions);
        mainProblem = (TextView) findViewById(R.id.problem_main);
        answerResult = (TextView) findViewById(R.id.answerResult);

        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtract();
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiply();
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                divide();
            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                problemCount++;
                String problemsAttempted = "Problems Attempted: " + problemCount;
                totalProblems.setText(problemsAttempted);

                boolean result = answerCheck();
                if (result) {
                    String correct = "Correct!";
                    answerResult.setText(correct);
                }
                else {
                    problemWrong++;
                    String problemsWrong = "Problems Wrong: " + problemWrong;
                    questionsWrong.setText(problemsWrong);

                    String wrong = "Wrong!";
                    answerResult.setText(wrong);
                }

                generateNewProblem();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                problemCount++;
                String problemsAttempted = "Problems Attempted: " + problemCount;
                totalProblems.setText(problemsAttempted);

                boolean result = answerCheck();
                if (!result) {
                    String correct = "Correct!";
                    answerResult.setText(correct);
                }
                else {
                    problemWrong++;
                    String problemsWrong = "Problems Wrong: " + problemWrong;
                    questionsWrong.setText(problemsWrong);

                    String wrong = "Wrong!";
                    answerResult.setText(wrong);
                }

                generateNewProblem();
            }
        });
    }

    private void add() {
        number1 = (int)(Math.random()*100);
        number2 = (int)(Math.random()*100);
        int sum = number1 + number2;

        double num = Math.random();
        if (num >= 0.5) {
            mainProblem.setText(number1 + " + " + number2 + " = " + sum);
            tempAnswer = sum;
        }
        else {
            tempAnswer = (int)(Math.random()*100);
            mainProblem.setText(number1 + " + " + number2 + " = " + tempAnswer);
        }
    }

    private void subtract() {
        number1 = (int)(Math.random()*100);
        number2 = (int)(Math.random()*100);
        int high = Math.max(number1,number2);
        int low = Math.min(number1,number2);

        int answer = high - low;

        double num = Math.random();
        if (num >= 0.5) {
            mainProblem.setText(high + " - " + low + " = " + answer);
            tempAnswer = answer;
        }
        else {
            tempAnswer = (int)(Math.random()*100);
            mainProblem.setText(high + " - " + low + " = " + tempAnswer);
        }
    }

    private void multiply() {
        number1 = (int)(Math.random()*10);
        number2 = (int)(Math.random()*10);
        int product = number1 * number2;

        double num = Math.random();
        if (num >= 0.5) {
            mainProblem.setText(number1 + " * " + number2 + " = " + product);
            tempAnswer = product;
        }
        else {
            tempAnswer = (int)(Math.random()*100);
            mainProblem.setText(number1 + " * " + number2 + " = " + tempAnswer);
        }
    }

    private void divide() {
        number1 = (int) (Math.random() * 10);
        while (number1 == 0) {
            number1 = (int) (Math.random() * 10);
        }

        number2 = number1 * (int) (Math.random() * 100);
        int quotient = number2 / number1;

        double num = Math.random();
        if (num >= 0.5) {
            mainProblem.setText(number2 + " / " + number1 + " = " + quotient);
            tempAnswer = quotient;
        } else {
            tempAnswer = (int)(Math.random() * 100);
            mainProblem.setText(number2 + " / " + number1 + " = " + tempAnswer);
        }
    }

    private boolean answerCheck() {
        String equationType = findEquationType();
        if (equationType.equalsIgnoreCase("addition")) {
            int sum = number1 + number2;
            return sum == tempAnswer;
        }
        if (equationType.equalsIgnoreCase("subtraction")) {
            int high = Math.max(number1,number2);
            int low = Math.min(number1,number2);
            int answer = high - low;
            return answer == tempAnswer;
        }
        if (equationType.equalsIgnoreCase("multiplication")) {
            int product = number1 * number2;
            return product == tempAnswer;
        }
        if (equationType.equalsIgnoreCase("division")) {
            int quotient = number2 / number1;
            return quotient == tempAnswer;
        }
        return true;
    }

    private String findEquationType() {
        int high = Math.max(number1, number2);
        int low = Math.min(number1, number2);

        CharSequence text = mainProblem.getText();
        String subt = high + " - " + low + " = " + tempAnswer;
        String add = number1 + " + " + number2 + " = " + tempAnswer;
        String mult = number1 + " * " + number2 + " = " + tempAnswer;
        String div = high + " / " + low + " = " + tempAnswer;

        if (text.equals(subt)) {
            return "subtraction";
        }
        else if (text.equals(add)) {
            return "addition";
        }
        else if (text.equals(mult)) {
            return "multiplication";
        }
        else if (text.equals(div)) {
            return "division";
        }
        else {
            return "not found";
        }
    }

    private void generateNewProblem() {
        String equationType = findEquationType();

        if (equationType.equalsIgnoreCase("addition")) {
            add();
        }
        if (equationType.equalsIgnoreCase("subtraction")) {
            subtract();
        }
        if (equationType.equalsIgnoreCase("multiplication")) {
            multiply();
        }
        if (equationType.equalsIgnoreCase("division")) {
            divide();
        }
    }
}
