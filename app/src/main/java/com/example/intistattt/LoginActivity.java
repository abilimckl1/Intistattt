package com.example.intistattt;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intistattt.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private String messageResId;
    boolean loginSuccess = false;
    FirebaseDatabase database;
    DatabaseReference mDatabaseReferenceAttend;
    ValueEventListener mValueEventListenerAttend;
    DatabaseReference user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = FirebaseDatabase.getInstance();
        user = database.getReference("student");
        final Button button = findViewById(R.id.login);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final TextView troubleLogin = findViewById(R.id.textTroubleLogin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
        troubleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, GetHelpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(final String userid, final String password){
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userid).exists()) {
                    if (!userid.isEmpty()) {
                        User user = dataSnapshot.child(userid).getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            loginSuccess = true;
                            Toast.makeText(LoginActivity.this, getString(R.string.login_success)+ " back, " + user.getName(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            loginSuccess = false;
                            Toast.makeText(LoginActivity.this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        loginSuccess = false;
                        Toast.makeText(LoginActivity.this, getString(R.string.blank_username), Toast.LENGTH_SHORT).show();
                    }

                    mDatabaseReferenceAttend = FirebaseDatabase.getInstance().getReference("student").child(userid).child("classattended");

                    if(loginSuccess){
                        mValueEventListenerAttend = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Intent goMain = new Intent(LoginActivity.this, MainActivity.class);
                                ArrayList<String> values = new ArrayList<>();
                                ArrayList<String> keys = new ArrayList<>();

                                for(DataSnapshot child : dataSnapshot.getChildren())
                                {
                                    values.add(child.getValue().toString());
                                    keys.add(child.getKey());
                                }
                                goMain.putStringArrayListExtra("CLASS_ATTENDED", values);
                                goMain.putStringArrayListExtra("KEY_CLASS", keys);
                                startActivity(goMain);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        };
                        mDatabaseReferenceAttend.addValueEventListener(mValueEventListenerAttend);
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, getString(R.string.invalid_username), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, getString(R.string.connection_database_failed), Toast.LENGTH_SHORT).show();
            }
        });

    }
}