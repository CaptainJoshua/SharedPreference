package com.example.garciamysharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextMessage;
    Button buttonCounter;
    CheckBox checkBoxRememberMe;

    // container
    int count = 0;
    String username;
    String message;
    boolean isChecked;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextMessage = findViewById(R.id.editTextUserMessage);
        buttonCounter = findViewById(R.id.buttonCounter);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);

        buttonCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                buttonCounter.setText("" + count);
            }
        });
        retrieveData();
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    public void saveData() {
        sharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        username = editTextUserName.getText().toString();
        message = editTextMessage.getText().toString();

        if (checkBoxRememberMe.isChecked()) {
            isChecked = true;
        } else {
            isChecked = false;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key username", username);
        editor.putString("key message", message);
        editor.putInt("key count", count);
        editor.putBoolean("key rememberMe", isChecked);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Your Data is saved", Toast.LENGTH_LONG).show();
    }

    public void retrieveData() {
        sharedPreferences = getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("key username", "");
        message = sharedPreferences.getString("key message", "");
        count = sharedPreferences.getInt("key count", 0);
        isChecked = sharedPreferences.getBoolean("key rememberMe", false);

        editTextUserName.setText(username);
        editTextMessage.setText(message);
        buttonCounter.setText("" + count);

        if (isChecked) {
            checkBoxRememberMe.setChecked(true);
        } else {
            checkBoxRememberMe.setChecked(false);
        }
    }
}