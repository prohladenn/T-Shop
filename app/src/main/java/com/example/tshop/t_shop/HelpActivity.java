package com.example.tshop.t_shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends Activity {

    ImageView buttonBack;
    EditText textName;
    EditText textNumber;
    EditText textTheme;
    TextView textViewProblem;
    FrameLayout frameProblem;
    FrameLayout frameSend;

    String textProblem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        buttonBack = findViewById(R.id.button_help_back);
        textName = findViewById(R.id.edit_text_help_name);
        textNumber = findViewById(R.id.edit_text_help_number);
        textTheme = findViewById(R.id.edit_text_help_theme);
        textViewProblem = findViewById(R.id.edit_text_help_description);
        frameProblem = findViewById(R.id.button_help_description);
        frameSend = findViewById(R.id.button_help_send);

        Intent intent = getIntent();

        switch (intent.getIntExtra("problem", 0)) {
            case 1: {
                textTheme.setText(R.string.problem1);
                break;
            }
            case 2: {
                textTheme.setText(R.string.problem2);
                break;
            }
            default:
                break;
        }

        buttonBackListener();
        frameProblemListener();
        frameSendListener();
    }

    private void buttonBackListener() {
        buttonBack.setOnClickListener(v -> finish());
    }

    private void frameProblemListener() {
        frameProblem.setOnClickListener(v -> {
            Intent intent = new Intent(HelpActivity.this, HelpDescriptionActivity.class);
            if (!textProblem.isEmpty()) {
                intent.putExtra("text_problem", textProblem);
                textProblem = "";
            } else intent.putExtra("text_problem", "");
            startActivityForResult(intent, 1234);
        });
    }

    private void frameSendListener() {
        frameSend.setOnClickListener(v -> {
            if (textProblem.isEmpty())
                Toast.makeText(HelpActivity.this, "Заполните отзыв!",
                        Toast.LENGTH_LONG).show();
            else Toast.makeText(HelpActivity.this, "Ваш отзыв отправлен!",
                    Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK && data != null) {

            textProblem = data.getStringExtra("text_problem");
            if (textProblem.isEmpty()) {
                textViewProblem.setText(R.string.help_description);
                textViewProblem.setTextColor(getResources().getColor(R.color.colorText));
            } else {
                String firstLine = textProblem.substring(0, textProblem.indexOf('\n'));
                textViewProblem.setText(firstLine);
                textViewProblem.setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}