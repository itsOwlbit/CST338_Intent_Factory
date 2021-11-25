package com.example.cst338_intent_factory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * title: ChildActivity.java
 * abstract: This class is created to be an intent factory.  An input message is received
 *           from MainActivity.java and the message input in this class is sent to
 *           MainActivity.java using intents and Extra.  The input field asks for input as to
 *           what the fox will say, while a textView displays what the cat said.
 * name: Juli S
 * date: 11/22/2021
 */

public class ChildActivity extends AppCompatActivity {

    private static final String TAG_CHILD = "IntentFactoryChild";
    private static final String M1CHILD = "com.example.cst338_intent_factory.child";

    private static String inputMessage;
    private static String failMessage = "Failed to get message.";

    EditText editText;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Log.d(TAG_CHILD, "on Create(Bundle) called from Child");

        editText = findViewById(R.id.editText_box);
        textView = findViewById(R.id.textView_chat);
        button = findViewById(R.id.button);

//        if(savedInstanceState != null) {
//            inputMessage = savedInstanceState.getString(M1CHILD, failMessage);
//        }

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            inputMessage = extras.getString(M1CHILD); // this got my message from Main.
        }

        textView.setText(inputMessage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMessage = editText.getText().toString();

                Intent intent = MainActivity.newIntent(ChildActivity.this,inputMessage);
                intent.putExtra(M1CHILD,inputMessage);
                startActivity(intent);
            }
        });
    }

    // Factory pattern implementation in ChildActivity
    public static Intent newIntent(Context context, String inputMessage) {
        Intent intent = new Intent(context, ChildActivity.class);
        intent.putExtra(M1CHILD, inputMessage);
        return intent;
    }
}