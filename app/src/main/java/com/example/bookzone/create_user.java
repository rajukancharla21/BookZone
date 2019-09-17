package com.example.bookzone;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class create_user extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email_entered,password_entered,name_entered,phone_entered,address_entered;
    TextView login_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_user);
        email_entered=findViewById(R.id.user_email_create);
        password_entered=findViewById(R.id.user_password_create);
        login_user=findViewById(R.id.user_login);
        address_entered=findViewById(R.id.user_address_create);
        FloatingActionButton create=findViewById(R.id.user_create_button);
        name_entered=findViewById(R.id.user_name_create);
        phone_entered=findViewById(R.id.user_phone_create);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=email_entered.getText().toString();
                String password=password_entered.getText().toString();
                final String name=name_entered.getText().toString();
                final String phone=phone_entered.getText().toString();
                final String address=address_entered.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(name) || password.length()<6){
                    Toast.makeText(getApplicationContext(),"Enter Details",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(create_user.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@androidx.annotation.NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        User user=new User(name,email,phone,address);
                                        database.getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(), "Successfully created and Data Stored", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(create_user.this, login_user.class);
                                                    startActivity(intent);
                                                }
                                                else
                                                {
                                                    Log.w("Raju2 : ", task.getException());
                                                }
                                            }
                                        });

                                    } else {
                                        Log.w("Raju : ", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(create_user.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                }


            }
        });
        login_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_user.this, login_user.class);
                startActivity(intent);

            }
        });
    }
}
