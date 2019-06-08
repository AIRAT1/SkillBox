package de.android.ayrathairullin.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    protected Button sendButton;
    protected EditText questionText;
    protected TextToSpeech tts;
    protected RecyclerView chatMessageList;
    protected MessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendButton = findViewById(R.id.sendButton);
        questionText = findViewById(R.id.questionField);
        chatMessageList = findViewById(R.id.chatMessageList);
        adapter = new MessageListAdapter();
        chatMessageList.setLayoutManager(new LinearLayoutManager(this));
        chatMessageList.setAdapter(adapter);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("ru"));
                }
            }
        });

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

        adapter.messageList.add(new Message(text, true));
        adapter.notifyDataSetChanged();

//        String answer = "Ok";
        AI.getAnswer(text, new Consumer<String>() {
            @Override
            public void accept(String answer) {
//                chatWindow.append("<< " + answer + "\n");
                adapter.messageList.add(new Message(answer, false));
                adapter.notifyDataSetChanged();
                chatMessageList.scrollToPosition(adapter.messageList.size() - 1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tts.speak(answer, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });

//        chatWindow.append(">> " + text + "\n");
    }
}
