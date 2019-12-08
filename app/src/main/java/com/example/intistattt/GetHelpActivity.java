package com.example.intistattt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GetHelpActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gethelp);
        getSupportActionBar().setTitle("Get Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RadioButton rb1 = findViewById(R.id.radioButton1);
        RadioButton rb2 = findViewById(R.id.radioButton2);
        RadioButton rb3 = findViewById(R.id.radioButton3);
        RadioGroup rg = findViewById(R.id.radioGroup);


        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((RadioButton)view).isChecked()) {
                    intent = new Intent(GetHelpActivity.this, PasswordProblemActivity.class);
                    startActivity(intent);
                    Toast.makeText(GetHelpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((RadioButton) view).isChecked()) {
                    intent = new Intent(GetHelpActivity.this, UsernameProblemActivity.class);
                    startActivity(intent);
                    Toast.makeText(GetHelpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((RadioButton)view).isChecked()) {
                    intent = new Intent(GetHelpActivity.this, FAQHelpActivity.class);
                    startActivity(intent);
                    Toast.makeText(GetHelpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}

