package de.android.ayrathairullin.myapplication;

import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected Button sendButton;
    protected EditText questionText;
    protected TextView chatWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        questionText = findViewById(R.id.questionField);
        chatWindow = findViewById(R.id.chatWindow);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.onClick();
            }
        });
    }

    protected void onClick() {
        String text = questionText.getText().toString();
        questionText.setText("");

//        String answer = "Ok";
        AI.getAnswer(text, new Consumer<String>() {
            @Override
            public void accept(String answer) {
                chatWindow.append("<< " + answer + "\n");
            }
        });

        chatWindow.append(">> " + text + "\n");
    }
}
