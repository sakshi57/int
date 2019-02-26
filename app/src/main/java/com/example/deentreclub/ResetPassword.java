package com.example.deentreclub;

import android.content.Intent;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;
    private EditText editTextmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        firebaseAuth = FirebaseAuth.getInstance();



        editTextmail=findViewById(R.id.mail);
        passwordEmail = (EditText)findViewById(R.id.mail);
        resetPassword = (Button)findViewById(R.id.resetPass);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = passwordEmail.getText().toString().trim();

                if (email.isEmpty()) {
                    editTextmail.setError("Email id is required!");
                    editTextmail.requestFocus();
                    return;
                }

                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Password reset mail sent!",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ResetPassword.this, MainActivity.class);
                                startActivity(i);

                            }

                            else{
                                Toast.makeText(getApplicationContext(),"Please provide registered email id!",Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
                }

            }
        });

    }
}
