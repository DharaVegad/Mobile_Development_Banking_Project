package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class UtilityMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_menu);

        GridView gridView = findViewById(R.id.gridview);
        // To add menu just add another element to this array
        String[] menu = {"Hydro","Water","Gas","Phone","Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_grid_layout,R.id.custom_textView,menu);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(UtilityMenuActivity.this, PayUtilityBill.class);
                in.putExtra("menu",menu[position]);
                startActivity(in);
                finish();
            }
        });
    }
}

