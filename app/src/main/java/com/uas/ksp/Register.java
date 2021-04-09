package com.uas.ksp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {
    private TextInputLayout textInputName,textInputUsername, textInputPassword, textInputConfPassword;
    String name,username,password,confpass;
    Button Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textInputName = (TextInputLayout)findViewById(R.id.edtNameRegist);
        textInputUsername = (TextInputLayout)findViewById(R.id.edtUNameRegist);
        textInputPassword = (TextInputLayout)findViewById(R.id.edtPassRegist);
        textInputConfPassword = (TextInputLayout)findViewById(R.id.edtConfPassRegist);
    }

    //proses mengecek dan mendaftar user ke database
    public void onRegister(View view) throws ExecutionException, InterruptedException {
        name = textInputName.getEditText().getText().toString();
        username = textInputUsername.getEditText().getText().toString();
        password = textInputPassword.getEditText().getText().toString();
        confpass = textInputConfPassword.getEditText().getText().toString();
        if(password.equals(confpass)){
            String type ="register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            String chechRegist = backgroundWorker.execute(type,name,username,password,confpass).get();
            if (chechRegist.equals("success")){
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Username sudah tersedia, mohon diganti username anda",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Password dan confirmation password tidak sama",Toast.LENGTH_LONG).show();
        }


    }


}
