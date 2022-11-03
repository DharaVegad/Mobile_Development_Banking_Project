package com.example.bankingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class PayUtilityBill extends AppCompatActivity {

    private static final String TAG = "com.android.tag.logs";
    private TextInputLayout subNo_layout, amount_layout;
    private TextInputEditText subNo_edittext, amount_edittext;
    private Button pay;
    private TextView result;

    private Spinner account_spinner;

    private int position_of_selected_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_utility_bill);

        // Setting the title of the action bar, the name of the menu from the previous activity
        String menu = getIntent().getStringExtra("menu");
        if(menu!=null){
            setTitle(menu);
        }

        initViews();

        // creating and inserting data into spinner
        ArrayList<Account> list = new ArrayList<>();
        list.add(new Account("Account 1",50000,"Savings"));
        list.add(new Account("Account 2",100000,"Current"));
        list.add(new Account("Account 3",5000,"Savings"));
        String[] acc_name = new String[list.size()+1];
        acc_name[0] = "Select Account";
        int count = 1;
        for(Account a : list){
            acc_name[count++] = a.getAccount_name();

        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,acc_name);
        account_spinner.setAdapter(spinnerAdapter);




        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                account_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        position_of_selected_account = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(PayUtilityBill.this, "Click something!", Toast.LENGTH_SHORT).show();
                    }
                });



                if(TextUtils.isEmpty(subNo_edittext.getText().toString())){
                    subNo_layout.setError("Enter Username");
                    subNo_layout.setErrorEnabled(true);
                }else if(TextUtils.isEmpty(amount_edittext.getText().toString())){
                    amount_layout.setError("Enter Password");
                    amount_layout.setErrorEnabled(true);
                }else if(list.get(position_of_selected_account).getCurrent_balance() <=0){
                    Toast.makeText(PayUtilityBill.this, "Your account balance is low", Toast.LENGTH_SHORT).show();
                }else if(list.get(position_of_selected_account).getCurrent_balance() - Double.parseDouble(amount_edittext.getText().toString()) < 0){
                    Toast.makeText(PayUtilityBill.this, "Account Balance is only "+list.get(position_of_selected_account).getCurrent_balance() , Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.i(TAG, ""+position_of_selected_account);
                    String subscriber_no = subNo_edittext.getText().toString().trim();
                    double amount = Double.parseDouble(amount_edittext.getText().toString().trim());

                    //deducting the amount
                    list.get(position_of_selected_account).withdraw(amount);
                    showAlertDialouge(subscriber_no);
                    result.setVisibility(View.VISIBLE);
                    result.setText("Payment Successful! \n Amount left: "+list.get(position_of_selected_account).getCurrent_balance());


                }

            }
        });





        subNo_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subNo_layout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        amount_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                amount_layout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showAlertDialouge(String subscriber_no) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Information")
                .setMessage("Do you Want to Save this Subscriber No. ? \n"+subscriber_no)
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PayUtilityBill.this, "Number Saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PayUtilityBill.this, UtilityMenuActivity.class));
                        finish();
                        // To save this number for unique user we need to have database. Without data base the information will be stored not according to user
                        // Data base is not implemented here so this info is not saved!
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PayUtilityBill.this, "Sure, We will not save the number", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PayUtilityBill.this, UtilityMenuActivity.class));
                finish();
            }
        }).show();
    }

    private void initViews() {

        subNo_layout = findViewById(R.id.subNo_textinput_layout);
        amount_layout = findViewById(R.id.amount_textinput_layout);

        subNo_edittext = findViewById(R.id.subNo_textinput_edittext);
        amount_edittext = findViewById(R.id.amount_textinput_ledittext);

        pay = findViewById(R.id.pay_btn);

        result = findViewById(R.id.result_textView);

        account_spinner = findViewById(R.id.spinner_id);
    }
}

