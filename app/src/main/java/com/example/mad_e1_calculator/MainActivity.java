/**
 * This class contains methods for implementing simple calculator app on Android
 * MAD-E2
 *
 * @author Pratyush Kumar (github.com/pratyushgta)
 */

package com.example.mad_e1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView input, output;
    int first_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        assign(R.id.button_div);
        assign(R.id.button_mul);
        assign(R.id.button_add);
        assign(R.id.button_diff);
        assign(R.id.button_0);
        assign(R.id.button_1);
        assign(R.id.button_2);
        assign(R.id.button_3);
        assign(R.id.button_4);
        assign(R.id.button_5);
        assign(R.id.button_6);
        assign(R.id.button_7);
        assign(R.id.button_8);
        assign(R.id.button_9);
        assign(R.id.button_AC);
        assign(R.id.button_equal);

    }

    void assign(int id) {
        Button b = findViewById(id);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (first_time == 0) { //by default, input field has 0. This would remove that 0 when any button is pressed for the first time
            input.setText("");
            first_time = 1;
        }
        Button button_clicked = (Button) view;
        String buttonText = button_clicked.getText().toString();
        //input.setText(buttonText);
        String expression = input.getText().toString();

        if (buttonText.equals("AC")) {
            input.setText("0");
            output.setText("");
            first_time = 0;
            return;
        } else if (buttonText.equals("=")) {
            String r = calculate(expression);
            output.setText(r);
            return;
        } else {
            expression += buttonText;
            input.setText(expression);
            return;
        }

    }

    public String calculate(String exp) {
        if (exp.length() == 0) {
            output.setText("");
            return "You thought this hasn't been cared for?";
        } else {

            if (!CheckOperand(exp.charAt(0)))
                return "Skill issue.";
            int res = value(exp.charAt(0));
            for (int i = 1; i < exp.length(); i += 2) {
                char operator = exp.charAt(i);
                if (i + 1 >= exp.length())
                    return "Bruh? U okay?";
                char operand = exp.charAt(i + 1);
                if (!CheckOperand(operand))
                    return "Rookie mistake!";
                if (operator == '+')
                    res += value(operand);
                else if (operator == '-')
                    res -= value(operand);
                else if (operator == '*')
                    res *= value(operand);
                else if (operator == '/')
                    res /= value(operand);
                else
                    return "Bruh. How difficult is it?";
            }
            output.setText("");
            return String.valueOf(res);
        }
    }

    public boolean CheckOperand(char c) {
        return (c >= '0' && c <= '9');
    }

    public int value(char c) {
        return c - '0';
    }
}