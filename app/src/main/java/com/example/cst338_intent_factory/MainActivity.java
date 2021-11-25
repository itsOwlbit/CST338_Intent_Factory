package com.example.cst338_intent_factory;

import androidx.annotation.NonNull;
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
 * title: MainActivity.java
 * abstract: This class is created to be an intent factory?  An input message is received
 *           from ChildActivity.java and the message input in this class is sent to
 *           ChildActivity.java using intents and Extra.  The input field asks for input as to
 *           what the cat will say, while a textView displays what the fox said.
 * name: Juli S
 * date: 11/22/2021
 */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "IntentFactoryMain";
    public static final String M1 = "ActivityState";

    private static String inputMessage;
    private static String failMessage = "Failed to get message.";

    EditText editText;  // the editText box that users can enter text in.
    TextView textView;
    Button button;      // button user presses after they enter text

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "on savedInstanceState from Main");
        savedInstanceState.putString(M1,inputMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "on Create(Bundle) called from Main");

        editText = findViewById(R.id.editText_box);
        textView = findViewById(R.id.textView_chat);
        button = findViewById(R.id.button);

//        if(savedInstanceState != null) {
//            inputMessage = savedInstanceState.getString(M1, failMessage);
//        }
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            inputMessage = extras.getString(M1); // this got my message from Main.
        }
//        inputMessage = getIntent().getStringExtra(M1); // this got my message back from Child.
        textView.setText(inputMessage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMessage = editText.getText().toString();

                Intent intent = ChildActivity.newIntent(MainActivity.this, inputMessage);
                intent.putExtra(M1,inputMessage);
                startActivity(intent);
            }
        });
    }

    // Factory pattern implementation in MainActivity
    public static Intent newIntent(Context context, String inputMessage) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(M1, inputMessage);
        return intent;
    }
}