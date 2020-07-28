package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText userName, userPassword;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        userName = (TextInputEditText) view.getRootView().findViewById(R.id.user_name_text);
        userPassword = (TextInputEditText) view.getRootView().findViewById(R.id.user_pass_text);

        Log.d("username", "onLogin: " + userName.getText().toString());
        String usrnm, usrpwd;
        usrnm = userName.getText().toString();
        usrpwd = userPassword.getText().toString();
//        Log.d("username", usrnm);
        if (usrnm.equals("admin") && usrpwd.equals("123")) {
            Log.d("Success", "onLogin: Finish");
            Toast.makeText(getApplicationContext(), "Valid Credentials", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        } else {
            Log.d("Fail", "onLogin: Failure");
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
        }
        userName.setText("");
        userPassword.setText("");
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}