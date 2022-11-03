package com.example.bankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TransferMoney extends AppCompatActivity {

    private TextInputLayout textInputLayout1, textInputLayout2, amount_layout;
    private TextInputEditText textInputEditText1, textInputEditText2, amount_editext;
    private Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);

        initViews();

        boolean isSelf = getIntent().getBooleanExtra("self",false);
        if(isSelf){
            // do for self
            setTitle("Transfer to Self");
            textInputEditText1.setHint("From Account");
            textInputEditText2.setHint("To Account");

            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(TextUtils.isEmpty(textInputEditText1.getText().toString())){
                        textInputLayout1.setError("Enter Account number");
                        textInputLayout1.setErrorEnabled(true);
                    }else if(TextUtils.isEmpty(textInputEditText2.getText().toString())){
                        textInputLayout2.setError("Enter Account Number");
                        textInputLayout2.setErrorEnabled(true);
                    }else if(TextUtils.isEmpty(amount_editext.getText().toString())){
                        amount_layout.setError("Enter Amount");
                        amount_layout.setErrorEnabled(true);
                    }else{
                        showAlertDialouge();
                    }
                }
            });
        }else{
            setTitle("Transfer to Other");
            textInputEditText1.setHint("Client Name");
            textInputEditText2.setHint("Account Number");

            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(TextUtils.isEmpty(textInputEditText1.getText().toString())){
                        textInputLayout1.setError("Enter Account number");
                        textInputLayout1.setErrorEnabled(true);
                    }else if(TextUtils.isEmpty(textInputEditText2.getText().toString())){
                        textInputLayout2.setError("Enter Account Number");
                        textInputLayout2.setErrorEnabled(true);
                    }else if(TextUtils.isEmpty(amount_editext.getText().toString())){
                        amount_layout.setError("Enter Amount");
                        amount_layout.setErrorEnabled(true);
                    }else{
                        showAlertDialouge();
                    }
                }
            });


        }
    }

    private void showAlertDialouge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Amount Transfer Successful")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(TransferMoney.this, MenuActivity.class));
                        finish();
                    }
                });
        builder.show();
    }

    private void initViews() {

        textInputLayout1 = findViewById(R.id.accountFrom_textinput_layout);
        textInputLayout2 = findViewById(R.id.accountTo_textinput_layout);

        textInputEditText1 = findViewById(R.id.accountFrom_textinput_edittext);
        textInputEditText2 = findViewById(R.id.accountTo_textinput_edittext);

        amount_layout = findViewById(R.id.amount_textinput_layout);
        amount_editext = findViewById(R.id.amount_textinput_edittext);

        pay = findViewById(R.id.pay_btn);
    }
}


