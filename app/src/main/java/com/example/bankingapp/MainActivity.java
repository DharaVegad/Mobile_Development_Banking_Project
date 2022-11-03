package com.example.bankingapp;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout user_layout, pass_layout;
    private TextInputEditText user_edittext, pass_edittext;
    private MaterialButton login;

    private final String USER_NAME = "user";
    private final int PASSWORD = 2628;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login");

        // initializing the views defined above
        initViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(user_edittext.getText().toString())){
                    user_layout.setError("Enter Username");
                    user_layout.setErrorEnabled(true);
                }else if(TextUtils.isEmpty(pass_edittext.getText().toString())){
                    pass_layout.setError("Enter Password");
                    pass_layout.setErrorEnabled(true);
                }else{
                    String username = user_edittext.getText().toString().trim();
                    String password = pass_edittext.getText().toString().trim();
                    boolean authorised = verifyUser(username, password);
                    if(authorised){
                        // go to screen two
                        Intent in = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(in);
                        finish();
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    }else{
                        Snackbar.make(findViewById(R.id.relLayout_id), "Invalid Username or Password",Snackbar.LENGTH_SHORT).show();
                    }
                }

            }
        });


        // This is to ensure that the error mesasge is gone after the user starts writing in the respective field.
        user_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user_layout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pass_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pass_layout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private boolean verifyUser(String username, String password) {
        return username.equals(this.USER_NAME) && Integer.parseInt(password) == this.PASSWORD;
    }

    private void initViews() {

        user_layout = findViewById(R.id.username_textinput_layout);
        pass_layout = findViewById(R.id.password_textinput_layout);

        user_edittext = findViewById(R.id.username_textinput_edittext);
        pass_edittext = findViewById(R.id.password_textinput_edittext);

        login = findViewById(R.id.submit_btn_id);
    }
}



