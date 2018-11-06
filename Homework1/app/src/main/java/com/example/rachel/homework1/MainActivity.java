package com.example.rachel.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupEditText();
        setupButton();
        setupTextView();
    }

    private void setupEditText() {
        editText = (EditText) findViewById(R.id.edit_text);
    }

    private void setupButton() {
        button = (Button) findViewById(R.id.button);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText());
            }
        };
        button.setOnClickListener(click);
    }

    private void setupTextView() {
        textView = (TextView) findViewById(R.id.text_view);
    }
}
