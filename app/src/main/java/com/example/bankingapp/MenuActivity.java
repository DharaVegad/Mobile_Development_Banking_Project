package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MaterialCardView utility_card = findViewById(R.id.utility_card_id);
        MaterialCardView self_transfer = findViewById(R.id.same_account_transfer_card_id);
        MaterialCardView other_transfer = findViewById(R.id.other_account_transfer_card_id);

        utility_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this,UtilityMenuActivity.class));
                finish();
            }
        });

        self_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MenuActivity.this, TransferMoney.class);
                in.putExtra("self",true);
                startActivity(in);
                finish();
            }
        });

        other_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MenuActivity.this, TransferMoney.class);
                in.putExtra("self",false);
                startActivity(in);
                finish();
            }
        });
    }
}


